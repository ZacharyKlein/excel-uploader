package com.ociweb

import org.grails.plugins.excelimport.*

class BookExcelImporter extends AbstractExcelImporter {

    static Map CONFIG_BOOK_COLUMN_MAP = [
            sheet:'Sheet1',
            startRow: 1,
            columnMap:  [
                    'B':'title',
                    'C':'author',
                    'D':'sales',
            ]
    ]

    //can also configure injection in resources.groovy
    def getExcelImportService() {
        ExcelImportService.getService()
    }

    public BookExcelImporter(fileName) {
        super(fileName)
    }

    List<Map> getBooks() {
        List bookList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP)
    }

}