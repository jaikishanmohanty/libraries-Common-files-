package utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import browsers.BrowserActions;

public class DataProviders{
	WebDriver driver;
	Object[][] credentialArrayObject; 
	Object[][] userCredential;	

	BrowserActions actions = new BrowserActions(driver);
	GoogleSheetAPIUtils googleSheet = new GoogleSheetAPIUtils();
	ConfigFileReader configFileReader = new ConfigFileReader();
	static ExcelUtils utils = new ExcelUtils();

	// Spreadsheet Ids
	String loginSpreadsheetId = configFileReader.getLoginSpreadSheetId();
	String masterSpreadsheetId = configFileReader.getMasterSpreadSheetId();

	// Mobile Login Credential Sheet Name
	String mobileLoginCredentialDataSheetName = configFileReader.getMobileLoginCredentialSheetName();
	

	// Get Login Data for Web From Configuration Spreadsheet
	@DataProvider ( name="loginDetails" )
	public Object[][] getLoginCredentials() throws IOException, GeneralSecurityException {

		String loginDataSheetId;
		String mobileNoInConfigSheet;
		String mobileNoInLoginDataSheet;
		String sheetNameInConfigSheet;	
		InetAddress localhost = InetAddress.getLocalHost();
		String Ip_Add = localhost.getHostAddress();
		String configurationSpreadsheetId = configFileReader.getConfigurationSpreadSheetId();
		String web_Credential = configFileReader.getWebLoginCreadentialSheetName();
		Object[][] arrayObjectWebCredential = googleSheet.readDataFromGoogleSheet(configurationSpreadsheetId,web_Credential);
		Object[][] webDataForIpAdd =actions.getFilterDataFromExcelSheet(arrayObjectWebCredential, Ip_Add);
		for(Object[] val: webDataForIpAdd) {
			sheetNameInConfigSheet = (String)val[4];
			loginDataSheetId = (String)val[3];
			credentialArrayObject = googleSheet.readDataFromGoogleSheet(loginDataSheetId,sheetNameInConfigSheet);
			mobileNoInConfigSheet = val[5].toString();
			if(mobileNoInConfigSheet.equals("")) {
				for(Object[] credentialValue: credentialArrayObject) {			  
					for(Object obj: credentialValue) {
						System.out.print(obj.toString() + ", ");
					}
					System.out.println();
				}
			}
			else {
				userCredential = actions.getFilterDataFromExcelSheet(credentialArrayObject,mobileNoInConfigSheet);
				for(Object[] credentialValue: userCredential) {	
					mobileNoInLoginDataSheet = credentialValue[2].toString();
					if(mobileNoInConfigSheet.equals(mobileNoInLoginDataSheet)) {
						for(Object obj: credentialValue) {
							System.out.print(obj.toString() + ", ");
						}
						System.out.println();
					}	
				}
			}
		}
		Object[][] loginCredentials;
		String username;
		String password;
		String mobileNo;
		if(userCredential != null) {
			loginCredentials  = userCredential;
			for(Object[] val: loginCredentials) {
				username = (String)val[0];
				password = (String)val[1];        
				mobileNo = (String)val[2];
			}
		}
		else {
			loginCredentials = credentialArrayObject;
			for(Object[] val: loginCredentials) {
				username = (String)val[0];
				password = (String)val[1];
				mobileNo = (String)val[2];
			}
		}	
		return loginCredentials;
	}
		
	public Object[][] getGoogleSheetData(String spreadSheetId,String spreadSheetRange) throws IOException, GeneralSecurityException{

		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(spreadSheetId, spreadSheetRange);
		for(Object[] val: arrayObject) {
			for(Object obj: val) {
				System.out.print(obj.toString() + ", ");
			}
			System.out.println();
		}	
		return arrayObject;
	}

