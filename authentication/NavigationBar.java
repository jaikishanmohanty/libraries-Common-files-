package authentication;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import browsers.BrowserActions;
import helpers.LoggerHelper;

/**
 * Page object Class of element present during navigation from dashboard to any app.
 * @author priyankag@dayalgroup.hq
 *
 */
public class NavigationBar {
	WebDriver driver;

	BrowserActions actions;
	Logger log = LoggerHelper.getLogger(NavigationBar.class);

	By pageHeaderElement = By.xpath("//nav[@aria-label='breadcrumb']//ol[@class='breadcrumb']/li[@class='breadcrumb-item active']");

	By pageException = By.xpath("//div[@class='tools']//following-sibling::h1/span");

	By createModalButton = By.xpath("//a[@id ='modalButton']/i");

	//By createButton = By.xpath("//div[@class='pull-right top_btn']/a/i[@class='fa fa-plus']");

	By createButton = By.xpath("//div[@class='pull-right top_btn']/a/i[contains(@class,'fa fa-plus')]");


	By createIconBy = By.xpath(".//*[@id='create-button']");

	@FindBy(xpath = ".//*[@id='create-button']")
	private WebElement createIcon;

	@FindBy(xpath = "//div[@class='tools']/following-sibling::h1/span[contains(.,'Database Exception')]")
	private List<WebElement> databaseException;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div/div[1]/div/div[1]/div/div[1]/span")
	private WebElement successMsg;

	@FindBy(xpath = ".//*[@id='myProfileDiv']")
	private WebElement logoutHoverTab;

	@FindBy(xpath = ".//*[@id='logout']")
	private WebElement logoutButton;

	@FindBy(xpath = ".//*[@id='myProfileDiv']")
	private WebElement logoutIcon;

	@FindBy(xpath = ".//*[@id='logout']")
	private  WebElement logoutLink;

	@FindBy(xpath = ".//a[@id='m_1_app']")
	private WebElement appLink;

	@FindBy(xpath = ".//*[@id='m_1_app']")
	private WebElement appIcon;

