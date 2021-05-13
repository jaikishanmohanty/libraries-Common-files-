package authentication;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import browsers.BaseTest;
import browsers.BrowserActions;
import helpers.LoggerHelper;
import utilities.ExcelUtils;

/**
 * Login Action class.
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class LoginAction extends BaseTest {
	WebDriver driver;
	BrowserActions actions;
	LoginPage loginPage;
	NavigationBar dashboard;
	String resultMessage;
	ExcelUtils utils;
	Object[][] resultData;
	Logger log = LoggerHelper.getLogger(LoginAction.class);
	String resultFilePath = System.getProperty("user.dir") + "/resources/file/Results.xlsx";

	/**
	 * Login Action
	 * 
	 * @param driver
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public LoginAction(WebDriver driver) throws IOException, GeneralSecurityException {
		this.driver = driver;
		loginPage = new LoginPage(driver);
		actions = new BrowserActions(driver);
	}

	/**
	 * Go to login Page
	 * 
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public void goToLoginPage() throws IOException, GeneralSecurityException {
		actions.openPage(getSathiUrl());
		actions.applyImplicitWaitToAnElement();
	}

	/**
	 * Method for login.
	 * 
	 * @param username the user name for login.
	 * @param password the password for login.
	 * @param clientId the client Id for login.
	 * @throws Exception
	 */
	public void login(String username, String password, String clientId) throws Exception {
		this.goToLoginPage();
		actions.waitForVisibility(loginPage.getUsernameTextBox(), 10);
		actions.clearAndSendKeysToElement(loginPage.getUsernameTextBox(), username);
		actions.clearAndSendKeysToElement(loginPage.getPasswordTextBox(), password);

		if (actions.isElementPresent(driver.findElements(loginPage.getOrganisationCode()))) {
			actions.clearAndSendKeysToElement(driver.findElement(loginPage.getOrganisationCode()), clientId);
		} else if (actions.isElementPresent(driver.findElements(loginPage.getDropdownOption()))) {
			actions.selectCustomizeDropDownValue(clientId, loginPage.getDropdownOption(),
					loginPage.getDropdownAllElements());
		}

		actions.clickOnElement(loginPage.getLoginButton());
	}

	/**
	 * Method for login in SPARK.
	 * 
	 * @param url      the url for login.
	 * @param username the username for login.
	 * @param password the password for login.
	 * @param clientId the client Id for login.
	 * @throws Exception
	 */
	public void loginSpark(String url, String username, String password, String clientId) throws Exception {
		actions.openPage(url);
		Thread.sleep(3000);
		actions.waitForVisibility(loginPage.getUsernameTextBox(), 10);
		actions.clearAndSendKeysToElement(loginPage.getUsernameTextBox(), username);
		actions.clearAndSendKeysToElement(loginPage.getPasswordTextBox(), password);

		try {
			if (actions.isElementPresent(driver.findElements(loginPage.getOrganisationCode()))) {
				actions.clearAndSendKeysToElement(driver.findElement(loginPage.getOrganisationCode()), clientId);
			} else if (actions.isElementPresent(driver.findElements(loginPage.getDropdownOption()))) {
				actions.selectCustomizeDropDownValue(clientId, loginPage.getDropdownOption(),
						loginPage.getDropdownAllElements());
			}
		} catch (InvalidElementStateException e) {
			System.out.println("Exception ");

		}

		finally {
			actions.clickOnElement(loginPage.getLoginButton());

		}

		Assert.assertFalse(actions.isElementPresent(driver.findElements(loginPage.getUserNameTextField())),
				"Username Field is mandatory");

		Assert.assertFalse(actions.isElementPresent(driver.findElements(loginPage.getPasswordTextField())),
				"Password Field is mandatory");

		Assert.assertFalse(actions.isElementPresent(driver.findElements(loginPage.getOrganisationCodeTextField())),
				"Organisation Code Field is mandatory");

	}

	/**
	 * Method for logout function.
	 * 
	 * @param null
	 */

	public void logout() {
		dashboard = new NavigationBar(driver);
		actions.waitForVisibility(dashboard.getLogoutHoverTab(), 10);
		actions.clickOnElement(dashboard.getLogoutHoverTab());
		actions.waitForVisibility(dashboard.getLogoutButton(), 5);
		actions.clickOnElement(dashboard.getLogoutButton());
	}

	/**
	 * Method for multiple user login.
	 * 
	 * @param username the user name for login.
	 * @param password the password for login.
	 * @param clientId the client Id for login.
	 * @throws Exception
	 */
	public void testMultipleUserLogin(String username, String password, String clientId) throws Exception {
		dashboard = new NavigationBar(driver);
		this.login(username, password, clientId);
		try {
			this.logout();
			resultMessage = "Login Successful.";

		} catch (Exception e) {
			resultMessage = this.loginExceptionHandling();
		}
		utils = new ExcelUtils();
		utils.writeFile(new Object[][] { { username, resultMessage } }, resultFilePath);
		log.info(username + ": " + resultMessage);
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.info(methodName + " - " + username + " - " + resultMessage);
	}

	/**
	 * Method for handling exception in login functionality.
	 * 
	 * @param null
	 * @return error message
	 */

	public String loginExceptionHandling() {
		String getErrorMessage;
		if (driver.findElement(loginPage.getByUserNameError()).isDisplayed()) {
			getErrorMessage = driver.findElement(loginPage.getByUserNameError()).getText();
		} else if (driver.findElement(loginPage.getByPasswordError()).isDisplayed()) {
			getErrorMessage = driver.findElement(loginPage.getByPasswordError()).getText();
		} else if (driver.findElement(loginPage.getByLicenseError()).isDisplayed()) {
			getErrorMessage = driver.findElement(loginPage.getByLicenseError()).getText();
		} else {
			getErrorMessage = "No element found";
		}
		return getErrorMessage;
	}
}
