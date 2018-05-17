package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApachePOIExcelWrite {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\ExcelExampleWrite.xlsx";

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        System.out.println("Creating sheet 1");
        XSSFSheet sheet1 = workbook.createSheet("Datatypes in Java");
        System.out.println("Creating sheet 2");
        XSSFSheet sheet2 = workbook.createSheet("Test");

        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        Object[][] datatests = {
                {"A", "B", "C"},
                {1, 3, 2},
                {"$","%%","!!!!!"}
        };

        int rowNum1 = 0;
        System.out.println("Writing sheet 1");

        for (Object[] datatype : datatypes) {
            Row row = sheet1.createRow(rowNum1++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        int rowNum2 = 0;
        System.out.println("Writing sheet 2");

        for (Object[] datatest: datatests) {
            Row row = sheet2.createRow(rowNum2++);
            int colNum2 = 0;
            for (Object field : datatest) {
                Cell cell = row.createCell(colNum2++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}