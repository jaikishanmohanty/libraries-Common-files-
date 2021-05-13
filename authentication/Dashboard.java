package authentication;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * Page object Class of Dashboard.
 * @author priyankag@dayalgroup.hq
 */
public class Dashboard {

	WebDriver driver;
	@FindBy(xpath = ".//*[@id='myProfileDiv']")
	private WebElement logoutIcon;
	@FindBy(xpath = ".//*[@id='logout']")
	private WebElement logoutLink;
	@FindBy(xpath = ".//*[@id='login-form']/div[2]/p")
	private String xpath_invalid_pwd;
	@FindBy(xpath = ".//*[@id='login-form']/div[3]/p")
	private String xpath_invalid_un;
	@FindBy(xpath = ".//*[@id='m_1_app']")
	private WebElement appIcon;
	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'HCMS')]")
	private WebElement hcmsLink;
	@FindBy(xpath = ".//*[@id='m_1_lms']")
	private WebElement lmsIcon;
	@FindBy(xpath = "//a[@id='m_1_lms']/following-sibling::ul/li/a[contains(.,'Leave Type')]")
	private WebElement leaveType;
	@FindBy(xpath = ".//*[@id='create-button']")
	private WebElement createIcon;
	@FindBy(xpath = "(//div[@class='col-12'])")
	private WebElement pageTitle;
	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Sales')]")
	private WebElement salesLink;
	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Order')]")
	private WebElement salesOrderLink;
	@FindBy(xpath = "//a[@id='m_1_sales']")
	private WebElement salesIcon;
	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Order')]")
	private WebElement salesOrder;
	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Dispatch Challans')]")
	private WebElement dispatchChallans;
	@FindBy(xpath = ".//*[@id='create-button']")
	private WebElement createLeaveIcon;
	@FindBy(xpath = "//a[@id='m_1_lms']/following-sibling::ul/li/a[contains(.,'Leave Policy')]")
	private WebElement leavePolicy;
	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Invoice')]")
	private WebElement salesInvoice;
	By salesIconForWait = By.xpath("//a[@id='m_1_sales']");
	By appIconForWait = By.xpath(".//*[@id='m_1_app']");
	@FindBy(xpath = "//a[@id='m_1_works']")
	private WebElement worksIcon;
	By worksIconForWait = By.xpath(".//*[@id='m_1_works']");
	/*@FindBy(xpath = "//a[@id='m_1_works']/following-sibling::ul/li/a[contains(.,'Godown 1')]")
	private WebElement godown1BranchLink;
	@FindBy(xpath = "//a[@id='m_1_works']/following-sibling::ul/li/a[contains(.,'Transys Head Office')]")
	private WebElement transysHeadOfficeBranchLink;
	@FindBy(xpath = "//a[@id='m_1_works']/following-sibling::ul/li/a[contains(.,'Works')]")
	private WebElement worksBranchLink;*/
	@FindBy(xpath = ".//*[@id='w9']/li[1]/a")
	private WebElement godown1BranchLink;
	@FindBy(xpath = ".//*[@id='w9']/li[4]/a")
	private WebElement transysHeadOfficeBranchLink;
	@FindBy(xpath ="//*[@id='w9']/li[5]/a")
	private WebElement worksBranchLink;
	@FindBy(xpath = ".//*[@id='w9']/li[2]/a")
	private WebElement transysAgroBranchLink;
	@FindBy(xpath = "//a[@id='m_1_--']")
	private WebElement emptyBranchIcon;
	By emptyBranchIconForWait = By.xpath(".//*[@id='m_1_--']");
	@FindBy(xpath = "//a[contains(@id, 'm_1_')]/following-sibling::ul/li/a[contains(.,'Works')]")
	private WebElement worksBranchLinkWithoutRefrence;
	By worksBranchLinkWithoutRefrenceForWait = By.xpath("//a[contains(@id, 'm_1_')]/following-sibling::ul/li/a[contains(.,'Works')]");
	@FindBy(xpath ="//*[@id='navigationMenuItems']/li[4]") 
	private WebElement selectBranchIcon;

	By selectBranchIconForWait = By.xpath("//*[@id='navigationMenuItems']/li[4]");

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Receive Payment')]")
	private WebElement receivePayment;

	public WebElement getReceivePayment() {
		return receivePayment;
	}
	public WebElement getTransysAgroBranchLink() {
		return transysAgroBranchLink;
	}
	public WebElement getEmptyBranchIcon() {
		return emptyBranchIcon;
	}
	public By getEmptyBranchIconForWait() {
		return emptyBranchIconForWait;
	}
	public WebElement getWorksBranchLinkWithoutRefrence() {
		return worksBranchLinkWithoutRefrence;
	}
	public By getWorksBranchLinkWithoutRefrenceForWait() {
		return worksBranchLinkWithoutRefrenceForWait;
	}
	public WebElement getSelectBranchIcon() {
		return selectBranchIcon;
	}
	public By getSelectBranchIconForWait() {
		return selectBranchIconForWait;
	}
	public WebElement getWorksIcon() {
		return worksIcon;
	}
	public By getWorksIconForWait() {
		return worksIconForWait;
	}
	public WebElement getGodown1BranchLink() {
		return godown1BranchLink;
	}
	public WebElement getTransysHeadOfficeBranchLink() {
		return transysHeadOfficeBranchLink;
	}
	public WebElement getWorksBranchLink() {
		return worksBranchLink;
	}
	public By getAppIconForWait() {
		return appIconForWait;
	}
	public By getSalesIconForWait() {
		return salesIconForWait;
	}
	public WebElement getSalesInvoice() {
		return salesInvoice;
	}
	public WebElement getSalesOrderLink() {
		return salesOrderLink;
	}
	public WebElement getCreateLeaveIcon() {
		return createLeaveIcon;
	}

	public WebElement getDispatchChallans() {
		return dispatchChallans;
	}

	public WebElement getSalesLink() {
		return salesLink;
	}

	public WebElement getSalesIcon() {
		return salesIcon;
	}

	public WebElement getSalesOrder() {
		return salesOrder;
	}

	public WebElement getLeavePolicy() {
		return leavePolicy;
	}

	// @FindBy(xpath = "//div[@class='col-12' and contains(text(), 'Create Leave
	// Type')]") private WebElement pageTitle;

	// By leaveType = By.xpath(".//*[@id='w9']/li[1]/a");



	public WebElement getPageTitle() {
		return pageTitle;
	}


	public Dashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateIcon() {
		return createIcon;
	}
	public void setPageTitle(WebElement pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setCreateIcon(WebElement createIcon) {
		this.createIcon = createIcon;
	}


	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public void setLogoutIcon(WebElement logoutIcon) {
		this.logoutIcon = logoutIcon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public void setLogoutLink(WebElement logoutLink) {
		this.logoutLink = logoutLink;
	}

	public String getXpath_invalid_pwd() {
		return xpath_invalid_pwd;
	}

	public void setXpath_invalid_pwd(String xpath_invalid_pwd) {
		this.xpath_invalid_pwd = xpath_invalid_pwd;
	}

	public String getXpath_invalid_un() {
		return xpath_invalid_un;
	}

	public void setXpath_invalid_un(String xpath_invalid_un) {
		this.xpath_invalid_un = xpath_invalid_un;
	}

	public WebElement getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(WebElement appIcon) {
		this.appIcon = appIcon;
	}

	public WebElement getHcmsLink() {
		return hcmsLink;
	}

	public void setHcmsLink(WebElement hcmsLink) {
		this.hcmsLink = hcmsLink;
	}

	public WebElement getLmsIcon() {
		return lmsIcon;
	}

	public void setLmsIcon(WebElement lmsIcon) {
		this.lmsIcon = lmsIcon;
	}

	public WebElement getLeaveType() throws Exception {
		Thread.sleep(3000);
		return leaveType;
	}

	public void setLeaveType(WebElement leaveType) {
		this.leaveType = leaveType;
	}
	/** Method for navigation MenuItems.
	 * @param null
	 * @throws Exception
	 */
	public void appModule() throws Exception {
		System.out.println("app module in home page");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement lmsIcon1 = driver.findElement(By.xpath(".//*[@id='navigationMenuItems']/li[3]/a"));
		lmsIcon1.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		leaveType.click();
		
	}
}
