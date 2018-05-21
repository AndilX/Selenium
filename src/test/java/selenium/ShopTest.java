package selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ShopTest {

    private static WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    private static final String FILE_NAME = System.getProperty("user.dir") + "\\shopDataTest.xlsx";


    private String email ="";
    private String fname ="";
    private String lname ="";
    private String password ="";
    private String address = "";
    private String city = "";
    private int zip;
    private int phone;
    private String alias = "";




    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test @Ignore
    public void shopTest1() throws InterruptedException, IOException{

        // where to create the report file
        report = new ExtentReports("C:\\Users\\QAC\\Desktop\\testResult\\automationreportShopTest1.html", true);
        // init/start the test
        test = report.startTest("Verify shop test example 1");
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        test.log(LogStatus.INFO, "Browser started");
        driver.get("http://automationpractice.com/index.php");
        String item = driver.findElement(By.partialLinkText("Printed Summer Dress")).getText();

        if(item.equals("Printed Summer Dress")){
            test.log(LogStatus.PASS, "item found");
        } else{
            test.log(LogStatus.FAIL, "item not found");
        }

        try{
            Assert.assertEquals("Printed Chiffn Dress", driver.findElement(By.partialLinkText("Printed Chiffon Dress")).getText());
            test.log(LogStatus.PASS, "item found");
        } catch (AssertionError e){
            test.log(LogStatus.FAIL, "item not found");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\img.jpg"));
            String image = test.addScreenCapture("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\img.jpg");
            test.log(LogStatus.FAIL, "item not found", image);
        }

        report.endTest(test);
        report.flush();
    }


    @Test
    public void shopTestIO() throws InterruptedException, IOException{
        // where to create the report file
        report = new ExtentReports("C:\\Users\\QAC\\Desktop\\testResult\\automationreportShopTestIO.html", true);
        // init/start the test
        test = report.startTest("Verify shop test example read/write");
        driver.manage().window();
        Thread.sleep(1000);
        test.log(LogStatus.INFO, "Browser started");
        driver.get("http://automationpractice.com/index.php");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        Thread.sleep(1000);

        //get first item in spreadsheet

        try {
            test.log(LogStatus.INFO, "Reading data from excel");
            FileInputStream dataFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(dataFile);
            Sheet datatypeSheet = workbook.getSheet("Test Data");
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        if ("email".equals(currentCell.getStringCellValue())) {
                            email = cellIterator.next().getStringCellValue();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("email_create")).sendKeys(email);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
        Thread.sleep(5000);
        // add screenshot-assert

        try{
            Assert.assertTrue(driver.findElement(By.id("id_gender1")).isDisplayed());
        } catch (AssertionError e){
            test.log(LogStatus.FAIL, "User already registered");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgTest.jpg"));
            String image = test.addScreenCapture("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgTest.jpg");
            test.log(LogStatus.FAIL, "User already registered", image);
        }
        driver.findElement(By.id("id_gender1")).click();
        Thread.sleep(5000);
        //reading all data

        try {
            test.log(LogStatus.INFO, "Reading data from excel");
            FileInputStream dataFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(dataFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        if ("First name".equals(currentCell.getStringCellValue())) {
                            fname = cellIterator.next().getStringCellValue();
                        }
                        if ("Last name".equals(currentCell.getStringCellValue())) {
                            lname = cellIterator.next().getStringCellValue();
                        }
                        if ("Password".equals(currentCell.getStringCellValue())) {
                            password = cellIterator.next().getStringCellValue();
                        }
                        if ("Address".equals(currentCell.getStringCellValue())) {
                            address = cellIterator.next().getStringCellValue();
                        }
                        if ("City".equals(currentCell.getStringCellValue())) {
                            city = cellIterator.next().getStringCellValue();
                        }
                        if ("Alias".equals(currentCell.getStringCellValue())) {
                            alias = cellIterator.next().getStringCellValue();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread.sleep(2000);
        driver.findElement(By.id("customer_firstname")).sendKeys(fname);
        Thread.sleep(500);
        driver.findElement(By.id("customer_lastname")).sendKeys(lname);
        Thread.sleep(500);
        driver.findElement(By.id("passwd")).sendKeys(password);
        Thread.sleep(500);
        driver.findElement(By.id("address1")).sendKeys(address);
        Thread.sleep(500);
        driver.findElement(By.id("city")).sendKeys(city);
        Thread.sleep(500);
        driver.findElement(By.id("id_state")).click();
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Idaho");
        Thread.sleep(500);
        driver.findElement(By.id("postcode")).sendKeys("00055");//(String.valueOf(zip));//zip
        Thread.sleep(500);
        driver.findElement(By.id("phone_mobile")).sendKeys("555888899");//(String.valueOf(phone)); //phone
        Thread.sleep(500);
        driver.findElement(By.id("alias")).clear();
        Thread.sleep(500);
        driver.findElement(By.id("alias")).sendKeys(alias);
        Thread.sleep(500);
        //submit data after read
        driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
        Thread.sleep(2000);
        //to confirm and then write 'Success' on file
        try{
            Assert.assertEquals(fname + " " + lname,driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgPass.jpg"));
            String image = test.addScreenCapture("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgPass.jpg");
            test.log(LogStatus.PASS, "Registration successful",image);

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = 13;
            Row row = sheet.getRow(rowNum);
            int colNum = 1;
            Cell cell = row.createCell(colNum,CellType.STRING);
            cell.setCellValue("PASS");
            test.log(LogStatus.INFO, "Test results logged on the excel file");


            try {
                FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (AssertionError e){
            test.log(LogStatus.FAIL, "Registration failed");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgfail.jpg"));
            String image = test.addScreenCapture("C:\\Users\\QAC\\Desktop\\testResult\\screenshot\\imgfail.jpg");
            test.log(LogStatus.FAIL, "Registration failed", image);
        }

        report.endTest(test);
        report.flush();

    }




    @After
    public void tearDown(){
        driver.quit();
    }


}
