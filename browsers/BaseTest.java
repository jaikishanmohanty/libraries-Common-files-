package browsers;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ConfigFileReader;
import utilities.GoogleSheetAPIUtils;

/**
 * Base Test Class for launch browser.
 * @author priyankag@dayalgroup.hq
 *
 */
public class BaseTest {

	protected WebDriver driver;
	FirefoxProfile geoDisabled;
	ChromeOptions options;
	DesiredCapabilities cap;
	BrowserActions actions = new BrowserActions(driver);
	ConfigFileReader configFileReader = new ConfigFileReader();
	GoogleSheetAPIUtils googleSheet = new GoogleSheetAPIUtils();

		
	/**
	 * SetUp Function : Get Browser Type from Suite file.
	 * @param browserName
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	//@BeforeClass
	//@Parameters({ "browserType"})
	public void setUp(String browserName) throws IOException, GeneralSecurityException {
		getSathiUrl();
		setDriver(browserName);

	}

	/**
	 * Set up function for selenium grid.
	 * @param node 
	 * @param browserName Name of the browser
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	//@BeforeClass
	//@Parameters({"node", "browserName"})
	@Parameters({"node", "browserType"})
	public void setUp(String node,String browserName) throws IOException, GeneralSecurityException {
		System.out.println("this is the test related to "+  browserName+" browserstack login"+ " " +Thread.currentThread().getId());

		//getSathiUrl();
		if(browserName.equalsIgnoreCase("firefox")) {
			cap=DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
					configFileReader.getFirefoxWinDriverPath());
			driver=new RemoteWebDriver(new URL(node),cap);
			//driver = new FirefoxDriver(new URL(node),cap);
			//driver = new FirefoxDriver(cap);
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			cap=DesiredCapabilities.chrome();
			cap.setPlatform(Platform.ANY);
			//driver=new RemoteWebDriver(new URL(node),cap);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
					configFileReader.getChromeWinDriverPath());
			driver=new RemoteWebDriver(new URL(node),cap);
			//driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	/**
	 * Method for calling different functions as per browser type for launch browser.
	 * @param browserType Name of the browser
	 * @throws UnknownHostException
	 */
	private void setDriver(String browserType) throws UnknownHostException {
		switch (browserType) {
		case "chrome":
			driver = startChromeDriver();
			break;
		case "firefox":
			driver = startFirefoxDriver();
			break;
		case "ie":
			driver = startIEDriver();
			break;
		case "opera":
			driver = startOperaDriver();
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = startFirefoxDriver();
		}
	}
	
	/**
	 * Getter for WebDriver
	 * @return WebDriver
	 */
	public WebDriver getDriver() 
	{
		return driver;
	}
	
	/**
	 * Test for stop driver after class execution.
	 */
		@AfterClass
		public void stopDriver() {
			//driver.quit();
		}
/**
 * Method for launch ie browser.
 * @return driver
 */
	public WebDriver startIEDriver() {
		
		if( System.getProperty("os.name").contains("Windows")) {
			System.out.println("Windows Platform");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +
					configFileReader.getIEWinDriverPath());
		}
		/*
		 * else if (System.getProperty("os.name").contains("Linux")){
		 * System.out.println("Linux Platform");
		 * System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +
		 * configFileReader.getIELinuxDriverPath()); }
		 */
		driver = new InternetExplorerDriver();

		return driver;
	}
	/**
	 * Method for launch opera browser.
	 * @return driver
	 */
	public WebDriver startOperaDriver() {
		if( System.getProperty("os.name").contains("Windows")) {
			System.out.println("Windows Platform");
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +
					configFileReader.getOperaWinDriverPath());
		}
		
		  else if (System.getProperty("os.name").contains("Linux")){
		  System.out.println("Linux Platform");
		  
		  System.out.println(" Check fo rOPera browser");
		  DesiredCapabilities capabilities = DesiredCapabilities.opera();
		  ChromeOptions options = new ChromeOptions();
		 // options.addExtensions(new File("/home/priyankag@dayalgroup.hq/Downloads/IDMGCEXTIDM-Chrome-Extension"));
		  //options.addArguments("user-data-dir=/path/to/profile/if/needed");
		  //options.setBinary('/Applications/Opera.app/Contents/MacOS/Opera');
		 // capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		  
//		  DesiredCapabilities capabilities = DesiredCapabilities.opera();
//
//		  ChromeOptions options = new ChromeOptions();
//			options.setBinary("/usr/bin/opera");
//
//		  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		  
//		  OperaOptions options = new OperaOptions();
//			options.setBinary("/usr/bin/opera");
//			DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
//			capabilities.setAcceptInsecureCerts(true);
//			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//
//			options.addArguments("--use-fake-ui-for-media-stream");
//			options.addArguments("--use-fake-device-for-media-stream");
//			capabilities.setCapability(OperaOptions.CAPABILITY, options);
		  System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +configFileReader.getOperaLinuxDriverPath()); 
		
	//	  OperaDriver browser = new OperaDriver(capabilities);

	//	  driver =browser;

	  driver = new OperaDriver(capabilities);
