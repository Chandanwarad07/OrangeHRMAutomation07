package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";

    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    // Constructor
    public ExcelUtility(String sheetName) throws IOException {

        fis = new FileInputStream(path);

        workbook = new XSSFWorkbook(fis);

        sheet = workbook.getSheet(sheetName);
    }

    // Get total rows
    public int getRowCount() {

        return sheet.getLastRowNum();
    }

    // Get total columns
    public int getCellCount() {

        return sheet.getRow(0).getLastCellNum();
    }

    // Read cell data
    public String getCellData(int row, int col) {

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(
                sheet.getRow(row).getCell(col));
    }

    // Close workbook
    public void closeExcel() throws IOException {

        workbook.close();

        fis.close();
    }

}