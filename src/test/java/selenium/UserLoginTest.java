package selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserLoginTest {
	private static WebDriver driver;
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
    public void testUserLoginExcelWrite() throws InterruptedException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        System.out.println("Creating sheet....");
        XSSFSheet sheet = workbook.createSheet("User Logins");

        Object[][] datatypes = {
                {"Username", "Password", "Result"}};

        driver.manage().window();
        driver.get("http://thedemosite.co.uk/");
        Thread.sleep(1000);
        // ERROR: Caught exception [unknown command [#]]
        driver.findElement(By.linkText("3. Add a User")).click();
        Thread.sleep(1000);


        int rowNum = 1;

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
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

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	@After
	public void tearDown(){
		driver.quit();
	}
}
