package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FTSE100Test {
	private static RemoteWebDriver driver;
	private WebElement targ;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));

	}

	@Test
	public void riserTest() throws InterruptedException {
		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
		System.out.println("Finding the largest Riser.");
		targ = driver.findElement(By.xpath("//*[@id=\"acceptCookie\"]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/ul/li[2]/a"));
		targ.click();
//		targ = driver.findElement(By.xpath("//*[@id=\"acceptCookie\"]"));
//		targ.click();
		assertEquals("Compass Group plc",
				driver.findElement(By.xpath("//*[@id=\"ls-row-CPG-L\"]/td[2]/a")).getText());
		System.out.println("Largest riser found. It is: Compass Group plc");

		Thread.sleep(10000);
	}
	
	@Test
	public void fallerTest() throws InterruptedException {
		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
		System.out.println("Finding the largest Faller.");
		targ = driver.findElement(By.xpath("//*//*[@id=\"acceptCookie\"]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/ul/li[3]/a"));
		targ.click();
//		targ = driver.findElement(By.xpath("//*[@id=\"acceptCookie\"]"));
//		targ.click();
		assertEquals("Just Eat Takeaway.com NV",
				driver.findElement(By.xpath("//*[@id=\"ls-row-JET-L\"]/td[2]/a")).getText());
		System.out.println("Largest faller found. It is: Just Eat Takeaway.com NV");

	}
	

	@After
	public void tearDown() {
		driver.close();
	}

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;

	}

}