	@FindBy(xpath = ".//a[contains(.,'HCMS')]")
	private WebElement hcmsAppLink;

	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'HCMS')]")
	private WebElement hcmsLink;

	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Purchase')]")
	private WebElement purchaseAppLink;

	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Accounting')]")
	private WebElement accountingLink;


	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Sales')]")
	private WebElement salesLink;

	//Purchase app links

	@FindBy(id = "m_1_purchase")
	private WebElement purchaseNavHome;   

	@FindBy(xpath = ".//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Purchase Order')]")
	private WebElement purchaseOrderAppLink;

	@FindBy(xpath = ".//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Material Receive')]")
	private WebElement materialReceiveAppLink;

	@FindBy(xpath = ".//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Purchase Invoice')]")
	private WebElement purchaseInvoiceAppLink;

	@FindBy(xpath = ".//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Make Payment')]")
	private WebElement makePaymentAppLink;

	// priyankag 
	@FindBy(xpath = ".//*[@id='m_1_lms']")
	private WebElement lmsIcon;

	@FindBy(xpath = "//a[@id='m_1_lms']/following-sibling::ul/li/a[contains(.,'Leave Type')]")
	private WebElement leaveType;

	@FindBy(xpath = "//a[@id='m_1_lms']/following-sibling::ul/li/a[contains(.,'Leave Policy')]")
	private WebElement leavePolicy;

	//Sales app links
	/*@FindBy(xpath = "//*[@id='m_1_sales']")
	private WebElement salesIcon;*/

	@FindBy(xpath = "//ul[@id='navigationMenuItems']//li/a[@id='m_1_sales']")
	private WebElement salesIcon;

	//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Retail')]

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Order')]")
	private WebElement salesOrderLink;

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Order')]")
	private WebElement salesOrder;

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Dispatch Challans')]")
	private WebElement dispatchChallans;

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Sales Invoice')]")
	private WebElement salesInvoice;

	@FindBy(xpath = "//a[@id='m_1_sales']/following-sibling::ul/li/a[contains(.,'Receive Payment')]")
	private WebElement receivePayment;

	By salesIconForWait = By.xpath("//*[@id='navigationMenuItems']//li/a[@id='m_1_sales']");

	By appIconForWait = By.xpath(".//*[@id='m_1_app']");

	@FindBy(xpath = "//a[@id='m_1_works']")
	private WebElement worksIcon;

	By worksIconForWait = By.xpath(".//*[@id='m_1_works']");

	@FindBy(xpath = "//a[contains(@id, 'm_1_')/following-sibling::ul/li/a[contains(.,'Godown 1')]")
	private WebElement godown1BranchLink;

	@FindBy(xpath = "//a[contains(@id, 'm_1_')/following-sibling::ul/li/a[contains(.,'Transys Head Office')]")
	private WebElement transysHeadOfficeBranchLink;

	@FindBy(xpath = "//a[contains(@id, 'm_1_')/following-sibling::ul/li/a[contains(.,'Works')]")
	private WebElement worksBranchLink;

	@FindBy(xpath = "//a[contains(@id, 'm_1_')/following-sibling::ul/li/a[contains(.,'Transys Agro')]")
	private WebElement transysAgroBranchLink;

	@FindBy(xpath = "//a[@id='m_1_--']")
	private WebElement emptyBranchIcon;

	By emptyBranchIconForWait = By.xpath(".//*[@id='m_1_--']");



	@FindBy(xpath = "//a[contains(@id, 'm_1_')]/following-sibling::ul/li/a[contains(.,'Works')]")
	private WebElement worksBranchLinkWithoutRefrence;

	By worksBranchLinkWithoutRefrenceForWait = By.xpath("//a[contains(@id, 'm_1_')]/following-sibling::ul/li/a[contains(.,'Works')]");

	@FindBy(xpath = "//*[@id='navigationMenuItems']/li[5]")
	private WebElement selectBranchIcon;

	By selectBranchIconForWait = By.xpath("//*[@id='navigationMenuItems']/li[5]");


	// Purchase

	@FindBy(xpath = ".//*[@id='m_1_app']")
	private  WebElement appTabAnchor;

	@FindBy(xpath = "//ul[@id='navigationMenuItems']//li/a[@id='m_1_purchase']")
	private WebElement purchaseTabAnchor;

	@FindBy(xpath = "//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Purchase Order')]")
	private WebElement purchaseOrderLink;

	By purchaseTabAnchorForWait = By.xpath("//ul[@id='navigationMenuItems']//li/a[@id='m_1_purchase']");
	By purchaseOrderLinkForWait = By.xpath("//a[@id='m_1_purchase']/following-sibling::ul/li/a[contains(.,'Purchase Order')]");

	/**
	 * @return the purchaseTabAnchorForWait
	 */
	public By getPurchaseTabAnchorForWait() {
		return purchaseTabAnchorForWait;
	}

	/**
	 * @return the purchaseOrderLinkForWait
	 */
	public By getPurchaseOrderLinkForWait() {
		return purchaseOrderLinkForWait;
	}
	// Accounting

	By reportsTabAnchorForWait = By.xpath("//ul[@id='navigationMenuItems']//li/a[@id='m_1_reports']");

	@FindBy(xpath = "//ul[@id='navigationMenuItems']//li/a[@id='m_1_reports']")
	private WebElement reportsTabAnchor;

	@FindBy(xpath = "//a[@id='m_1_reports']/following-sibling::ul/li/a[contains(.,'Payment Tagging')]")
	private WebElement paymentTaggingLink;


	/**
	 * @return the accountingLink
	 */
	public WebElement getAccountingLink() {
		return accountingLink;
	}

	/**
	 * @return the reportsTabAnchorForWait
	 */
	public By getReportsTabAnchorForWait() {
		return reportsTabAnchorForWait;
	}

	/**
	 * @return the reportsTabAnchor
	 */
	public WebElement getReportsTabAnchor() {
		return reportsTabAnchor;
	}

	// Retail

	/**
	 * @return the paymentTaggingLink
	 */
	public WebElement getPaymentTaggingLink() {
		return paymentTaggingLink;
	}

	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'Retail')]")
	private WebElement retailAppLink;

	@FindBy(id = "m_1_retail")
	private WebElement retailNavHome; 

	@FindBy(xpath = ".//a[@id='m_1_retail']/following-sibling::ul/li/a[contains(.,'Retail Invoice')]")
	private WebElement retailInvoiceAppLink;


	public WebElement getSelectBranchIcon() {
		return selectBranchIcon;
	}

	public By getSelectBranchIconForWait() {
		return selectBranchIconForWait;
	}

	public By getWorksBranchLinkWithoutRefrenceForWait() {
		return worksBranchLinkWithoutRefrenceForWait;
	}

	public WebElement getWorksBranchLinkWithoutRefrence() {
		return worksBranchLinkWithoutRefrence;
	}

	public WebElement getEmptyBranchIcon() {
		return emptyBranchIcon;
	}

	public By getEmptyBranchIconForWait() {
		return emptyBranchIconForWait;
	}

	public WebElement getTransysAgroBranchLink() {
		return transysAgroBranchLink;
	}

	public WebElement getCreateIcon() {
		return createIcon;
	}

	public WebElement getAppLink() {
		return appLink;
	}

	public WebElement getHcmsAppLink() {
		return hcmsAppLink;
	}

	public WebElement getPurchaseNavHome() {
		return purchaseNavHome;
	}

	public WebElement getMaterialReceiveAppLink() {
		return materialReceiveAppLink;
	}

	public WebElement getPurchaseOrderAppLink() {
		return purchaseOrderAppLink;
	}

	public WebElement getPurchaseInvoiceAppLink() {
		return purchaseInvoiceAppLink;
	}

	public WebElement getMakePaymentAppLink() {
		return makePaymentAppLink;
	}

	public WebElement getSalesLink() {
		return salesLink;
	}

	public WebElement getSalesOrderLink() {
		return salesOrderLink;
	}

	public WebElement getSalesIcon() {
		return salesIcon;
	}

	public WebElement getSalesOrder() {
		return salesOrder;
	}

	public WebElement getDispatchChallans() {
		return dispatchChallans;
	}

	public WebElement getSalesInvoice() {
		return salesInvoice;
	}

	public By getSalesIconForWait() {
		return salesIconForWait;
	}

	public By getAppIconForWait() {
		return appIconForWait;
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

	public WebElement getLeavePolicy() {
		return leavePolicy;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getAppIcon() {
		return appIcon;
	}

	public WebElement getHcmsLink() {
		return hcmsLink;
	}

	public WebElement getLmsIcon() {
		return lmsIcon;
	}

	public WebElement getLeaveType() {
		return leaveType;
	}	

	public NavigationBar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new BrowserActions(driver);
	}

	public WebElement getAppNavigationMenuItem() {
		return appLink;
	}

	public WebElement getHcmsModuleNavigationMenuItem() {
		return hcmsAppLink;
	}

	public WebElement getSuccessMsg() {
		return successMsg;
	}

	public WebElement getLogoutHoverTab() {
		return logoutHoverTab;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public void navigateAppLink() {
		//actions.waitForVisibility(appLink, 30);
		actions.implicitWaitToAnElement(30);
		actions.clickOnElement(appLink);
	}

	public void navigateHCMSLink() {
		actions.waitForVisibility(hcmsAppLink, 30);
		actions.clickOnElement(hcmsAppLink);
		log.info("HCMS app link is opening sucessfully.");
	}

	public void navigatePuchaseLink() {
		actions.waitForVisibility(purchaseAppLink, 30);
		actions.clickOnElement(purchaseAppLink);
		log.info("Purchase app link is opening sucessfully.");
	}

	public void navigateRetailLink() {
		actions.waitForVisibility(retailAppLink, 30);
		actions.clickOnElement(retailAppLink);
		log.info("Retail app link is opening sucessfully.");
	}

	public void navigatePuchaseNavHome() {
		actions.waitForVisibility(purchaseNavHome, 30);
		actions.clickOnElement(purchaseNavHome);
		log.info("Purchase home link is opening sucessfully.");
	}

	public void navigateRetailNavHome() {
		actions.waitForVisibility(retailNavHome, 30);
		actions.clickOnElement(retailNavHome);
		log.info("Retail home link is opening sucessfully.");
	}

	public void navigateMaterialReceiveAppLink() {
		actions.waitForVisibility(materialReceiveAppLink, 30);
		actions.clickOnElement(materialReceiveAppLink);
		log.info("Material Receive app link is opening sucessfully.");
	}

	public void navigatePurchaseOrderAppLink() {
		actions.waitForVisibility(purchaseOrderAppLink, 30);
		actions.clickOnElement(purchaseOrderAppLink);
		log.info("Purchase Order app link is opening sucessfully.");
	}

	public void navigatePurchaseInvoiceAppLink() {
		actions.waitForVisibility(purchaseInvoiceAppLink, 30);
		actions.clickOnElement(purchaseInvoiceAppLink);
		log.info("Purchase Invoice app link is opening sucessfully.");
	}

	public void navigateMakePaymentAppLink() {
		actions.waitForVisibility(makePaymentAppLink, 30);
		actions.clickOnElement(makePaymentAppLink);
	}

	public void navigateRetailInvoiceAppLink() {
		actions.waitForVisibility(retailInvoiceAppLink, 30);
		actions.clickOnElement(retailInvoiceAppLink);
		log.info("Retail Invoice app link is opening sucessfully.");
	}


	// Master
	// Priyankag

	@FindBy(xpath = ".//a[@id='m_1_home']")
	private WebElement homeLink;


	@FindBy(id = "m_1_master")
	private WebElement masterNavHome; 

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Supplier/ Vendor')]")
	private WebElement vendorMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Supplier/ Vendor')]/following-sibling::ul/li/a[contains(.,'All')]")
	private WebElement allInVendorMasterLink;


	public void navigateHomeLink() {
		actions.waitForVisibility(homeLink, 30);
		actions.clickOnElement(homeLink);
	}

	public void navigateMasterNavHome() {
		actions.waitForVisibility(masterNavHome, 30);
		actions.clickOnElement(masterNavHome);
		log.info("Master link is opening sucessfully.");
	}

	public void navigateVendorMasterLink() {
		actions.waitForVisibility(vendorMasterLink, 30);
		actions.clickOnElement(vendorMasterLink);
		log.info("Vendor master link is opening sucessfully.");
	}

	public void navigateAllInVendorMasterLink() {
		actions.waitForVisibility(allInVendorMasterLink, 30);
		actions.clickOnElement(allInVendorMasterLink);
		log.info("All in vendor master link is opening sucessfully.");
	}

	// Priyankag
	// Setting link
	@FindBy(id = "m_1_settings")
	private WebElement settingsNavHome; 


	public void navigateSettingNavHome() {
		actions.waitForVisibility(settingsNavHome, 30);
		actions.clickOnElement(settingsNavHome);
		log.info("Setting link is opening sucessfully.");
	}

	// Priyankag
	// Group link
	@FindBy(id = "m_1_group")
	private WebElement groupNavHome; 

	public void navigateGroupNavHome() {
		actions.waitForVisibility(groupNavHome, 30);
		actions.clickOnElement(groupNavHome);
		log.info("Group link is opening sucessfully.");
	}
	// Priyankag
	// Create Organization

	@FindBy(xpath = ".//a[@id='m_1_group']/following-sibling::ul/li/a[contains(.,'Companies')]")
	private WebElement organizationLink;

	public void navigateOrganizationLink() {
		actions.waitForVisibility(organizationLink, 30);
		actions.clickOnElement(organizationLink);
		log.info("Organization link is opening sucessfully.");
	}

	// Priyankag
	// Create Cities

	@FindBy(xpath = ".//a[@id='m_1_settings']/following-sibling::ul/li/a[contains(.,'Cities')]")
	private WebElement citiesLink;

	public void navigateCitiesLink() {
		actions.waitForVisibility(citiesLink, 30);
		actions.clickOnElement(citiesLink);
		log.info("Cities link is opening sucessfully.");
	}

	// Priyankag
	// Create District

	@FindBy(xpath = ".//a[@id='m_1_settings']/following-sibling::ul/li/a[contains(.,'Districts')]")
	private WebElement districtLink;

	public void navigateDistrictLink() {
		actions.waitForVisibility(districtLink, 30);
		actions.clickOnElement(districtLink);
		log.info("District link is opening sucessfully.");
	}
	//Priyankag
	//Create customer

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Customer')]")
	private WebElement customerMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Customer')]/following-sibling::ul/li/a[contains(.,'All')]")
	private WebElement allInCustomerMasterLink;



	public void navigateCustomerMasterLink() {
		actions.waitForVisibility(customerMasterLink, 30);
		actions.clickOnElement(customerMasterLink);
		log.info("Customer master link is opening sucessfully.");
	}

	public void navigateAllInCustomerMasterLink() {
		actions.waitForVisibility(allInCustomerMasterLink, 30);
		actions.clickOnElement(allInCustomerMasterLink);
		log.info("All in customer master link is opening sucessfully.");
	}

	//Priyankag
	//Create product froup

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Master')]")
	private WebElement productMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Pack Rate')]")
	private WebElement productPackRateLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Master')]/following-sibling::ul/li/a[contains(.,'Group')]")
	private WebElement groupInProductMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Master')]/following-sibling::ul/li/a[contains(.,'Products')]")
	private WebElement productsInProductMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Master')]/following-sibling::ul/li/a[contains(.,'Product Packs')]")
	private WebElement productPackInProductMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Product Master')]/following-sibling::ul/li/a[contains(.,'Brand')]")
	private WebElement brandInProductMasterLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Taxation')]")
	private WebElement taxationLink;

	@FindBy(xpath = ".//a[@id='m_1_master']/following-sibling::ul/li/a[contains(.,'Taxation')]/following-sibling::ul/li/a[contains(.,'Tax Rate')]")
	private WebElement taxRateLink;

	public void navigateProductMasterLink() {
		actions.waitForVisibility(productMasterLink, 30);
		actions.clickOnElement(productMasterLink);
		log.info("Product master link is opening sucessfully.");
	}
	public void navigateTaxationLink() {
		actions.waitForVisibility(taxationLink, 30);
		actions.clickOnElement(taxationLink);
		log.info("Taxation link is opening sucessfully.");
	}

	public void navigateTaxRateLink() {
		actions.waitForVisibility(taxRateLink, 30);
		actions.clickOnElement(taxRateLink);
		log.info("Tax Rate Page is opening sucessfully.");
	}

	public void navigateProductPackRateLink() {
		actions.waitForVisibility(productPackRateLink, 30);
		actions.clickOnElement(productPackRateLink);
		log.info("Product pack rate link is opening sucessfully.");
	}

	public void navigateGroupInProductMasterLink() {
		actions.waitForVisibility(groupInProductMasterLink, 30);
		actions.clickOnElement(groupInProductMasterLink);
		log.info("Group In product  master link is opening sucessfully.");
	}

	public void navigateProductsInProductMasterLink() {
		actions.waitForVisibility(productsInProductMasterLink, 30);
		actions.clickOnElement(productsInProductMasterLink);
		log.info("Products In product  master link is opening sucessfully.");
	}

	public void navigateProductPackInProductMasterLink() {
		actions.waitForVisibility(productPackInProductMasterLink, 30);
		actions.clickOnElement(productPackInProductMasterLink);
		log.info("Product Pack Pae In product  master link is opening sucessfully.");
	}

	public void navigateBrandInProductMasterLink() {
		actions.waitForVisibility(brandInProductMasterLink, 30);
		actions.clickOnElement(brandInProductMasterLink);
		log.info("Brand Page In product  master link is opening sucessfully.");
	}



	//Aditya code


	@FindBy(xpath = ".//*[@id='create-button']/i")
	private WebElement createPurchaseOrderIcon;

	public WebElement getAppTabAnchor() {
		return appTabAnchor;
	}

	public WebElement getPurchaseAppLink() {
		return purchaseAppLink;
	}

	public WebElement getPurchaseTabAnchor() {
		return purchaseTabAnchor;
	}

	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}

	public WebElement getCreatePurchaseOrderIcon() {
		return createPurchaseOrderIcon;
	}

	public void navigatePurchaseLink() {
		actions.implicitWaitToAnElement(30);
		actions.clickOnElement(purchaseAppLink);
	}
	public void navigateAccountingLink() {
		actions.implicitWaitToAnElement(30);
		actions.clickOnElement(accountingLink);
	}

	public void navigatePurchaseTabAnchor() {
		actions.implicitWaitToAnElement(30);
		actions.clickOnElement(purchaseTabAnchor);
	}

	public void navigatePurchaseOrderLink() {
		actions.waitForVisibility(purchaseOrderLink, 30);
		actions.clickOnElement(purchaseOrderLink);
	}

	public void createPurchaseOrderIcon() {
		actions.waitForVisibility(createPurchaseOrderIcon, 30);
		actions.clickOnElement(createPurchaseOrderIcon);
	}

	public void appModule() throws Exception {
		System.out.println("app module in home page");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement lmsIcon1 = driver.findElement(By.xpath(".//*[@id='navigationMenuItems']/li[3]/a"));
		lmsIcon1.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		leaveType.click();

	}

	//In future this data will be removed.
	@FindBy(xpath = ".//*[@id='login-form']/div[2]/p")
	private String xpath_invalid_pwd;

	@FindBy(xpath = ".//*[@id='login-form']/div[3]/p")
	private String xpath_invalid_un;

	@FindBy(xpath = "(//div[@class='col-12'])")
	private WebElement pageTitle;

	@FindBy(xpath = ".//*[@id='create-button']")
	private WebElement createLeaveIcon;

	public WebElement getCreateLeaveIcon() {
		return createLeaveIcon;
	}

	public WebElement getPageTitle() {
		return pageTitle;
	}

	public WebElement getRetailAppLink() {
		return retailAppLink;
	}

	public void setPageTitle(WebElement pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setCreateIcon(WebElement createIcon) {
		this.createIcon = createIcon;
	}

	public void setLogoutIcon(WebElement logoutIcon) {
		this.logoutIcon = logoutIcon;
	}

	public void setLogoutLink(WebElement logoutLink) {
		this.logoutLink = logoutLink;
	}

	public String getXpath_invalid_pwd() {
		return xpath_invalid_pwd;
	}

	public WebElement getRetailNavHome() {
		return retailNavHome;
	}

	public WebElement getRetailInvoiceAppLink() {
		return retailInvoiceAppLink;
	}

	public void setXpath_invalid_pwd(String xpath_invalid_pwd) {
		this.xpath_invalid_pwd = xpath_invalid_pwd;
	}

	public String getXpath_invalid_un() {
		return xpath_invalid_un;
	}

	/**
	 * @return the receivePayment
	 */
	public WebElement getReceivePayment() {
		return receivePayment;
	}

	/**
	 * @return the databaseException
	 */
	public List<WebElement> getDatabaseException() {
		return databaseException;
	}


	// Quick Purchase


	private By app_dropdown = By.xpath("//a[@id='m_1_app']");
	private By list_of_App = By.xpath("//ul[@id='w6']//li");
	private By quick_Purchase_app = By.xpath("//a[contains(text(),'QP')]");
	private By purchase_dropdown = By.xpath("//a[@id='m_1_purchase']");
	private By manage_purchase_module = By.xpath("//ul[@id='w3']//a[contains(text(),'Purchase')]");
	private By create_QP_button = By.xpath("//a[@id='create-button']");

	public By getApp_dropdown_homePAge() {
		return app_dropdown;
	}

	public By getList_of_App() {
		return list_of_App;
	}

	public By getQuick_Purchase_app() {
		return quick_Purchase_app;
	}

	public By getPurchase_dropdown() {
		return purchase_dropdown;
	}

	public By getManage_purchase_module() {
		return manage_purchase_module;
	}
	public By getCreate_QP_button() {
		return create_QP_button;
	}

	//	@FindBy(xpath = "//a[@id='m_1_app']")
	//	WebElement app_dropdown;


	// Dkosh


	//	@FindBy(xpath = "//a[contains(text(),'DKOSH')]")
	//	WebElement dkosh_app;


	@FindBy(xpath = "//a[@id='m_1_app']/following-sibling::ul/li/a[contains(.,'dKOSH')]")
	private WebElement dkosh_app;



	@FindBy(xpath = "//a[@id='m_1_dkosh']")
	WebElement dkosh_dropdown;
	/*
	@FindBy(xpath = "//ul[@id='w4']//a[contains(text(),'Manage Forms')]")
	WebElement manage_form_list_page;*/

	@FindBy(xpath = ".//a[@id='m_1_dkosh']/following-sibling::ul/li/a[contains(.,'Manage Forms')]")
	private WebElement manageFormLink;



	@FindBy(xpath = "//a[@id='m_1_onlinetest']")
	WebElement onlineTest_dropdown;

	@FindBy(xpath = ".//a[@id='m_1_onlinetest']/following-sibling::ul/li/a[contains(.,'Manage Candidates')]")
	private WebElement manageCandidatesLink;

	@FindBy(xpath = ".//a[@id='m_1_onlinetest']/following-sibling::ul/li/a[contains(.,'Manage Tests')]")
	private WebElement manageTestsLink;


	/**
	 * @return the homeLink
	 */
	public WebElement getHomeLink() {
		return homeLink;
	}

	/**
	 * @return the masterNavHome
	 */
	public WebElement getMasterNavHome() {
		return masterNavHome;
	}

	/**
	 * @return the vendorMasterLink
	 */
	public WebElement getVendorMasterLink() {
		return vendorMasterLink;
	}

	/**
	 * @return the allInVendorMasterLink
	 */
	public WebElement getAllInVendorMasterLink() {
		return allInVendorMasterLink;
	}

	/**
	 * @return the settingsNavHome
	 */
	public WebElement getSettingsNavHome() {
		return settingsNavHome;
	}

	/**
	 * @return the groupNavHome
	 */
	public WebElement getGroupNavHome() {
		return groupNavHome;
	}

	/**
	 * @return the organizationLink
	 */
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	/**
	 * @return the citiesLink
	 */
	public WebElement getCitiesLink() {
		return citiesLink;
	}

	/**
	 * @return the districtLink
	 */
	public WebElement getDistrictLink() {
		return districtLink;
	}

	/**
	 * @return the customerMasterLink
	 */
	public WebElement getCustomerMasterLink() {
		return customerMasterLink;
	}

	/**
	 * @return the allInCustomerMasterLink
	 */
	public WebElement getAllInCustomerMasterLink() {
		return allInCustomerMasterLink;
	}

	/**
	 * @return the productMasterLink
	 */
	public WebElement getProductMasterLink() {
		return productMasterLink;
	}

	/**
	 * @return the productPackRateLink
	 */
	public WebElement getProductPackRateLink() {
		return productPackRateLink;
	}

	/**
	 * @return the groupInProductMasterLink
	 */
	public WebElement getGroupInProductMasterLink() {
		return groupInProductMasterLink;
	}

	/**
	 * @return the productsInProductMasterLink
	 */
	public WebElement getProductsInProductMasterLink() {
		return productsInProductMasterLink;
	}

	/**
	 * @return the productPackInProductMasterLink
	 */
	public WebElement getProductPackInProductMasterLink() {
		return productPackInProductMasterLink;
	}

	/**
	 * @return the brandInProductMasterLink
	 */
	public WebElement getBrandInProductMasterLink() {
		return brandInProductMasterLink;
	}

	/**
	 * @return the taxationLink
	 */
	public WebElement getTaxationLink() {
		return taxationLink;
	}

	/**
	 * @return the taxRateLink
	 */
	public WebElement getTaxRateLink() {
		return taxRateLink;
	}

	/**
	 * @return the app_dropdown
	 */
	public By getApp_dropdown() {
		return app_dropdown;
	}

	/**
	 * @return the dkosh_app
	 */
	public WebElement getDkosh_app() {
		return dkosh_app;
	}

	/**
	 * @return the dkosh_dropdown
	 */
	public WebElement getDkosh_dropdown() {
		return dkosh_dropdown;
	}

	/**
	 * @return the manageFormLink
	 */
	public WebElement getManageFormLink() {
		return manageFormLink;
	}

	/**
	 * @return the onlineTest_dropdown
	 */
	public WebElement getOnlineTest_dropdown() {
		return onlineTest_dropdown;
	}

	/**
	 * @return the manageCandidatesLink
	 */
	public WebElement getManageCandidatesLink() {
		return manageCandidatesLink;
	}

	/**
	 * @return the manageTestsLink
	 */
	public WebElement getManageTestsLink() {
		return manageTestsLink;
	}

	/*@FindBy(xpath = "//a[@id='create-button']")
	WebElement create_form_button;
	 */

	/**
	 * 08-07-2020
	 * Function For Navigation - After Changes In UI of Navigation
	 * @param moduleName
	 * @param subModuleName
	 * @throws InterruptedException
	 */
	public void navigation(String moduleName , String subModuleName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		By appMenu = By.xpath("//*[contains(@class,'app-menu')]");
		By menuApp = By.xpath("//ul[contains(@class,'dropdown-menu-app')]/li/a/span[contains(.,'" + moduleName+ "')]");
		By navBarToggler = By.xpath("//button[@id='navtoggleid']");
		By selectModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+moduleName+" ']");
		By selectSubModule = By.xpath("//li[contains(@class,'dropdown open')]/ul/li/a[contains(.,'" + subModuleName+ "')]");

		// Select Produc In Master app
		String productModuleName = "Products";
		By selectProductModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+productModuleName+" ']");


		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(appMenu));
		// Select App Menu
		actions.clickOnElementWithJS(driver.findElement(appMenu));
		actions.waitForPageLoaded();
		Thread.sleep(1000);
		// Select Module
		try {
			actions.clickOnElementWithJS(driver.findElement(menuApp));
			actions.implicitWaitToAnElement(30);
			Thread.sleep(3000);
			actions.switchToParentWindow();
			Thread.sleep(2000);
			System.out.println("Title Of Page : "+ driver.getTitle()+"Current Url : "+driver.getCurrentUrl());
			driver.navigate().refresh();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
			// Select navbar-toggler
			actions.waitForPageLoaded();
			actions.clickOnElement(driver.findElement(navBarToggler));
			actions.implicitWaitToAnElement(50);
		}
		catch (Exception e) {
			// TODO: handle exception
			if(moduleName.equals("Sales")) {
				driver.navigate().to("http://10.1.10.110:89/Sathi/Sales/");
			}
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
			// Select navbar-toggler
			actions.clickOnElement(driver.findElement(navBarToggler));
			actions.implicitWaitToAnElement(50);
		}

		// Select Module
		if(subModuleName.contains("Rate Management")) {
			actions.clickOnElementWithJS(driver.findElement(selectProductModule));
			actions.implicitWaitToAnElement(30);

		}else {
			actions.clickOnElementWithJS(driver.findElement(selectModule));
			actions.implicitWaitToAnElement(30);
		}
		// Select SubModule
		actions.clickOnElementWithJS(driver.findElement(selectSubModule));
		actions.implicitWaitToAnElement(30);

	}

	/**
	 * 08-07-2020
	 * Function For Navigation - After Changes In UI of Navigation
	 * @author priyankag
	 * @throws InterruptedException 
	 */
	public void navigation(String appName,String menuItemName, String subMenuItemName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		By appMenu = By.xpath("//a[contains(@class,'app-menu')]");
		By menuApp = By.xpath("//ul[contains(@class,'dropdown-menu-app')]/li/a/span[contains(.,'" + appName+ "')]");
		By navBarToggler = By.xpath("//button[@id='navtoggleid']");
		By selectModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+menuItemName+" ']");
		By selectSubModule = By.xpath("//li[contains(@class,'dropdown open')]/ul/li/a[text()='"+subMenuItemName+"']");

		// Select Produc In Master app
		String productModuleName = "Products";
		By selectProductModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+productModuleName+" ']");

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(appMenu));
		// Select App Menu
		actions.clickOnElementWithJS(driver.findElement(appMenu));
		actions.waitForPageLoaded();
		Thread.sleep(1000);
		// Select Module
		try {
			if(actions.isElementPresent(driver.findElements(menuApp))) {
				actions.clickOnElementWithJS(driver.findElement(menuApp));
				actions.implicitWaitToAnElement(30);
			}
			else {
				Reporter.log("The App Name is - "+appName +" Not Present.");
				Assert.assertTrue(actions.isElementPresent(driver.findElements(menuApp)),"The permission of  App - "+appName +" Not Present.");
			}
			Thread.sleep(3000);
			actions.switchToParentWindow();
			Thread.sleep(2000);
			System.out.println("Title Of Page : "+ driver.getTitle()+"Current Url : "+driver.getCurrentUrl());
			driver.navigate().refresh();
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
			// Select navbar-toggler
			actions.waitForPageLoaded();
			actions.clickOnElement(driver.findElement(navBarToggler));
			actions.implicitWaitToAnElement(50);

		}
		catch (Exception e) {
			// TODO: handle exception
			if(appName.equals("Sales")) {
				driver.navigate().to("http://10.1.10.110:89/Sathi/Sales/");
			}
			//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
			// Select navbar-toggler
			actions.waitForPageLoaded();
			actions.clickOnElement(driver.findElement(navBarToggler));
			actions.implicitWaitToAnElement(50);
		}
		// Select Module
		if(subMenuItemName.contains("Rate Management")) {
			actions.clickOnElementWithJS(driver.findElement(selectProductModule));
			actions.implicitWaitToAnElement(30);

		}else
			if(actions.isElementPresent(driver.findElements(selectModule))) {
				actions.clickOnElementWithJS(driver.findElement(selectModule));
				actions.implicitWaitToAnElement(30);
			}
			else {
				Reporter.log("The "+menuItemName +" In App - "+appName + " Not Present.");
				Assert.assertTrue(actions.isElementPresent(driver.findElements(selectModule)),"The permission of  Menu Item - "+menuItemName +" In App - "+appName + " Not Present.");

			}

		// Select SubModule
		if(actions.isElementPresent(driver.findElements(selectSubModule))) {
			actions.clickOnElementWithJS(driver.findElement(selectSubModule));
			actions.implicitWaitToAnElement(30);
		}
		else {
			Reporter.log("The "+subMenuItemName +" In App - "+appName + " Not Present.");
			Assert.assertTrue(actions.isElementPresent(driver.findElements(selectSubModule)),"The permission of  Sub Menu Item - "+subMenuItemName +" - Menu Item - "+menuItemName +" In App - "+appName + "Not Present.");
		}

	}
	/**
	 * 08-07-2020
	 * Function For Navigation - After Changes In UI of Navigation
	 * @author priyankag
	 * @throws InterruptedException 
	 */
	public void navigationSparkPhase1(String appName,String menuItemName, String subMenuItemName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		By appMenu = By.xpath("//*[contains(@class,'app-menu')]");
		By menuApp = By.xpath("//ul[contains(@class,'dropdown-menu-app')]/li/a/span[contains(.,'" + appName+ "')]");
		By navBarToggler = By.xpath("//button[@id='navtoggleid']");
		By selectModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+menuItemName+" ']");
		By selectSubModule = By.xpath("//li[contains(@class,'dropdown open')]/ul/li/a[contains(.,'" + subMenuItemName+ "')]");

		// Select Produc In Master app
		String productModuleName = "Products";
		By selectProductModule = By.xpath("//li[contains(@class,'dropdown')]/a[text()='"+productModuleName+" ']");

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(appMenu));
		// Select App Menu
		actions.clickOnElementWithJS(driver.findElement(appMenu));
		actions.waitForPageLoaded();
		Thread.sleep(1000);
		// Select Module
		try {
			if(actions.isElementPresent(driver.findElements(menuApp))) {
				actions.clickOnElementWithJS(driver.findElement(menuApp));
				actions.implicitWaitToAnElement(30);
				Thread.sleep(3000);
				actions.switchToParentWindow();
				Thread.sleep(2000);
				System.out.println("Title Of Page : "+ driver.getTitle()+"Current Url : "+driver.getCurrentUrl());
				driver.navigate().refresh();
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
				// Select navbar-toggler
				actions.waitForPageLoaded();
				actions.clickOnElement(driver.findElement(navBarToggler));
				actions.implicitWaitToAnElement(50);
			}
			else {
				Reporter.log("The App Name is - "+appName +" Not Present.");
				//softAssert.assertTrue(true,"The App Name is - "+appName +" Not Present.");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			if(appName.equals("Sales")) {
				driver.navigate().to("http://10.1.10.110:89/Sathi/Sales/");
			}
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(navBarToggler));
			// Select navbar-toggler
			actions.clickOnElement(driver.findElement(navBarToggler));
			actions.implicitWaitToAnElement(50);
		}

		// Select Module
		if(subMenuItemName.contains("Rate Management")) {
			actions.clickOnElementWithJS(driver.findElement(selectProductModule));
			actions.implicitWaitToAnElement(30);

		}else if(actions.isElementPresent(driver.findElements(selectModule))) {
			actions.clickOnElementWithJS(driver.findElement(selectModule));
			actions.implicitWaitToAnElement(30);
		}
		else {
			Reporter.log("The "+menuItemName +" In App - "+appName + " Not Present.");
			//softAssert.assertTrue(true,"The "+menuItemName +" In App - "+appName + " Not Present.");
		}


		// Select SubModule
		if(actions.isElementPresent(driver.findElements(selectSubModule))) {
			actions.clickOnElementWithJS(driver.findElement(selectSubModule));
			actions.implicitWaitToAnElement(30);
		}
		else {
			Reporter.log("The "+subMenuItemName +" In App - "+appName + " Not Present.");
			//softAssert.assertTrue(true,"The "+subMenuItemName +" In App - "+appName + " Not Present.");
		}

	}


	/**
	 * @return the createButton
	 */
	public By getCreateButton() {
		return createButton;
	}

	/**
	 * @return the createModalButton
	 */
	public By getCreateModalButton() {
		return createModalButton;
	}

	/**
	 * @return the createIconBy
	 */
	public By getCreateIconBy() {
		return createIconBy;
	}

	/**
	 * @return the pageHeaderElement
	 */
	public By getPageHeaderElement() {
		return pageHeaderElement;
	}

	/**
	 * @return the pageException
	 */
	public By getPageException() {
		return pageException;
	}


}
