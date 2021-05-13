package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;	
	private final String propertyFilePath= System.getProperty("user.dir") +"/../Library/configs/Configuration.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	public String getIEWinDriverPath(){
		String ieWindowsDriverPath = properties.getProperty("ieWindowsDriverPath");
		if(ieWindowsDriverPath!= null) return ieWindowsDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	
	public String getOperaWinDriverPath(){
		String operaWindowsDriverPath = properties.getProperty("operaWindowsDriverPath");
		if(operaWindowsDriverPath!= null) return operaWindowsDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	public String getFirefoxWinDriverPath(){
		String firefoxWindowsDriverPath = properties.getProperty("firefoxWindowsDriverPath");
		if(firefoxWindowsDriverPath!= null) return firefoxWindowsDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	public String getFirefoxLinuxDriverPath(){
		String firefoxLinuxDriverPath = properties.getProperty("firefoxLinuxDriverPath");
		if(firefoxLinuxDriverPath!= null) return firefoxLinuxDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	public String getOperaLinuxDriverPath(){
		String operaLinuxDriverPath = properties.getProperty("operaLinuxDriverPath");
		if(operaLinuxDriverPath!= null) return operaLinuxDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	public String getChromeWinDriverPath(){
		String chromeWindowsDriverPath = properties.getProperty("chromeWindowsDriverPath");
		if(chromeWindowsDriverPath!= null) return chromeWindowsDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	public String getChromeLinuxDriverPath(){
		String chromeLinuxDriverPath = properties.getProperty("chromeLinuxDriverPath");
		if(chromeLinuxDriverPath!= null) return chromeLinuxDriverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getConfigurationSpreadSheetId() {
		String configSpreadSheetId = properties.getProperty("configurationSpreadsheetId");
		if(configSpreadSheetId != null) return configSpreadSheetId;
		else throw new RuntimeException("configuration spread sheet Id not specified in the Configuration.properties file.");
	}
	public String getWebLoginCreadentialSheetName() {
		String webLoginCredentialSheetName = properties.getProperty("webLoginCredentialSheetName");
		if(webLoginCredentialSheetName != null) return webLoginCredentialSheetName;
		else throw new RuntimeException("web login credential sheet name not specified in the Configuration.properties file.");
	}
	public String getMobileLoginCredentialSheetName() {
		String mobileLoginCredentialSheetName = properties.getProperty("mobileLoginCredentialSheetName");
		if(mobileLoginCredentialSheetName != null) return mobileLoginCredentialSheetName;
		else throw new RuntimeException("mobile login credential sheet name not specified in the Configuration.properties file.");
	}
	public String getLoginSpreadSheetId() {
		String loginSpreadsheetId = properties.getProperty("loginSpreadsheetId");
		if(loginSpreadsheetId != null) return loginSpreadsheetId;
		else throw new RuntimeException("Login data spread sheet Id not specified in the Configuration.properties file.");
	}
	public String getMasterSpreadSheetId() {
		String masterSpreadsheetId = properties.getProperty("masterSpreadsheetId");
		if(masterSpreadsheetId != null) return masterSpreadsheetId;
		else throw new RuntimeException("Master data spread sheet Id not specified in the Configuration.properties file.");
	}
	public String getFreightDataSheetName() {
		String frightDataSheetName = properties.getProperty("frightDataSheetName");
		if(frightDataSheetName != null) return frightDataSheetName;
		else throw new RuntimeException("freight data sheet name not specified in the Configuration.properties file.");
	}
	public String getCustomerDataSheetName() {
		String customerDataSheetName = properties.getProperty("customerDataSheetName");
		if(customerDataSheetName != null) return customerDataSheetName;
		else throw new RuntimeException("customer data sheet name not specified in the Configuration.properties file.");
	}
	public String getOrganizationDataSheetName() {
		String organizationDataSheetName = properties.getProperty("organizationDataSheetName");
		if(organizationDataSheetName != null) return organizationDataSheetName;
		else throw new RuntimeException("organization data sheet name not specified in the Configuration.properties file.");
	}
	public String getCityDataSheetName() {
		String cityDataSheetName = properties.getProperty("cityDataSheetName");
		if(cityDataSheetName != null) return cityDataSheetName;
		else throw new RuntimeException("city data sheet name not specified in the Configuration.properties file.");
	}
	public String getDistrictDataSheetName() {
		String districtDataSheetName = properties.getProperty("districtDataSheetName");
		if(districtDataSheetName != null) return districtDataSheetName;
		else throw new RuntimeException("district data sheet name not specified in the Configuration.properties file.");
	}
	public String getProductGroupDataSheetName() {
		String productGroupDataSheetName = properties.getProperty("productGroupDataSheetName");
		if(productGroupDataSheetName != null) return productGroupDataSheetName;
		else throw new RuntimeException("product group data sheet name not specified in the Configuration.properties file.");
	}
	public String getPartyProductDataSheetName() {
		String partyProductDataSheetName = properties.getProperty("partyProductDataSheetName");
		if(partyProductDataSheetName != null) return partyProductDataSheetName;
		else throw new RuntimeException("party product data sheet name not specified in the Configuration.properties file.");
	}
	public String getBrandDataSheetName() {
		String brandDataSheetName = properties.getProperty("brandDataSheetName");
		if(brandDataSheetName != null) return brandDataSheetName;
		else throw new RuntimeException("brand data sheet name not specified in the Configuration.properties file.");
	}
	public String getProductDataSheetName() {
		String productDataSheetName = properties.getProperty("productDataSheetName");
		if(productDataSheetName != null) return productDataSheetName;
		else throw new RuntimeException("product data sheet name not specified in the Configuration.properties file.");
	}
	public String getProductPackDataSheetName() {
		String productPackDataSheetName = properties.getProperty("productPackDataSheetName");
		if(productPackDataSheetName != null) return productPackDataSheetName;
		else throw new RuntimeException("product pack data sheet name not specified in the Configuration.properties file.");
	}
	public String getProductPackRateDataSheetName() {
		String productPackRateDataSheetName = properties.getProperty("productPackRateDataSheetName");
		if(productPackRateDataSheetName != null) return productPackRateDataSheetName;
		else throw new RuntimeException("product pack rate data sheet name not specified in the Configuration.properties file.");
	}
	public String getTaxRateDataSheetName() {
		String taxRateDataSheetName = properties.getProperty("taxRateDataSheetName");
		if(taxRateDataSheetName != null) return taxRateDataSheetName;
		else throw new RuntimeException("tax rate data sheet name not specified in the Configuration.properties file.");
	}
}