	// Get Freight Data From freight_data sheet of master spreadSheet
	@DataProvider (name = "freightData")
	public Object[][] getFreightData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getFreightDataSheetName());
		return arrayObject;
	}

	// Get Mobile Login Data From priyanka_account_mobile sheet of LoginData spreadSheet
	@DataProvider (name = "MobileLoginDetails")
	public Object[][] getLoginData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(loginSpreadsheetId, mobileLoginCredentialDataSheetName);
		for(Object[] val: arrayObject) {
			for(Object obj: val) {
				System.out.print(obj.toString() + ", ");
			}
			System.out.println();
		}	
		return arrayObject;
	}

	// Get Customer Data From customer_data sheet of master spreadSheet
	@DataProvider (name = "CustomerData")
	public Object[][] getCustomerData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId,configFileReader.getCustomerDataSheetName());
		return arrayObject;
	}
	// Get Organization Data From organization sheet of master spreadSheet
	@DataProvider (name = "OrganizationData")
	public Object[][] getOrganizationData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getOrganizationDataSheetName());
		return arrayObject;
	}

	// Get City Data From city sheet of master spreadSheet
	@DataProvider (name = "CityData")
	public Object[][] getCityData() throws IOException, GeneralSecurityException {
		//   return getData("city");
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getCityDataSheetName());
		return arrayObject;
	}

	// Get District Data From district sheet of master spreadSheet
	@DataProvider (name = "DistrictData")
	public Object[][] getDistrictData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getDistrictDataSheetName());
		return arrayObject;
	}

	// Get Product Group Data From product_group sheet of master spreadSheet
	@DataProvider (name = "ProductGroupData")
	public Object[][] getProductGroupData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getProductGroupDataSheetName());
		for(Object[] val: arrayObject) {
			for(Object obj: val) {
				System.out.print(obj.toString() + ", ");
			}
			System.out.println();	
		}
		return arrayObject;
	}

	// Get Party Product Data From party_product sheet of master spreadSheet
	@DataProvider (name = "PartyProductData")
	public Object[][] getPartyProductData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId,configFileReader.getPartyProductDataSheetName());
		for(Object[] val: arrayObject) {
			for(Object obj: val) {
				System.out.print(obj.toString() + ", ");
			}
			System.out.println();	
		}

		return arrayObject;
	}

	// Get Brand Data From brand sheet of master spreadSheet
	@DataProvider (name = "BrandData")
	public Object[][] getBrandData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getBrandDataSheetName());
		return arrayObject;
	}
	
	// Get Product Data From product sheet of master spreadSheet
	@DataProvider (name = "ProductData")
	public Object[][] getProductData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getProductDataSheetName());
		return arrayObject;
	}

	// Get Product Pack Data From product_pack sheet of master spreadSheet
	@DataProvider (name = "ProductPackData")
	public Object[][] getProductPackData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getProductPackDataSheetName());	
		return arrayObject;
	}
	
	// Get Product Pack Rate Data From product_pack_rate sheet of master spreadSheet
	@DataProvider (name = "ProductPackRateData")
	public Object[][] getProductPackRateData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getProductPackRateDataSheetName());
		return arrayObject;
	}
	
	// Get Tax Rate Data From tax_rate sheet of master spreadSheet
	@DataProvider (name = "TaxRateData")
	public Object[][] getTaxRateData() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(masterSpreadsheetId, configFileReader.getTaxRateDataSheetName());
		return arrayObject;
	}

	public Object[][] getExcelSheetData(String filePath,String sheeName)
	{
		Object[][] arrayObject = utils.getExcelData(filePath,sheeName);
		return arrayObject;
	}	
	@DataProvider (name = "purchaseOrderDataDetails")
	public static Object[][] getPurchaseOrderProvider(){
		return new Object[][]{
			{"Kribhoco Shyam Fertilizers Ltd. (KSFL)", "", "Ball", "Leather", "12 Ball Set", "100", "Self", "Works"}
		};
	}
	/**
	 * Prints the names and majors of Login in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1QVD54NYCN2kjNaiKVRt0gyFbxMOQqDPdRZWcXrpnSXU/edit
	 */
	/*@DataProvider (name = "loginDetails")
	public Object[][] getLogin() throws IOException, GeneralSecurityException {
		Object[][] arrayObject = googleSheet.readDataFromGoogleSheet(loginSpreadsheetId,login_Credential);
		for(Object[] val: arrayObject) {
			for(Object obj: val) {
			System.out.print(obj.toString() + ", ");
		}
		System.out.println();
		}		
		return arrayObject;
	}*/
	//static ExcelReader reader = new ExcelReader();

	/*
		int intDataSourceType;
		String strDataSourceName;

		public void setIntDataSourceType(int intDataSourceType) {
			this.intDataSourceType = intDataSourceType;
		}

		public void setStrDataSourceName(String strDataSourceName) {
			this.strDataSourceName = strDataSourceName;
		}
	 */
	/*
		@DataProvider (name = "SheetData")
		public Object[][] getData(String strSheetName) throws IOException, GeneralSecurityException {
			Object[][] arrayObject;
			if (this.intDataSourceType == 1) {
				// Excel/CSV
				arrayObject = utils.getExcelData(strDataSourceName, strSheetName);
			} else {
				// Google spreadsheet
				arrayObject = googleSheet.readDataFromGoogleSheet(strDataSourceName, strSheetName + "!A2:Z");
			}
			return arrayObject;
		}
	 */
}
