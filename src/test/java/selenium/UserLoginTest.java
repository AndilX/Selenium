package selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class UserLoginTest {
	private static WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    private static final String FILE_NAME = System.getProperty("user.dir") + "\\DemoSiteDDT.xlsx";

	@Before
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test @Ignore
	public void testUserLogin() throws InterruptedException {
		driver.manage().window();
		driver.get("http://thedemosite.co.uk/");
		Thread.sleep(1000);
		// ERROR: Caught exception [unknown command [#]]
		driver.findElement(By.linkText("3. Add a User")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Wakanda");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("forever");
		driver.findElement(By.name("FormsButton2")).click();
		driver.findElement(By.linkText("4. Login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Wakanda");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("forever");
		driver.findElement(By.name("FormsButton2")).click();
        Thread.sleep(3000);

    }

    @Test
    public void testUserLoginExcelReadWrite() throws InterruptedException{
//
        // where to create the report file
        report = new ExtentReports("C:\\Users\\QAC\\Desktop\\testResult\\automationreportUserLogin.html", true);
        // init/start the test
        test = report.startTest("Verify user login read data / write result");

        driver.manage().window();
        driver.get("http://thedemosite.co.uk/");
        Thread.sleep(1000);

        // ----------
        driver.findElement(By.linkText("3. Add a User")).click();
        Thread.sleep(1000);

        try{
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet1 = workbook.getSheetAt(0);

//        //reading file
            Iterator<Row> iterator = sheet1.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                int rowIndex = currentCell.getRowIndex();

                    if(rowIndex == 1){
                        int columnIndex = currentCell.getColumnIndex();
                        switch (columnIndex){
                            case 1:
                                String username = cellIterator.next().getStringCellValue();
                                System.out.println(username);
                                break;
                            case 2:
                                String password = cellIterator.next().getStringCellValue();
                                System.out.println(password);
                                break;
                        }
                    }

                }
            }

            for (int rowNumb = 0; rowNumb<=3 ; rowNumb++){
                Row row = sheet1.getRow(rowNumb+1);
                for(int colNumb = 0;colNumb <= 1;colNumb++){
                    Cell cell = row.getCell(rowNumb);

                    if (cell == null)
                    {
                        System.out.println("Cell is Empty in Column");
                        break;

                    } else if (cell.getCellTypeEnum() == CellType.STRING)
                    {
                        colNumb = 0;
                        cell = row.getCell(colNumb);
                        String username = cell.getStringCellValue();
                        System.out.println(username);
                        Cell cell2 = row.getCell(colNumb+1);
                        String password = cell2.getStringCellValue();
                        System.out.println(password);
                    }
                    break;

                }


            }

//
//        //writing result
                int rowNum = 1;
                Row row = sheet1.getRow(rowNum);
                int colNum = 2;
                Cell cell = row.createCell(colNum,CellType.STRING);
                cell.setCellValue("PASS");

                try {
                    FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
                    workbook.write(outputStream);
                    workbook.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        report.endTest(test);
        report.flush();


    }

	@After
	public void tearDown(){
		driver.quit();
	}
}
