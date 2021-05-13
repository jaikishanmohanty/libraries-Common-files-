package browsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import helpers.LoggerHelper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * This class for start appium service and appium server.
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class MobileBase {

	Logger log = LoggerHelper.getLogger(MobileBase.class);
	protected AndroidDriver driver;
	protected RemoteWebDriver remoteDriver;

	static AppiumDriverLocalService appiumService;
	static String appiumServiceUrl;
	DesiredCapabilities capabilities;
	AppiumServiceBuilder builder;

	// For Appium Grid

	private AppiumServiceBuilder server;
	private AppiumDriverLocalService service;
	String platformRunAs;
	String appActivityName;


	/**
	 * Function For setup appium grid for start appium server.
	 * 
	 * @param androidVersion
	 * @param runOn
	 * @param url
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	// @BeforeClass
	// @Parameters({"androidVersion","runOn","url"})
	public void setupAppiumGrid(String androidVersion, String runOn, String url)
			throws MalformedURLException, InterruptedException {
		// startAppiumService();
		startAppiumServer(androidVersion, runOn, url);
	}

	public void setUpApp(String androidVersion, String runOn, String url, String appPackageName, String seleniumType)
			throws Exception {
		System.out.println("start Appium And Uninstall App");

		// Set The Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appWaitActivity", "2000");
		capabilities.setCapability("appWaitActivity",
				"SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		// uninstallAppPackage(appPackageName);
//			File app = new File(mSATHIApkUrl);
//			capabilities.setCapability("app", app.getAbsolutePath());
		// launchGooglePlayStore();

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, runOn);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidVersion);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		//capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("avdLaunchTimeout", 30000);
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("noResetValue", "false");
		if(appPackageName.contains("grahaksathi.production")) {
			appActivityName = "com.dayalinfosystems.grahaksathi.ui.activity.SplashActivity";
		}
		else if(appPackageName.contains("sathi.msathi")){
			appActivityName = "com.dayalinfosystems.sathi.app.ActivitySplash";
		}
		
		if (seleniumType.equals("WebDriver")) {
			//capabilities = new DesiredCapabilities();
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackageName);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivityName);			
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}

		else if (seleniumType.equals("Selenium Grid")) {

			remoteDriver = new RemoteWebDriver(new URL(url), capabilities);

		}
		// driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
		// capabilities);
		// uninstallAppPackage(appPackageName);
		System.out.println("Before launch Play Store");
		// launchGooglePlayStore();
	}

	/**
	 * Method for start appium server.
	 * 
	 * @param androidVersion
	 * @param runOn
	 * @param url
	 * @throws MalformedURLException
	 */
	public void startAppiumServer(String androidVersion, String runOn, String url) throws MalformedURLException {
		String path = System.getProperty("user.dir");
		platformRunAs = "mobile";
		String connectedDeviceName = runOn;
		String connectedDeviceAndroidVersion = androidVersion;
		String mSathiAppPackageName = "com.dayalinfosystems.sathi.msathi";
		String mSathiAppActivityName = "com.dayalinfosystems.sathi.app.ActivitySplash";
		// Set The Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appWaitActivity", "2000");
		capabilities.setCapability("appWaitActivity",
				"SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		/*
		 * File app = new File(iSATHIApkUrl); capabilities.setCapability("app",
		 * app.getAbsolutePath());
		 */
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, connectedDeviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, connectedDeviceAndroidVersion);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, mSathiAppPackageName);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, mSathiAppActivityName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("udid", connectedDeviceName);
		// driver= (AndroidDriver)new RemoteWebDriver(new
		// URL("http://0.0.0.0:4723/wd/hub"),capabilities);
		remoteDriver = new RemoteWebDriver(new URL(url), capabilities);
		// this.driver = remoteDriver;

		// driver = (AndroidDriver) new RemoteWebDriver(new URL(url), capabilities);
		// driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);
		// driver = new AndroidDriver(new URL(url), capabilities);
		// driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
		// capabilities);

		// driver = new AndroidDriver<MobileElement>(new
		// URL("http://0.0.0.0:4728/wd/hub"), capabilities);
		System.out.println("mobile will launch");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/**
	 * Start appium service and start appium server.
	 * 
	 * @throws Exception
	 */
	// @BeforeClass
	public void setUp() throws Exception {

		// startAppiumService();
		// runAppiumService(4723);
		startAppiumServer();
		// startAppiumAndUninstallApp();
		// appiumService.stop();

		/*
		 * int port = 4723; if(!checkIfServerIsRunnning(port)) {
		 * 
		 * startAppiumService(); startAppiumServer(); } else { appiumService.stop();
		 * startAppiumService(); startAppiumServer();
		 * System.out.println("Appium Server already running on Port - " + port); }
		 */
	}

	/**
	 * @deprecated
	 * @throws InterruptedException
	 */
	@Deprecated
	public void startAppiumService2() throws InterruptedException {
		appiumService = AppiumDriverLocalService.buildDefaultService();
		Thread.sleep(2000);
		appiumService.start();
		Thread.sleep(10000);
		appiumServiceUrl = appiumService.getUrl().toString();
		log.info("Appium Service Address : - " + appiumServiceUrl);

		/*
		 * String Appium_Node_Path="C:/Program Files/nodejs/node.exe"; String
		 * Appium_JS_Path=
		 * "C:/Users/priyankag/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
		 * 
		 * appiumService = AppiumDriverLocalService.buildService(new
		 * AppiumServiceBuilder(). usingPort(4723).usingDriverExecutable(new
		 * File(Appium_Node_Path)). withAppiumJS(new File(Appium_JS_Path)));
		 * appiumService.start();
		 * 
		 * builder = new AppiumServiceBuilder()
		 * .withArgument(GeneralServerFlag.LOG_NO_COLORS)
		 * .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
		 * .withArgument(GeneralServerFlag.LOG_LEVEL, "warn:error:info")
		 * .usingDriverExecutable(new File(Appium_Node_Path)) .withAppiumJS(new
		 * File(Appium_JS_Path));
		 */
		/*
		 * appiumService = AppiumDriverLocalService.buildService(builder);
		 * appiumService.start();
		 */

		/*
		 * 
		 * 
		 * nodeConfigFilePath = "/Users/vikram-anna/.../EMULATOR_Nexus_4_2.json";
		 * driverLocalService = AppiumDriverLocalService .buildService(new
		 * AppiumServiceBuilder() .withAppiumJS(new
		 * File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
		 * .usingDriverExecutable(new File("/usr/local/bin/node")) .usingPort(4823)
		 * .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,"4824")
		 * .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
		 * .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
		 * .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfigFilePath));
		 * 
		 * driverLocalService.start();
		 */
	}

	/**
	 * Method for run appium service
	 * 
	 * @param appiumPort appium port number
	 * @throws InterruptedException
	 */
	public void runAppiumService(int appiumPort) throws InterruptedException {

		// Build parameters for appium server:
		builder = new AppiumServiceBuilder();
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		/*
		 * appiumServiceBuilder.usingPort(appiumPort) .withIPAddress(APPIUM_IP)
		 * .withAppiumJS(new File(getAppiumJsPath()))
		 * .withArgument(GeneralServerFlag.SESSION_OVERRIDE) .withLogFile(new
		 * File(System.getProperty("user.dir") + "/target/resources/appium_server_logs"
		 * + Thread.currentThread().getId()));
		 */ appiumService = AppiumDriverLocalService.buildService(builder);
		appiumService.start();
		Thread.sleep(10000);
		appiumServiceUrl = appiumService.getUrl().toString();
		log.info("Appium Service Address : - " + appiumServiceUrl);
	}

	/**
	 * Start appium server
	 * 
	 * @throws Exception
	 */
	public void startAppiumServer() throws Exception {

		String connectedDeviceName = "emulator-5554 device";
		String connectedDeviceAndroidVersion = "9";
		String mSathiAppPackageName = "com.dayalinfosystems.sathi.msathi";
		String mSathiAppActivityName = "com.dayalinfosystems.sathi.app.ActivitySplash";
		// Set The Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appWaitActivity", "2000");
		capabilities.setCapability("appWaitActivity",
				"SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		/*
		 * File app = new File(iSATHIApkUrl); capabilities.setCapability("app",
		 * app.getAbsolutePath());
		 */
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, connectedDeviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, connectedDeviceAndroidVersion);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, mSathiAppPackageName);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, mSathiAppActivityName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("avdLaunchTimeout", 30000);
		// Instantiate Appium Driver(Constructor To Initialize Driver Object With New
		// Url)
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// driver = (AndroidDriver) new RemoteWebDriver(new URL(appiumServiceUrl),
		// capabilities);
		// driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);

		// driver = new AndroidDriver<MobileElement>(appiumService, capabilities);

	}

	public void startAppiumAndUninstallApp() throws IOException, InterruptedException {
		System.out.println("start Appium And Uninstall App");
		// String mSATHIApkUrl =
		// "/home/priyankag@dayalgroup.hq/Downloads/mSATHI-release.apk";
		String connectedDeviceName = "emulator-5554 device";
		String connectedDeviceAndroidVersion = "9.0";
		String mSathiAppPackageName = "com.dayalinfosystems.sathi.msathi";
		String mSathiAppActivityName = "com.dayalinfosystems.sathi.app.ActivitySplash";
		String QPAppPackageName = "com.dayalinfosystems.quickpurchase";
		String POAppPackageName = "com.dayalinfosystems.purchaseorder";
		// Set The Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appWaitActivity", "2000");
		capabilities.setCapability("appWaitActivity",
				"SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
		// uninstallAppPackage(mSathiAppPackageName);
//			File app = new File(mSATHIApkUrl);
//			capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, connectedDeviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, connectedDeviceAndroidVersion);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, mSathiAppPackageName);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, mSathiAppActivityName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("avdLaunchTimeout", 30000);
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("noResetValue", "false");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// launchGooglePlayStore();

	}

	public void uninstallApp(String appPackageName) {
		try {
			driver.removeApp(appPackageName);
			System.out.println("removed all instance of this app");
		} catch (NullPointerException e) {
			System.out.println("this package is not already installed,please install!");
		}
	}

	public void launchGooglePlayStore() throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiSelector().resourceId(\"com.google.android.apps.nexuslauncher:id/search_container_hotseat\")"))
				.click();
		String testAppUrl = "https://play.google.com/store/apps/details?id=com.dayalinfosystems.sathi.msathi";
		Thread.sleep(1000);
		// Send playstore Url of the app into the search bar
		driver.findElement(MobileBy.className("android.widget.EditText")).sendKeys(testAppUrl);
		driver.pressKeyCode(AndroidKeyCode.ENTER);
		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			// click on the Install button
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.vending:id/0_resource_name_obfuscated\").text(\"Install\")"))
					.click();
			// time for the installation
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// click on accept open button
			driver.findElement(By.id("com.android.vending:id/0_resource_name_obfuscated")).click();
			// driver.findElement(MobileBy.AndroidUIAutomator("new
			// UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.vending:id/0_resource_name_obfuscated\").text(\"Open\")")).click();
		} catch (NoSuchElementException e) {
			// If Install button wasn't found, the app has been already installed.so there's
			// a Open button.
			// click on open button
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.vending:id/0_resource_name_obfuscated\").text(\"Open\")"))
					.click();
		}
	}

	private void uninstallAppPackage(String appPackage) throws IOException, InterruptedException {
		final Process p = Runtime.getRuntime().exec("adb uninstall " + appPackage);

		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;

				try {
					while ((line = input.readLine()) != null)
						System.out.println(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		p.waitFor();
	}

	/**
	 * Getter for WebDriver
	 * 
	 * @return WebDriver
	 */
	public AndroidDriver getDriver() {
		return driver;
	}

	/**
	 * Method for start appium service
	 * 
	 * @throws InterruptedException
	 */
	public void startAppiumService() throws InterruptedException {

		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
		// Start the server with the builder
		appiumService = AppiumDriverLocalService.buildService(builder);
		// appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();
		Thread.sleep(10000);
		appiumServiceUrl = appiumService.getUrl().toString();
		log.info("Appium Service Address : - " + appiumServiceUrl);
	}

	/**
	 * Verify appium service is running.
	 * 
	 * @param port
	 * @return
	 */
	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	/**
	 * Method for stop appium service.
	 */
	// @AfterClass
	public void stopAppium() {
		// appiumService.stop();
		// driver.quit();
	}

}