//		  driver.manage().window().maximize();

		  }
		 
	driver = new OperaDriver();

return driver;		
	}
	
	/**
	 * Method for launch firefox browser
	 * @return driver
	 * @throws UnknownHostException
	 */
	public WebDriver startFirefoxDriver() throws UnknownHostException {
		//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");

		if( System.getProperty("os.name").contains("Windows")) {
			System.out.println("Windows Platform");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
					configFileReader.getFirefoxWinDriverPath());
		}
		else if (System.getProperty("os.name").contains("Linux")){
			System.out.println("Linux Platform");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
					configFileReader.getFirefoxLinuxDriverPath());
		}
		//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./FFLogs.txt");
		cap = DesiredCapabilities.firefox();
		geoDisabled = new FirefoxProfile();
		geoDisabled.setPreference("geo.enabled", false);
		geoDisabled.setPreference("geo.provider.use_corelocation", false);
		geoDisabled.setPreference("geo.prompt.testing", false);
		geoDisabled.setPreference("geo.prompt.testing.allow", false);
		cap.setCapability(FirefoxDriver.PROFILE, geoDisabled);
		cap.setCapability("marionette", true);
		cap.setBrowserName("firefox");
		//driver = new FirefoxDriver(geoDisabled);

		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();	
		return driver;

	}

	/**
	 * Method for launch chrome browser.
	 * @return driver
	 */
	public WebDriver startChromeDriver() {

		//System.setProperty("webdriver.chrome.silentOutput", "true");
		//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		if( System.getProperty("os.name").contains("Windows")) {
			System.out.println("Windows Platform");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
					configFileReader.getChromeWinDriverPath());
		}
		else if (System.getProperty("os.name").contains("Linux")){
			System.out.println("Linux Platform");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
					configFileReader.getChromeLinuxDriverPath());
		}
		/*	options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("test-type");
			options.addArguments("enable-strict-powerful-feature-restrictions");
			options.addArguments("disable-geolocation");
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			cap = cap.merge(DesiredCapabilities.chrome());			
			driver = new ChromeDriver(options);*/
		//driver = new ChromeDriver(cap);

		//System.setProperty("webdriver.chrome.logfile", "./Chromelog.txt");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * Method for Check OS name.
	 */
	public void CheckBrowserOS() {

		//Get Browser name and version.
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		//Get OS name.
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("OS = " + os + ", Browser = " + browserName + " "+ browserVersion);
	} 

	
	/**
	 * getSathiUrl Function : Get Sathi Url From Configuration SpreadSheet as per system Ip Address.
	 * @return sathi url
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public String getSathiUrl() throws IOException, GeneralSecurityException {
		String sathi_Url = null;
		InetAddress localhost = InetAddress.getLocalHost();
		String Ip_Add = localhost.getHostAddress();
		System.out.println("IP ADD:"+Ip_Add);
		String configurationSpreadsheetId = configFileReader.getConfigurationSpreadSheetId();
		String web_Credential = configFileReader.getWebLoginCreadentialSheetName();
		Object[][] arrayObjectWebCredential = googleSheet.readDataFromGoogleSheet(configurationSpreadsheetId,web_Credential);
		Object[][] webDataForIpAdd =actions.getFilterDataFromExcelSheet(arrayObjectWebCredential, Ip_Add);
		for(Object[] val: webDataForIpAdd) {
			sathi_Url =  val[2].toString();
			if(sathi_Url !=null) {
				break;
			}
		}
		return sathi_Url;
	}

}


