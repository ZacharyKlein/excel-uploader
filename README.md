# Excel Uploader
Demo app for the grails-excel-import plugin: https://github.com/gpc/grails-excel-import

```
    compile "org.grails.plugins:excel-import:3.0.0.BUILD-SNAPSHOT"
```

If Grails 3.2.3 is installed on your `PATH`:

`grails run-app`

Otherwise use the Gradle wrapper:

`./gradlew bootRun` (Unix) 

`gradlew bootRun` (Windows)

Navigate to `http:localhost:8080/excelUploader` and upload an excel sheet in the expected format (see sample under `grails-app/docs/Books.xlsx`). Then navigate to `http:localhost:8080/book` and you should see the newly created `Book` instances from the sheet.

##Code

The main custom peice of code is `com.ociweb.BookExcelImporter`, which extends the `AbstractExcelImporter` provided by the plugin, and mostly serves to map columns on the Excel sheet to keys that are appropriate for a domain class property map.

E.g, for the following Excel sheet (see sample under `grails-app/docs/Books.xlsx`):


| ID       |      Title                    |        Author             |  NumSold
|----------|:-----------------------------:|--------------------------:|-----------:
| 1        |  Definitive Guide to Grails   | Jeff Brown, Graeme Rocher |  10000
| 2        |  Groovy Recipes               |         Scott Davis       |  15000
| 3        |  Grails - A Quick Start Guide |          Dave Klein       |   9500

The importer looks like this:

```
class BookExcelImporter extends AbstractExcelImporter {

    static Map CONFIG_BOOK_COLUMN_MAP = [
            sheet:'Sheet1',
            startRow: 1,
            columnMap:  [
                    'B':'title',
                    'C':'author',
                    'D':'numSold',
            ]
    ]

    List<Map> getBooks() {
        List bookList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP)
    }

}
```

Note that the columns "B", "C", and "D" are mapped to the properties `title`, `author`, and `sales`, which are the properties of our target domain class below:

```
class Book {
    String title
    String author
    Integer sales
}
```

We can now use our importer in our controller (see `com.ociweb.ExcelUploaderController`):

```

```
