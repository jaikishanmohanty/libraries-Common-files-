package authentication;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import browsers.BrowserActions;
import helpers.LoggerHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Login Action class
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class MobileLoginPage {

	WebDriver driver;
	BrowserActions actions;
	// MobileActions objMobileAction;
	Logger log = LoggerHelper.getLogger(MobileLoginPage.class);

	public MobileLoginPage(WebDriver driver) throws InterruptedException {
		this.driver = driver;
		actions = new BrowserActions(driver);
		// objMobileAction = new MobileActions(driver);
		PageFactory.initElements(driver, this);
		// this.goToLoginPage();
	}

	@FindBy(id = "com.dayalinfosystems.isathi:id/btnDashboardSignIn")
	public WebElement dashBoardSignInButton;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/etLoginScrId']")
	public WebElement loginSrcId;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/etLoginScrPw']")
	public WebElement loginSrcPw;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/etLoginScrMobileNo']")
	public WebElement loginSrcMobileNo;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/btnLoginSubmit']")
	public WebElement loginSubmit;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/llHomeUxOk']")
	private WebElement mSathiInstallationOkButton;

	@FindBy(xpath = ".//*[@resource-id='com.dayalinfosystems.isathi:id/ivHomeMenu']")
	private WebElement iSathiHomeMenuButton;

	By mSATHISignInButton = By.xpath(
			"//android.widget.Button[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/btnDashboardSignIn')]");
	By mSATHIRegistrationButton = By.xpath(".//[@resource-id='com.dayalinfosystems.sathi.msathi:id/btnDashboardReg']");
	By mSATHIVersion = By.xpath(".//[@resource-id='com.dayalinfosystems.sathi.msathi:id/tvAppVersionName']");
	By mSATHILoginSrcId = By.xpath(
			"//android.widget.EditText[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/etLoginScrId')]");
	By mSATHILoginSrcPwd = By.xpath(
			"//android.widget.EditText[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/etLoginScrPw')]");
	By mSATHILoginOrganisationCode = By.xpath(
			"//android.widget.EditText[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/etLoginScrCompCode')]");
	By mSATHILogo = By.xpath(".//*[@resource-id='com.dayalinfosystems.sathi.msathi:id/ivLoginLogo']");
	By serverTypeDropdownList = By.xpath("//android.widget.ListView/android.widget.CheckedTextView");
	// By mSATHILoginButton =
	// By.xpath(".//[@resource-id='com.dayalinfosystems.sathi.msathi:id/btnLoginSubmit']");
	By mSATHILoginButton = By.xpath(
			"//android.widget.Button[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/btnLoginSubmit')]");

	String serverTypeDropdown = ".//*[@resource-id='com.dayalinfosystems.sathi.msathi:id/llLayoutServerType']";

	/**
	 * @return the iSathiHomeMenuButton
	 */
	public WebElement getiSathiHomeMenuButton() {
		return iSathiHomeMenuButton;
	}

	/**
	 * @return the mSATHISignInButton
	 */
	public By getmSATHISignInButton() {
		return mSATHISignInButton;
	}

	/**
	 * @return the mSATHIRegistrationButton
	 */
	public By getmSATHIRegistrationButton() {
		return mSATHIRegistrationButton;
	}

	/**
	 * @return the mSATHIVersion
	 */
	public By getmSATHIVersion() {
		return mSATHIVersion;
	}

	/**
	 * @return the mSATHILoginSrcId
	 */
	public By getmSATHILoginSrcId() {
		return mSATHILoginSrcId;
	}

	/**
	 * @return the mSATHILoginSrcPwd
	 */
	public By getmSATHILoginSrcPwd() {
		return mSATHILoginSrcPwd;
	}

	/**
	 * for (int i = 0; i < listOfElements.size(); i++) {
	 * 
	 * @return the mSATHILoginOrganisationCode
	 */
	public By getmSATHILoginOrganisationCode() {
		return mSATHILoginOrganisationCode;
	}

	/**
	 * @return the mSATHILogo
	 */
	public By getmSATHILogo() {
		return mSATHILogo;
	}

	/**
	 * @return the serverTypeDropdown
	 */
	public By getByServerTypeDropdown() {
		return By.xpath(serverTypeDropdown);
	}

	/**
	 * @return the selectFreightTypeDropdown
	 */
	public AndroidElement getServerTypeDropdown() {
		return (AndroidElement) driver.findElement(By.xpath(serverTypeDropdown));
	}

	/**
	 * @return the serverTypeDropdownList
	 */
	public By getServerTypeDropdownList() {
		return serverTypeDropdownList;
	}

	/**
	 * @return the mSATHILoginButton
	 */
	public By getmSATHILoginButton() {
		return mSATHILoginButton;
	}

	public WebElement getDashBoardSignInButton() {
		return dashBoardSignInButton;
	}

	public WebElement getLoginSrcId() {
		return loginSrcId;
	}

	public WebElement getLoginSrcPw() {
		return loginSrcPw;
	}

	public WebElement getLoginSrcMobileNo() {
		return loginSrcMobileNo;
	}

	public WebElement getLoginSubmit() {
		return loginSubmit;
	}

	public WebElement getmSathiInstallationOkButton() {
		return mSathiInstallationOkButton;
	}

	public void goToLoginPage() throws InterruptedException {
		Thread.sleep(8000);
		actions.clickOnElement(getDashBoardSignInButton());
		Thread.sleep(3000);
		actions.applyImplicitWaitToAnElement();
	}

	/**
	 * Method for login.
	 * 
	 * @param username
	 * @param password
	 * @param clientId
	 * @throws Exception
	 */
	public void login(String username, String password, String clientId) throws Exception {
		Thread.sleep(8000);
		log.info("User name :" + username);
		Thread.sleep(3000);
		log.info(driver);
		actions.clearAndSendKeysToElement(driver.findElement(getmSATHILoginSrcId()), username);
		actions.clearAndSendKeysToElement(driver.findElement(getmSATHILoginSrcPwd()), password);
		actions.clearAndSendKeysToElement(driver.findElement(getmSATHILoginOrganisationCode()), clientId);
		((AndroidDriver) driver).hideKeyboard();
		Thread.sleep(2000);
		actions.clickOnElement(loginSubmit);
		Thread.sleep(3000);
		actions.clickOnElement(getmSathiInstallationOkButton());
	}

	/**
	 * Method for login.
	 * 
	 * @param username
	 * @param password
	 * @param clientId
	 * @throws Exception
	 */
	public void mSATHILogin(String username, String password, String clientId, String serverType) throws Exception {
		Thread.sleep(8000);
		driver.findElement(getmSATHISignInButton()).click();
		actions.implicitWaitToAnElement(80);
		for (int mSATHILogo = 1; mSATHILogo <= 10; mSATHILogo++) {
			actions.clickOnElement(driver.findElement(getmSATHILogo()));
		}
		actions.clickAndSendKeysToElement(driver.findElement(getmSATHILoginSrcId()), username);
		actions.clickAndSendKeysToElement(driver.findElement(getmSATHILoginSrcPwd()), password);
		actions.clickAndSendKeysToElement(driver.findElement(getmSATHILoginOrganisationCode()), clientId);
		actions.implicitWaitToAnElement(40);
		selectSpinner(getServerTypeDropdown(), getServerTypeDropdownList(), serverType);
		Thread.sleep(2000);
		actions.clickOnElement(driver.findElement(getmSATHILoginButton()));
		Thread.sleep(3000);
	}

	public void setBranch(String organisationName, String branchName) throws InterruptedException {
		Thread.sleep(3000);
		actions.implicitWaitToAnElement(40);

		actions.clickOnElement(getSetBranchButton());
		actions.implicitWaitToAnElement(40);

		actions.clickOnElement(driver
				.findElement(By.xpath(".//*[@resource-id='com.dayalinfosystems.sathi.msathi:id/tvAppListUserName']")));
		// selectSpinner(getOrganisationDropdown(),
		// getOrganisationDropdownList(),organisationName );

		actions.clickOnElement(getOrganisationDropdown());
		actions.implicitWaitToAnElement(40);
		Thread.sleep(2000);
		// actions.clickOnElement(driver.findElement(By.xpath("//android.widget.TextView[@text
		// = 'Branch']")));

//		actions.clickOnElement(driver.findElement(By.xpath("//android.widget.ListView/android.widget.TextView[@text = 'Shanti Traders']")));
		// actions.clickOnElementWithJS(driver.findElement(By.xpath("//android.widget.FrameLayout/android.widget.ListView/[@resource-id='android:id/text1'
		// and contains(@text,'Shanti Traders')]")));
		// actions.clickOnElementWithJS(driver.findElement(By.xpath("//android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[@index
		// = '1']")));
		Thread.sleep(2000);
		actions.implicitWaitToAnElement(40);

		// selectSpinner(getBranchDropdown(), getBranchDropdownList(),branchName );
		actions.implicitWaitToAnElement(40);

		actions.clickOnElement(getSetBranchButton());
		actions.implicitWaitToAnElement(40);

	}

	public void logOut() throws InterruptedException {

		actions.clickOnElement(driver.findElement(getEditDataBaseIcon()));
		Thread.sleep(3000);
		actions.clickOnElement(driver.findElement(getLogOutText()));
		actions.implicitWaitToAnElement(40);
		actions.clickOnElement(driver.findElement(getYesOptionInLogoutPopup()));
		actions.implicitWaitToAnElement(40);

	}

	By organisationDropdownList = By.xpath("//android.widget.ListView/android.widget.TextView");
	String organisationDropdown = "//android.widget.Spinner[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/lsbSpinOrganisation')]";

	By branchDropdownList = By.xpath("//android.widget.ListView/android.widget.TextView");
	String branchDropdown = ".//*[@resource-id='com.dayalinfosystems.sathi.msathi:id/lsbSpinBranch']";

	By setBranchButton = By.xpath(
			"//android.widget.Button[contains(@resource-id,'com.dayalinfosystems.sathi.msathi:id/lsobBtnSetBranch')]");

	By editDataBaseIcon = By.xpath(".//*[@resource-id='com.dayalinfosystems.sathi.msathi:id/ivEditDatabase']");
	By logOutText = By.xpath("//android.widget.TextView[@text = 'Logout']");

	By yesOptionInLogoutPopup = By.xpath("//android.widget.Button[@text = 'YES']");

	By list = By.xpath(".//*[@resource-id='com.google.android.gms:id/account_name']");

	/**
	 * @return the list
	 */
	public By getList() {
		return list;
	}

	/**
	 * @return the editDataBaseIcon
	 */
	public By getEditDataBaseIcon() {
		return editDataBaseIcon;
	}

	/**
	 * @return the logOutText
	 */
	public By getLogOutText() {
		return logOutText;
	}

	/**
	 * @return the yesOptionInLogoutPopup
	 */
	public By getYesOptionInLogoutPopup() {
		return yesOptionInLogoutPopup;
	}

	/**
	 * @return the organisationDropdown
	 */
	public AndroidElement getSetBranchButton() {
		return (AndroidElement) driver.findElement(setBranchButton);
	}

	/**
	 * @return the organisationDropdownList
	 */
	public By getOrganisationDropdownList() {
		return organisationDropdownList;
	}

	/**
	 * @return the organisationDropdown
	 */
	public AndroidElement getOrganisationDropdown() {
		return (AndroidElement) driver.findElement(By.xpath(organisationDropdown));
	}

	/**
	 * @return the branchDropdownList
	 */
	public By getBranchDropdownList() {
		return branchDropdownList;
	}

	/**
	 * @return the branchDropdown
	 */
	public AndroidElement getBranchDropdown() {
		return (AndroidElement) driver.findElement(By.xpath(branchDropdown));
	}

	/**
	 * For select spinner in mobile app.
	 * 
	 * @param spinnerOption   Xpath of spinner option list.
	 * @param listAllElements Xpath of all list data.
	 * @param spinnerValue    Value name for select from spinner.
	 * @throws InterruptedException
	 */
	public void selectSpinner(AndroidElement spinnerOption, By listAllElements, String spinnerValue)
			throws InterruptedException {

		spinnerOption.click();
		Thread.sleep(5000);
		List<WebElement> listOfElements = driver.findElements(listAllElements);
		for (int i = 0; i < listOfElements.size(); i++) {
			try {
				if (listOfElements.get(i).getText().equals(spinnerValue)) {
					Thread.sleep(3000);
					listOfElements.get(i).click();
					Thread.sleep(4000);
					break;
				}
			} catch (Exception ex) {
				log.info("exception found");
				break;
			}
		}
	}

	/**
	 * Method for logout function.
	 * 
	 * @param null
	 */

	/*
	 * public void logout() { dashboard = new NavigationBar(driver);
	 * actions.waitForVisibility(dashboard.getLogoutHoverTab(), 10);
	 * actions.clickOnElement(dashboard.getLogoutHoverTab());
	 * actions.waitForVisibility(dashboard.getLogoutButton(), 5);
	 * actions.clickOnElement(dashboard.getLogoutButton()); }
	 */
	public void grahakSATHILogin(String username, String passWord, String serverType) throws Exception {
		// click on app logo to select server type

		Thread.sleep(8000);
		for (int Logo = 1; Logo <= 10; Logo++) {
			actions.implicitWaitToAnElement(80);
			actions.clickOnElement(driver.findElement(getGrahakSATHIapp_imgLogo()));
		}
		// select server type
		if ((!serverType.equals(""))) {
			actions.implicitWaitToAnElement(40);
			selectSpinner(getServerTypeDropdown__grahakSATHI(), getServerTypeDropdownList_grahakSATHI(), serverType);
			Thread.sleep(2000);
			// click on "sign in with other" button
			actions.clickOnElement(driver.findElement(getBtnSignInWithOther()));
			Thread.sleep(1000);
			// fill user credentials
			if ((!username.equals(""))) {
				actions.clickAndSendKeysToElement(driver.findElement(getLogin_username()), username);
				if ((!passWord.equals(""))) {
					actions.clickAndSendKeysToElement(driver.findElement(getLogin_password()), passWord);
					actions.clickOnElement(driver.findElement(getBtn_login()));
				} else {
					System.out.println("password should be given");
				}
			} else {
				System.out.println("username should be given");
			}

		} else {
			System.out.println("server type should be given");
		}
	}

	public void grahakSATHISignInWithGmail(String serverType, String gmailId) throws Exception {
		// click on app logo to select server type
		Thread.sleep(8000);
		for (int Logo = 1; Logo <= 10; Logo++) {
			actions.implicitWaitToAnElement(80);
			actions.clickOnElement(driver.findElement(getGrahakSATHIapp_imgLogo()));
		}
		// select server type
		if ((!serverType.equals(""))) {
			actions.implicitWaitToAnElement(40);
			selectSpinner(getServerTypeDropdown(), getServerTypeDropdownList(), serverType);
			Thread.sleep(2000);
			// click on sign in button
			actions.clickOnElement(driver.findElement(getBtnSignInFromGoogle()));
			Thread.sleep(4000);
			if (actions.isElementPresent(driver.findElements(getGrahakSATHIapp_icon_inpopup()))) {
				if ((!gmailId.equals(""))) {
					actions.selectDesiredOptionFromList(getList(), gmailId);
				} else {
					System.out.println("gmail id should be given");
				}
			}

			else {
				// softAssert.assertTrue(actions.isElementPresent(driver.findElements(getGrahakSATHIapp_icon_inpopup())),
				// "There is no gmail account added,Please add any account.");
				System.out.println("There is no google account added.");
			}
		} else {
			System.out.println("server type should be given");
		}
	}

	public void grahakSATHISignInWithFacebook(String serverType, String gmailId, String fbPassword) throws Exception {
		// click on app logo to select server type
		Thread.sleep(8000);
		for (int Logo = 1; Logo <= 10; Logo++) {
			actions.implicitWaitToAnElement(80);
			actions.clickOnElement(driver.findElement(getGrahakSATHIapp_imgLogo()));
		}
		// select server type
		if ((!serverType.equals(""))) {
			actions.implicitWaitToAnElement(40);
			selectSpinner(getServerTypeDropdown(), getServerTypeDropdownList(), serverType);
			Thread.sleep(2000);
			// click on continue with facebook button
			actions.clickOnElement(driver.findElement(getBtn_login_ContinueWithFacebook()));
			Thread.sleep(1000);
			if (actions.isElementPresent(driver.findElements(getFb_loginPage_header()))) {
				if ((!gmailId.equals(""))) {
					System.out.println("111111" + gmailId);
					actions.clearAndSendKeysToElement(driver.findElement(getFb_login_email_or_mobileNumber()), gmailId);

					if ((!fbPassword.equals(""))) {
						actions.clearAndSendKeysToElement(driver.findElement(getFb_login_password()), fbPassword);
						actions.clickOnElement(driver.findElement(getFb_login_button()));
					} else {
						System.out.println("fbPassword id should be given");
					}
				} else {
					System.out.println("gmail id should be given");
				}
			}

			else {
				// softAssert.assertTrue(actions.isElementPresent(driver.findElements(getFb_loginPage_header())),
				// "login page of facebook is not showing.");
				System.out.println("login page of facebook is not showing.");
			}
		} else {
			System.out.println("server type should be given");
		}

	}

	// permission popup
	By permission_message = By.xpath(".//*[@resource-id='com.android.packageinstaller:id/permission_message']");
	By permission_allow_button = By
			.xpath(".//*[@resource-id='com.android.packageinstaller:id/permission_allow_button']");
	By permission_deny_button = By.xpath(".//*[@resource-id='com.android.packageinstaller:id/permission_deny_button']");

	By btn_login_ContinueWithFacebook = By
			.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/login_facebook_button']");
	// facebook login page open
	By fb_loginPage_header = By.xpath(".//*[@resource-id='header']");
	By fb_login_email_or_mobileNumber = By.xpath(".//*[@resource-id='m_login_email']");
	By fb_login_password = By.xpath(".//*[@resource-id='m_login_password']");
	By fb_login_button = By.xpath(".//*[@resource-id='android.widget.Button']");
	By fb_continue_button = By.xpath(".//*[@resource-id='u_0_5']");

	// Sign in
	By btnSignInFromGoogle = By.xpath("//android.widget.Button[contains(@text,'Sign in')]");
	By grahakSATHIapp_icon_inpopup = By.xpath(".//*[@resource-id='com.google.android.gms:id/app_icon']");

	public By getGmailAccountId(String emailId) {

		By GmailAccountId = By.xpath(
				"//android.widget.LinearLayout[contains(@resource-id,'com.google.android.gms:id/account_name')][@text='"
						+ emailId + "']");
		return GmailAccountId;

	}

	// when there is no email account added.
	By google_signIn_page = By.xpath(".//*[@resource-id='com.google.android.gms:id/suc_layout_status']");
	By email_or_phone_field_OnSignInPage = By.xpath(".//*[@resource-id='identifierId']");

	By grahakSATHIapp_imgLogo = By.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/imgLogo']");

	// Sign in with other
	By btnSignInWithOther = By.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/btnLogin']");
	By login_username = By
			.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/editText_login_username']");
	By login_password = By
			.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/editText_login_password']");
	By btn_login = By.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/btn_login']");
	// select servre type
	By serverTypeDropdownList_grahakSATHI = By.xpath("//android.widget.ListView/android.widget.CheckedTextView");
	// String serverTypeDropdown__grahakSATHI =
	// ".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/llSpinServerType']";
	By serverTypeDropdown__grahakSATHI = By
			.xpath(".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/llSpinServerType']");

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @return the actions
	 */
	public BrowserActions getActions() {
		return actions;
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}

	/**
	 * @return the permission_message
	 */
	public By getPermission_message() {
		return permission_message;
	}

	/**
	 * @return the permission_allow_button
	 */
	public By getPermission_allow_button() {
		return permission_allow_button;
	}

	/**
	 * @return the permission_deny_button
	 */
	public By getPermission_deny_button() {
		return permission_deny_button;
	}

	/**
	 * @return the btn_login_ContinueWithFacebook
	 */
	public By getBtn_login_ContinueWithFacebook() {
		return btn_login_ContinueWithFacebook;
	}

	/**
	 * @return the fb_loginPage_header
	 */
	public By getFb_loginPage_header() {
		return fb_loginPage_header;
	}

	/**
	 * @return the fb_login_email_or_mobileNumber
	 */
	public By getFb_login_email_or_mobileNumber() {
		return fb_login_email_or_mobileNumber;
	}

	/**
	 * @return the fb_login_password
	 */
	public By getFb_login_password() {
		return fb_login_password;
	}

	/**
	 * @return the fb_login_button
	 */
	public By getFb_login_button() {
		return fb_login_button;
	}

	/**
	 * @return the fb_continue_button
	 */
	public By getFb_continue_button() {
		return fb_continue_button;
	}

	/**
	 * @return the btnSignInFromGoogle
	 */
	public By getBtnSignInFromGoogle() {
		return btnSignInFromGoogle;
	}

	/**
	 * @return the grahakSATHIapp_icon_inpopup
	 */
	public By getGrahakSATHIapp_icon_inpopup() {
		return grahakSATHIapp_icon_inpopup;
	}

	/**
	 * @return the google_signIn_page
	 */
	public By getGoogle_signIn_page() {
		return google_signIn_page;
	}

	/**
	 * @return the email_or_phone_field_OnSignInPage
	 */
	public By getEmail_or_phone_field_OnSignInPage() {
		return email_or_phone_field_OnSignInPage;
	}

	/**
	 * @return the grahakSATHIapp_imgLogo
	 */
	public By getGrahakSATHIapp_imgLogo() {
		return grahakSATHIapp_imgLogo;
	}

	/**
	 * @return the btnSignInWithOther
	 */
	public By getBtnSignInWithOther() {
		return btnSignInWithOther;
	}

	/**
	 * @return the login_username
	 */
	public By getLogin_username() {
		return login_username;
	}

	/**
	 * @return the login_password
	 */
	public By getLogin_password() {
		return login_password;
	}

	/**
	 * @return the btn_login
	 */
	public By getBtn_login() {
		return btn_login;
	}

	/**
	 * @return the serverTypeDropdownList_grahakSATHI
	 */
	public By getServerTypeDropdownList_grahakSATHI() {
		return serverTypeDropdownList_grahakSATHI;
	}

	/**
	 * @return the serverTypeDropdown__grahakSATHI
	 */
	public AndroidElement getServerTypeDropdown__grahakSATHI() {
		return (AndroidElement) driver.findElement(serverTypeDropdown__grahakSATHI);
	}

	// Logout grahaksathi

	By logoutOptionInDrawer = By.xpath("//android.widget.CheckedTextView[@text='Logout']");
	// permission popup of logout
	By logout_message = By.xpath("//android.widget.TextView[@text='Do you want to logout?']");
	By logout_message_yes = By.xpath("//android.widget.Button[@text='YES']");
	By logout_message_no = By.xpath("//android.widget.Button[@text='NO']");
	By openDrawer = By.xpath(
			".//*[@resource-id='com.dayalinfosystems.grahaksathi.production:id/toolbar_main']/android.widget.ImageButton[@index='0']");

	/**
	 * @return the logoutOptionInDrawer
	 */
	public By getLogoutOptionInDrawer() {
		return logoutOptionInDrawer;
	}

	/**
	 * @return the logout_message
	 */
	public By getLogout_message() {
		return logout_message;
	}

	/**
	 * @return the logout_message_yes
	 */
	public By getLogout_message_yes() {
		return logout_message_yes;
	}

	/**
	 * @return the logout_message_no
	 */
	public By getLogout_message_no() {
		return logout_message_no;
	}

	/**
	 * @return the openDrawer
	 */
	public By getOpenDrawer() {
		return openDrawer;
	}

	public void logoutActivityGrahakSATHI() throws GeneralSecurityException, IOException, InterruptedException {
		System.out.println("logoutfunction");
		Thread.sleep(2000);
		actions.clickOnElement(driver.findElement(getOpenDrawer()));
		if (actions.isElementPresent(driver.findElements(getLogoutOptionInDrawer()))) {
			Thread.sleep(1000);
			actions.clickOnElement(driver.findElement(getLogoutOptionInDrawer()));
			if (actions.isElementPresent(driver.findElements(getLogout_message()))) {
				Thread.sleep(1000);
				actions.clickOnElement(driver.findElement(getLogout_message_yes()));
			}
		}
	}

}
