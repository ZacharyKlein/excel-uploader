package com.ociweb

import org.springframework.web.multipart.MultipartFile

class ExcelUploaderController {


    def index() { }

    def upload() {
        println "upload...."
        MultipartFile workbook = request.getFile('myFile')

        if(workbook) {

            def webrootDir = servletContext.getRealPath("/") //app directory
            File fileDest = new File(webrootDir,"uploads/myFile.xls")
            workbook.transferTo(fileDest)

            BookExcelImporter importer = new BookExcelImporter(fileDest.absolutePath)

            def bookRows = importer.getBooks()


            bookRows.each { props ->
                println props.toString()
                def book = new Book(props)
                if(!book.save(flush: true)) {
                    book.errors.allErrors.each { println it }
                }
            }


        }

        flash.message = "Upload complete"
        redirect action: 'index'
    }
}
