package authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login Page class.
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class LoginPage {
	WebDriver driver;

	@FindBy(xpath = ".//*[@id='loginform-username']")
	private WebElement usernameTextBox;
	@FindBy(xpath = ".//*[@id='loginform-password']")
	private WebElement passwordTextBox;
//	@FindBy(xpath = ".//*[@id='loginform-clientid']")private WebElement clientIdTextBox;
	@FindBy(xpath = ".//*[@id='login-button']")
	private WebElement loginButton;

	By dropdownOption = By.id("select2-loginform-clientid-container");
	By dropdownAllElements = By.xpath(".//*[@id='select2-loginform-clientid-results']/li");
	By byUserNameError = By.xpath(".//*[@id='login-form']/div[1]/p");
	By byPasswordError = By.xpath(".//*[@id='login-form']/div[2]/p");
	By byLicenseError = By.xpath(".//*[@id='login-form']/div[3]/p");

	By organisationCode = By.xpath("//input[@id ='loginform-clientid']");

//	[@type='submit' OR @name='btnReset']
//			
//			[contains(@id,'lst-ib')]

	By userNameTextField = By.xpath(
			"//input[@id='loginform-username']/following-sibling::p[contains(.,'Invalid') or contains(.,'cannot be blank.')]");
	By passwordTextField = By.xpath(
			"//input[@id='loginform-password']/following-sibling::p[contains(.,'Invalid') or contains(.,'cannot be blank.')]");
	By organisationCodeTextField = By.xpath(
			"//input[@id='loginform-clientid']/following-sibling::p[contains(.,'Invalid') or contains(.,'cannot be blank.')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameTextBox() {
		return usernameTextBox;
	}

	public WebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public By getDropdownOption() {
		return dropdownOption;
	}

	public By getDropdownAllElements() {
		return dropdownAllElements;
	}

	public By getByUserNameError() {
		return byUserNameError;
	}

	public By getByPasswordError() {
		return byPasswordError;
	}

	public By getByLicenseError() {
		return byLicenseError;
	}

	// priyankag

	public String str;
	WebElement titleInvalidPassord;
	WebElement titleInvalidUsernameMobileno;

	@FindBy(xpath = ".//*[@id='login-form']/div[2]/p")
	private WebElement errorMessagePasswordFieldLabel;
	@FindBy(xpath = ".//*[@id='login-form']/div[3]/p")
	private WebElement errorMessageMobileFieldLabel;

	By mobileNodropdown = By.id("select2-loginform-clientid-container");
	By mobilenoTextBox = By.xpath(".//*[@id='select2-loginform-clientid-results']/li");

	public By getMobileNodropdown() {
		return mobileNodropdown;
	}

	public void setMobileNodropdown(By mobileNodropdown) {
		this.mobileNodropdown = mobileNodropdown;
	}

	public By getMobilenoTextBox() {
		return mobilenoTextBox;
	}

	public void setMobilenoTextBox(By mobilenoTextBox) {
		this.mobilenoTextBox = mobilenoTextBox;
	}

	public WebElement getIdunTextBox() {
		return usernameTextBox;
	}

	public void setPasswordTextBox(WebElement passwordTextBox) {
		this.passwordTextBox = passwordTextBox;
	}

	public void setLoginButton(WebElement loginButton) {
		this.loginButton = loginButton;
	}

	public WebElement getXpath_invalid_pwd() {
		return errorMessagePasswordFieldLabel;
	}

	public void setXpath_invalid_pwd(WebElement xpath_invalid_pwd) {
		this.errorMessagePasswordFieldLabel = xpath_invalid_pwd;
	}

	public WebElement getXpath_invalid_un() {
		return errorMessageMobileFieldLabel;
	}

	public void setXpath_invalid_un(WebElement xpath_invalid_un) {
		this.errorMessageMobileFieldLabel = xpath_invalid_un;
	}

	public void clickOnLoginButton() throws Exception {
		Boolean lbtn = loginButton.isEnabled();
		if (lbtn) {
			loginButton.click();
		}
	}
	// String url = "http://10.1.10.110:89/Saarthi/index.php/site/login";
	/*
	 * public String getUrl() throws IOException, GeneralSecurityException {
	 * DataProviders dataProvider = new DataProviders(); return
	 * dataProvider.getSathiUrl(); }
	 */

	/**
	 * @return the organisationCode
	 */
	public By getOrganisationCode() {
		return organisationCode;
	}

	/**
	 * @return the titleInvalidPassord
	 */
	public WebElement getTitleInvalidPassord() {
		return titleInvalidPassord;
	}

	/**
	 * @return the titleInvalidUsernameMobileno
	 */
	public WebElement getTitleInvalidUsernameMobileno() {
		return titleInvalidUsernameMobileno;
	}

	/**
	 * @return the errorMessagePasswordFieldLabel
	 */
	public WebElement getErrorMessagePasswordFieldLabel() {
		return errorMessagePasswordFieldLabel;
	}

	/**
	 * @return the errorMessageMobileFieldLabel
	 */
	public WebElement getErrorMessageMobileFieldLabel() {
		return errorMessageMobileFieldLabel;
	}

	/**
	 * @return the userNameTextField
	 */
	public By getUserNameTextField() {
		return userNameTextField;
	}

	/**
	 * @return the passwordTextField
	 */
	public By getPasswordTextField() {
		return passwordTextField;
	}

	/**
	 * @return the organisationCodeTextField
	 */
	public By getOrganisationCodeTextField() {
		return organisationCodeTextField;
	}

	/*
	 * public String getUrl() { return url; }
	 */

}
