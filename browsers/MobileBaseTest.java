package browsers;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import helpers.LoggerHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class MobileBaseTest {
	
	Logger log = LoggerHelper.getLogger(MobileBaseTest.class);
	
	protected AppiumDriver<WebElement> driver;
	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;
	DesiredCapabilities capabilities;
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		startAppiumService();
		launchApp();
		//launchChromeBrowser();
	}
	
	@AfterClass
	public void stopAppium() {
		appiumService.stop();
		//driver.quit();
	}
	
	public void startAppiumService() {
		appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();
		appiumServiceUrl = appiumService.getUrl().toString();
		log.info("Appium Service Address : - "+ appiumServiceUrl);
	}
	
	public void launchApp() throws MalformedURLException {
		
		String iSATHIApkUrl = "C:/Users/priyankag/WorkSpace/Library/resources/apps/iSATHI.apk";
		String mSATHIApkUrl = "C:/Users/priyankag/WorkSpace/Library/resources/apps/mSATHI.apk";
		String androidDeviceName= "emulator-5554 device";
		//String androidDeviceName= "Q4459HAIJVWORWMB";
		String androidDeviceVersion = "7.0";
		String iSathiAppPackageName = "com.dayalinfosystems.isathi";
		String iSathiAppActivityName = "com.dayalinfosystems.isathi.app.ActivitySplash";
		String mSathiAppPackageName = "com.dayalinfosystems.msathi";
		String mSathiAppActivityName = "com.dayalinfosystems.msathi.app.ActivitySplash";
		
		capabilities = new DesiredCapabilities();
//		for installing the new app in the device
//		File app = new File(iSATHIApkUrl);
//		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidDeviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidDeviceVersion);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, iSathiAppPackageName);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, iSathiAppActivityName);
		capabilities.setCapability("autoGrantPermissions", "true");
		capabilities.setCapability("autoAcceptAlerts", "true");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
		
		driver = new AndroidDriver<>(new URL(appiumServiceUrl), capabilities);
	
	}

}
