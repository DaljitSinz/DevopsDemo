import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by vipuljain on 14/11/18.
 */
public class DemoTest {

    WebDriver driver ;
    String hostURL = "http://localhost:8080/";

    @AfterTest
    public void tearDown()
    {
        driver.close();
    }

    @BeforeTest
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
        driver = new ChromeDriver();
        driver.get(hostURL);

    }


    @Test
    public void validatePageTitle()
    {
        String expected = "PetClinic :: a Spring Framework demonstration";
        driver.get(hostURL);
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);


    }


    @Test
    public void validateAddNewOwner()
    {
        driver.findElement(By.cssSelector("[title='find owners']")).click();
        driver.findElement(By.cssSelector("a.btn.btn-default")).click();
        driver.findElement(By.name("firstName")).sendKeys("Mark");
        driver.findElement(By.name("lastName")).sendKeys("Ford");
        driver.findElement(By.name("address")).sendKeys("random");
        driver.findElement(By.name("city")).sendKeys("delhi");
        driver.findElement(By.name("telephone")).sendKeys("9988776655");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.findElement(By.cssSelector("[title='find owners']")).click();
        driver.findElement(By.name("lastName")).sendKeys("Ford");
        driver.findElement(By.cssSelector("button.btn.btn.btn-default")).click();
        int count = driver.findElement(By.id("vets")).findElements(By.tagName("tr")).size();
        System.out.println(count);
        Assert.assertTrue(count > 1);
    }

    @Test
    public void validateVets()
    {
        driver.findElement(By.cssSelector("a[title='veterinarians']")).click();
        int count = driver.findElement(By.id("vets")).findElements(By.tagName("tr")).size();
        System.out.println(count);
        Assert.assertTrue(count > 1);
    }
}
