package browsers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import helpers.LoggerHelper;

/**
 * Browser Actions Class for launch browser.
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class BrowserActions {
	private static final RemoteWebElement El = null;
	WebDriver driver;
	WebDriverWait wait;
	Logger log = LoggerHelper.getLogger(BrowserActions.class);

	public BrowserActions(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Function for load URL in a browser using get() method
	 * 
	 * @param url URL for load in browser
	 */
	public void openPage(String url) {
		driver.get(url);
	}

	/**
	 * Function for clear and send text in element
	 * 
	 * @param element WebElement for input field
	 * @param text    String value for input field
	 */
	public void clearAndSendKeysToElement(WebElement element, String text) {
		implicitWaitToAnElement(40);
		try {
			element.clear();
		} catch (Exception e) {
			implicitWaitToAnElement(30);

		}
		element.sendKeys(text);

	}

	/**
	 * Function for click and send text in element
	 * 
	 * @param element WebElement for input field
	 * @param text    String value for input field
	 */
	public void clickAndSendKeysToElement(WebElement element, String text) {
		implicitWaitToAnElement(40);
		element.click();
		element.sendKeys(text);
	}

	/**
	 * Function for verify element is displayed
	 * 
	 * @param element Xpath of element
	 * @return
	 */
	public boolean isElementDisplayed(WebElement element) {
		applyImplicitWaitToAnElement();
		return element.isDisplayed();
	}

	/**
	 * Function for verify element is enabled
	 * 
	 * @param element Xpath of element
	 * @return
	 */
	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	/**
	 * Function for implicit element
	 * 
	 * @param null
	 */
	public void applyImplicitWaitToAnElement() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * Function for page load timeOut
	 * 
	 * @param null
	 */
	public void pageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	/*
	 * public void applyUntilWaitToAnElement(By element) { wait = new
	 * WebDriverWait(driver, 10);
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)); }
	 */

	/**
	 * Function for wait of element as per given time value
	 * 
	 * @param element    WebElement for check visibility
	 * @param timeSecond Time in second
	 * @throws Error
	 */
	public void waitForVisibility(WebElement element, int timeSecond) throws Error {
		new WebDriverWait(driver, timeSecond).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Function for click on element
	 * 
	 * @param element Xpath for click on element
	 */
	public void clickOnElement(WebElement element) {
		implicitWaitToAnElement(40);
		element.click();
		implicitWaitToAnElement(40);
	}

	/**
	 * Function for get text from field
	 * 
	 * @param element Xpath of field
	 * @return
	 */
	public String getText(WebElement element) {
		implicitWaitToAnElement(40);
		return element.getText();

	}

	/**
	 * Function for browser to move backward
	 * 
	 * @param null
	 */
	public void navigateBack() {
		driver.navigate().back();
	}

	/**
	 * Function for browser to move forward
	 * 
	 * @param null
	 */
	public void navigateForward() {
		driver.navigate().forward();
	}

	/**
	 * Function for select value from dropdown
	 * 
	 * @param element       Xpath of dropdown field
	 * @param selectedvalue String for selected value.
	 * @throws InterruptedException
	 */
	public void selectDropDown(WebElement element, String selectedvalue) throws InterruptedException {
		implicitWaitToAnElement(50);
		Select option = new Select(element);
		option.selectByVisibleText(selectedvalue);
		Thread.sleep(1000);
		implicitWaitToAnElement(30);
	}

	/**
	 * Function for check is element selected
	 * 
	 * @param element Xpath of element
	 * @return
	 */
	public boolean isElementSelected(WebElement element) {
		implicitWaitToAnElement(40);
		return element.isSelected();
	}

	/**
	 * Function for select value from customized dropdown field
	 * 
	 * @param dropDownSelectedValue Value want to select from dropdown.
	 * @param dropdownOption        Xpath for all options present in the dropdown
	 * @param dropdownAllElements   Xpath for all dropdown elements
	 * @throws InterruptedException
	 */
	public void selectCustomizeDropDownValue(String dropDownSelectedValue, By dropdownOption, By dropdownAllElements)
			throws InterruptedException {
		driver.findElement(dropdownOption).click();
		List<WebElement> options = driver.findElements(dropdownAllElements);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		System.out.println("options,,,,,,,,,,,,,::"+options.size());
		System.out.println(dropDownSelectedValue);

		if (options.size() > 0)
			for (int i = 0; i < options.size(); i++)
				try {
					System.out.println("aaaaaaaaaaaaa,,,,,,,,,"+options.size());
					System.out.println("text,,,,,,,,,"+options.get(i).getText());
					if (dropDownSelectedValue.equalsIgnoreCase(options.get(i).getText())) {
						System.out.println("dropDownSelectedValue,,,,,,,,,,::"+dropDownSelectedValue);
						WebElement DropDownText = options.get(i);
						System.out.println("DropDownText,,,,,,,,,,::"+options.get(i).getText());

						je.executeScript("arguments[0].scrollIntoView(true);", DropDownText);
						Thread.sleep(2000);
						DropDownText.click();
						log.info(dropDownSelectedValue + "- dropdown value is selected");
						break;
					}
				} catch (Exception ex) {
					log.info("exception found");
					break;
				}
		else
			log.info("No record found in dropdown.");
	}

	/*
	 * switch to parent window
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public void switchToParentWindow() {
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window))
				driver.switchTo().window(child_window);
			// System.out.println(driver.switchTo().window(child_window).getTitle());
			// driver.close();
		}

	}

	/**
	 * Function for select value from dropdown after search
	 * 
	 * @param dropDownSelectedValue Value want to select from dropdown
	 * @param dropdownInput
	 * @param dropdownOption        Xpath for all options present in the dropdown
	 * @param dropdownAllElements   Xpath for all dropdown elements.
	 * @throws InterruptedException
	 */
	public void searchAndselectDropDownValue(String dropDownSelectedValue, By dropdownInput, By dropdownOption,
			By dropdownAllElements) throws InterruptedException {
		implicitWaitToAnElement(50);
		driver.findElement(dropdownOption).click();
		driver.findElement(dropdownInput).sendKeys(dropDownSelectedValue);
		;
		Thread.sleep(2000);
		List<WebElement> options = driver.findElements(dropdownAllElements);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		for (int i = 0; i < options.size(); i++)
			try {
				if (dropDownSelectedValue.equalsIgnoreCase(options.get(i).getText())) {
					WebElement DropDownText = options.get(i);
					je.executeScript("arguments[0].scrollIntoView(true);", DropDownText);
					Thread.sleep(1000);
					DropDownText.click();
					break;
				}
			} catch (Exception ex) {
				System.out.println("exception found");
				break;
			}
	}

	/**
	 * Function for random string generator
	 * 
	 * @param length of the string to generate
	 * @return String
	 */
	public String randomStringGenerator(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * Function for current date picker
	 * 
	 * @param null
	 * @return null
	 */
	public String currentDatePickker() {
		return null;
	}

	/**
	 * Function for type in field
	 * 
	 * @param xpath Xpath for field
	 * @param value Value
	 */
	public void TypeInField(WebElement xpath, String value) {
		String val = value;
		WebElement element = xpath;
		element.clear();
		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
		}

	}

	/**
	 * Function to check values sorted or not
	 * 
	 * @param dropDownValues Dropdown values
	 * @return
	 */
	public boolean sortedOrNot(ArrayList<String> dropDownValues) {
		System.out.println("number of values sortedOrNot function" + dropDownValues.size());
		for (int i = 0; i < dropDownValues.size(); i++) {
			int temp = dropDownValues.get(i).compareTo(dropDownValues.get(i + 1));
			System.out.println(temp);

			if (temp > 1) {
				System.out.println("i value" + i);
				return false;
			} else
				System.out.println("i value is greater than 1");
		}
		return true;
	}

	/**
	 * Function for type in field
	 * 
	 * @param xpath Xpath of element
	 * @param value Integer value
	 */
	public void TypeInFieldInteger(WebElement xpath, int value) {
		int val = value;
		// WebElement element = driver.findElement(By.xpath(xpath));
		WebElement element = xpath;
		element.clear();
		String s = null;

		for (int i = 0; i < val; i++) {
			// char c = val.charAt(i);
			s = new StringBuilder().append(i).toString();
			element.sendKeys(s);

		}
		// TODO Auto-generated method stub

	}

	/**
	 * Function for select value from dropdown list
	 * 
	 * @param dropDownClick   By for click on dropdown
	 * @param data            String data value
	 * @param dropDownAllData By for dropdown all data
	 * @throws InterruptedException
	 */
	public void dropdownList(By dropDownClick, String data, By dropDownAllData) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(dropDownClick).click();
		implicitWaitToAnElement(40);
		List<WebElement> we = driver.findElements(dropDownAllData);
		implicitWaitToAnElement(40);
		for (int j = 0; j <= we.size(); j++)
			if (we.get(j).getText().equalsIgnoreCase(data)) {
				we.get(j).click();
				break;
			} else {
				// System.out.println("Not found");
			}
	}

	/**
	 * 
	 * @param dropDownClick   By for click on dropDown.
	 * @param num
	 * @param dropDownAllData
	 */
	public void dropdownListVerifyExpList(By dropDownClick, String num[], By dropDownAllData) {
		int count = 0;
		implicitWaitToAnElement(40);
		driver.findElement(dropDownClick).click();
		implicitWaitToAnElement(40);
		List<WebElement> we = driver.findElements(dropDownAllData);
		loop: for (WebElement options : we)
			for (int i = 0; i < num.length; i++)
				if (options.getText().equals(num[i]))
					count++;
		if (count == num.length)
			System.out.println("matched");
		else
			System.out.println("not matched");
	}

	/**
	 * Function for get random number
	 * 
	 * @param min integer value for minimum range
	 * @param max integer value for maximum range
	 * @return
	 */
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max)
			throw new IllegalArgumentException("max must be greater than min");

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * Function for select date
	 * 
	 * @param datePicker
	 * @param DatePickerTable
	 * @param data
	 */
	public void datePicker(WebElement datePicker, WebElement DatePickerTable, String data) {
		(datePicker).click();

		WebElement dateWidgetFrom = (DatePickerTable);
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

		for (WebElement cell : columns)
			if (cell.getText().equals(data)) {
				cell.click();
				break;
			}

	}

	/**
	 * Function for get current day.
	 * 
	 * @param null
	 * @return Current Day
	 */
	public String getCurrentDay() {
		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");

		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
	}

	/**
	 * Function for wait
	 * 
	 * @param element
	 * @param timeSecond
	 */
	public void waitForVisibility(By element, int timeSecond) {

		new WebDriverWait(driver, timeSecond).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
		// TODO Auto-generated method stub

	}

	/**
	 * Function for current date
	 * 
	 * @param null
	 * @return Current Date
	 */
	public String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Function for current time
	 * 
	 * @param null
	 * @return Current time
	 */
	public String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	/**
	 * @author ankitra This method will scroll the page vertically.
	 * @param null
	 */
	public void scrollPageVertically() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * @author ankitra This method will wait for complete page load.
	 * @param null
	 */
	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// This loop will rotate for 25 times to check If page Is ready after every 1
		// second.
		// You can replace your value with 25 If you wants to Increase or decrease wait
		// time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				log.info("Page is Ready");
				break;
			}
			log.info("Page is not Ready");
		}
	}

	// Aditya code

	/**
	 * Function for wait until element not present
	 * 
	 * @param value
	 * @param timeSecond
	 */
	public void presenceOfElement(By value, int timeSecond) {
		implicitWaitToAnElement(40);
		WebDriverWait wait = new WebDriverWait(driver, timeSecond);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(value));

	}

	/**
	 * Function for select required date
	 * 
	 * @param value Value of date
	 * @return String selected date
	 */
	public String selectRequiredDate(int value) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, value);
		String selectedDate = dateFormat.format(cal.getTime());
		return selectedDate;
	}

	/**
	 * Function to wait for element
	 * 
	 * @param value Xpath of element
	 */
	public void waitForElement(WebElement value) {
		// wait for field
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(value));

	}

	/**
	 * Function for capture screeenshot
	 * 
	 * @param ldriver Current Webdriver
	 */
	public void captureScreenShot(WebDriver ldriver) {
		// Take screenshot and store as a file format � � � � � �
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the� screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File("C:/selenium/" + System.currentTimeMillis() + ".png"));
			System.out.print(new File("C:/selenium/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Function for verify search data on list
	 * 
	 * @param dataToBeVerified String
	 * @param xPathTableRow    String
	 * @param column           int
	 * @return
	 */
	public boolean verifySearchDataOnList(String dataToBeVerified, String xPathTableRow, int column) {
		implicitWaitToAnElement(40);
		// List<String> allData = new ArrayList<String>();
		List<WebElement> allRows = driver.findElements(By.xpath(xPathTableRow));
		if (allRows.size() > 0)
			for (int i = 1; i <= allRows.size(); i++)
				try {
					if (i < 20) {
						Thread.sleep(2000);
						String actualData = driver
								.findElement(By.xpath(xPathTableRow + "[" + i + "]/td[" + column + "]")).getText();
						Assert.assertEquals(dataToBeVerified, actualData);
					}
				} catch (Exception e) {
					return false;
				}
		else {
			log.info("No record found.");
			return true;
		}
		return true;
	}

	/**
	 * @author priyankag This method will scroll the page vertically.
	 * @param null
	 */

	/*
	 * public boolean isElementEnabled(WebElement element){ return
	 * element.isEnabled(); }
	 */

	/**
	 * Function for wait as per visibility of element
	 * 
	 * @param element
	 */
	public void waitForVisibility(WebElement element) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * Function for presence of element located
	 * 
	 * @param element
	 */
	public void waitForPresenceOfElementLocated(By element) {

		new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(element));
	}

	/**
	 * Function for check element is present
	 * 
	 * @param element
	 * @return Boolean value
	 */
	public boolean isElementPresent(List<WebElement> element) {
		Boolean presenceOfElement = element.size() > 0;
		return presenceOfElement;
	}

	/**
	 * Function for presence of element
	 * 
	 * @param selector
	 * @param timeOutInSeconds
	 */
	public void findElement(By selector, long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));

	}

	/**
	 * Function to check is element enabled
	 * 
	 * @param element Xpath of element
	 * @return Boolean value
	 */
	public boolean checkElementIsEnabled(WebElement element) {
		Boolean isEnabled = (element).isEnabled();
		log.info("isEnabled =" + isEnabled);
		Boolean expectedPresenceOfElement = true;
		if (isEnabled.equals(expectedPresenceOfElement)) {
			(element).click();
			Assert.assertEquals(isEnabled, expectedPresenceOfElement);
		} else
			Assert.assertEquals(isEnabled, expectedPresenceOfElement);
		return isEnabled;
	}

	/**
	 * Function for assert true functionality
	 * 
	 * @param condition Condition for assertTrue
	 */
	public void assertTrue(boolean condition) {
		try {
			Assert.assertTrue(condition);
		} catch (AssertionError failure) {
			System.out.println(failure);
		}
	}

	/**
	 * Function for assert false functionality
	 * 
	 * @param condition Condition for assert false
	 */
	public void assertFalse(boolean condition) {
		try {
			Assert.assertFalse(condition);
		} catch (AssertionError failure) {

		}
	}

	/**
	 * Function for assert equals functionality
	 * 
	 * @param expected Object
	 * @param actual   Object
	 */
	public void assertEquals(Object expected, Object actual) {
		try {
			Assert.assertEquals(expected, actual);
		} catch (AssertionError failure) {
			System.out.println(failure);
		}
	}

	/**
	 * Function for check sorting of dropdown options
	 * 
	 * @param dropDownValues
	 * @return Boolean
	 */
	public boolean sortedOrNotDropdownOptions(ArrayList<String> dropDownValues) {
		System.out.println("number of values " + (dropDownValues.size()));
		for (int i = 0; i < dropDownValues.size(); i++) {
			int temp = dropDownValues.get(i).compareTo(dropDownValues.get(i + 1));
			System.out.println("temp=" + temp);
			if (temp > 1) {
				System.out.println("i value" + i);
				return false;
			}
		}
		return true;
	}

	/**
	 * Function for click on element after check element is enabled and displayed
	 * 
	 * @param element
	 */
	public void iconEnabledAndPresence(WebElement element) {

		boolean searchIconPresence = element.isDisplayed();
		boolean searchIconEnabled = element.isEnabled();
		if (searchIconPresence == true && searchIconEnabled == true)
			element.click();
	}

	/*
	 * Get Data From Excel Sheet according to key from arrayData
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public Object[][] getFilterDataFromExcelSheetSpark(Object[][] arrayData, String key) {

		ArrayList<List<String>> alistFilterredValue = null;
		Object[][] commonData11 = null;
		int k = 0;
		try {
			for (int i = 0; i < arrayData.length; i++) {
				Object[] value = arrayData[i];
				/*
				 * log.info("No of rows"+arrayData.length);
				 * log.info("No Of Columns"+value.length);
				 */
				// for (int j = 0; j < value.length; j++) {
				// System.out.println("arr[" + i + "][" + j + "] = "+ arrayData[i][j]);
				if (arrayData[i][0].equals(key)) {
					if (alistFilterredValue == null)
						alistFilterredValue = new ArrayList<>();
					List al = Arrays.asList(arrayData[i]);
					alistFilterredValue.add(al);
				}
				//					else if (arrayData[i][0].equals(key)) {
				//						if (alistFilterredValue == null)
				//							alistFilterredValue = new ArrayList<>();
				//						List al = Arrays.asList(arrayData[i]);
				//						alistFilterredValue.add(al);
				//					}
				// }
			}
		} catch (Exception ex) {

		}
		commonData11 = new Object[alistFilterredValue.size()][];
		// System.out.println("1377----------:"+alistFilterredValue.size());
		for (int i = 0; i < alistFilterredValue.size(); i++) {
			commonData11[i] = new Object[alistFilterredValue.get(i).size()];
			List list = alistFilterredValue.get(i);
			for (int j = 0; j < list.size(); j++)
				if (list.get(j) != null)
					commonData11[i][j] = list.get(j);
		}
		/*
		 * for(Object[] str: commonData11) { log.info("ARRAY  ****** " + str);
		 * for(Object strV : str) { if(strV !=null) log.info("VALUE  ****** " + strV); }
		 * }
		 */
		return commonData11;
	}

	/*
	 * public void pageRefreshed(By element) { WebDriverWait wait = new
	 * WebDriverWait(driver, 5); wait.until(presenceOfElementLocated(element));
	 * //wait.until(presenceOfElementLocated(By.id("container-element"))); }
	 */

	/*
	 * private Function presenceOfElementLocated(By element) { // TODO
	 * Auto-generated method stub return null; }
	 */
	/*
	 * public void waitForVisibility(By element, int timeSecond ) throws Error {
	 * //new WebDriverWait(driver,
	 * timeSecond).until(ExpectedConditions.visibilityOf(element));
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, timeSecond);
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)); }
	 */
	/*
	 * public void waitForVisibility(WebElement element) throws Error { new
	 * WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element)); }
	 */

	/**
	 * Function for set data in the field
	 * 
	 * @param element
	 * @param data
	 */
	public void setDatainField(WebElement element, String data) {
		implicitWaitToAnElement(40);
		element.sendKeys(data);
	}

	/**
	 * Function for clear data from field
	 * 
	 * @param element
	 */
	public void clearDatafromField(WebElement element) {

		element.clear();
	}
	/*
	 * public void getDropDownAllOptions(WebElement element) { Select
	 * SelectStatusDropdownInPopUpWindow = new Select(element); String
	 * getStatusDropdownInPopUpWindow = actions
	 * .getText(SelectStatusDropdownInPopUpWindow.getFirstSelectedOption());
	 * System.out.println("SelectStatusDropdownInPopUpWindow  =" +
	 * getStatusDropdownInPopUpWindow); java.util.List<WebElement> options =
	 * SelectStatusDropdownInPopUpWindow.getOptions();
	 * System.out.println("Dropdown values size are "+ options.get(2));
	 * for(WebElement item:options) {
	 * 
	 * System.out.println("Dropdown values are "+ item.getText());
	 * 
	 * }
	 * 
	 * 
	 * actions.clickOnElement(ObjManageSalesOrder.getCloseButtonInStatusPopUp()); }
	 * }
	 */

	/**
	 * Function for click on element
	 * 
	 * @param element
	 */
	public void click(WebElement element) {
		element.click();
	}

	/*
	 * public int getRandomNumberInRange(int min, int max) {
	 * 
	 * if (min >= max) { throw new
	 * IllegalArgumentException("max must be greater than min"); }
	 * 
	 * Random r = new Random(); return r.nextInt((max - min) + 1) + min; }
	 */

	/*
	 * public void TypeInField(WebElement xpath, String value) { String val = value;
	 * // WebElement element = driver.findElement(By.xpath(xpath)); WebElement
	 * element = xpath; element.clear();
	 * 
	 * for (int i = 0; i < val.length(); i++) { char c = val.charAt(i); String s =
	 * new StringBuilder().append(c).toString(); element.sendKeys(s); }
	 * 
	 * }
	 * 
	 * public void waitForElement(WebElement value) { // wait for field
	 * WebDriverWait wait = new WebDriverWait(driver, 60);
	 * 
	 * wait.until(ExpectedConditions.visibilityOf(value));
	 * 
	 * 
	 * }
	 * 
	 * public void presenceOfElement(By value , int timeSecond) { WebDriverWait wait
	 * = new WebDriverWait(driver, timeSecond); WebElement element =
	 * wait.until(ExpectedConditions.presenceOfElementLocated(value));
	 * 
	 * 
	 * }
	 * 
	 * public boolean sortedOrNot(ArrayList<String> dropDownValues) {
	 * System.out.println("number of values" + dropDownValues.size()); for (int i =
	 * 0; i < dropDownValues.size(); i++) { int temp =
	 * dropDownValues.get(i).compareTo(dropDownValues.get(i + 1));
	 * System.out.println(temp);
	 * 
	 * if (temp > 1) { System.out.println("i value" + i); return false; } else {
	 * System.out.println("i value is greater than 1"); } } return true; }
	 * 
	 * public void TypeInFieldInteger(WebElement xpath, int value) { int val =
	 * value; // WebElement element = driver.findElement(By.xpath(xpath));
	 * WebElement element = xpath; element.clear(); String s = null;
	 * 
	 * for (int i = 0; i < val; i++) { // char c = val.charAt(i); s = new
	 * StringBuilder().append(i).toString(); element.sendKeys(s);
	 * 
	 * } // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * /*public void dropdownUsingSelect(WebElement element, String data) {
	 * 
	 * Select oSelect = new Select(driver.findElement(By.id("continents")));
	 * 
	 * 
	 * oSelect.selectByVisibleText("Europe"); }
	 */
	/*
	 * public void dropdownList(By dropDownClick, String data, By dropDownAllData)
	 * throws InterruptedException { driver.findElement(dropDownClick).click();
	 * List<WebElement> we = driver.findElements(dropDownAllData);
	 * JavascriptExecutor je = (JavascriptExecutor) driver; for (int j = 0; j <=
	 * we.size(); j++) { if (we.get(j).getText().equalsIgnoreCase(data)) {
	 * WebElement DropDownText = we.get(j);
	 * je.executeScript("arguments[0].scrollIntoView(true);",DropDownText);
	 * Thread.sleep(1000); we.get(j).click(); break; } else { //
	 * System.out.println("Not found"); } } } public void
	 * clearAndSendKeysToElement(WebElement element, String text){ element.clear();
	 * element.sendKeys(text); }
	 */

	/**
	 * Function for enter on element
	 * 
	 * @param element
	 */
	public void SendKeysToElement(WebElement element) {
		implicitWaitToAnElement(30);
		element.sendKeys(Keys.ENTER);
		implicitWaitToAnElement(30);
	}

	/**
	 * Function for press enter after clear element
	 * 
	 * @param element
	 */
	public void clearToElement(WebElement element) {
		element.clear();
		element.sendKeys(Keys.ENTER);
	}

	/**
	 * Function for send tab key to an element
	 * 
	 * @param element
	 */
	public void SendTabToElement(WebElement element) {
		implicitWaitToAnElement(30);
		element.sendKeys(Keys.TAB);
	}

	/**
	 * Function for send any String to element
	 * 
	 * @param element
	 * @param text
	 */
	public void sendAnyKeyToElement(WebElement element, String text) {
		element.sendKeys(text);
	}

	/*
	 * public boolean isElementDisplayed(WebElement element){
	 * applyImplicitWaitToAnElement(element); return element.isDisplayed(); }
	 */
	public void sendTabEnterElement(WebElement element) {
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.ENTER);
	}

	public void applyImplicitWaitToAnElement(WebElement element) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/*
	 * public void pageLoadTimeOut() {
	 * driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); }
	 */

	/*
	 * public void clickOnElement(WebElement element){ JavascriptExecutor js =
	 * (JavascriptExecutor)driver; js.executeScript("arguments[0].click();",
	 * element); //element.click(); }
	 */

	/*
	 * public void scrollPageVertically() { JavascriptExecutor je =
	 * (JavascriptExecutor) driver;
	 * je.executeScript("window.scrollTo(0,document.body.scrollHeight)"); }
	 */
	/*
	 * public String getText(WebElement element){
	 * 
	 * return element.getText(); }
	 */

	/**
	 * Function for get attribute value from field
	 * 
	 * @param element Xpath of element
	 * @return value of attribute
	 */
	public String getValue(WebElement element) {

		return element.getAttribute("value");
	}
	/*
	 * public void searchAndselectDropDownValue(String dropDownSelectedValue, By
	 * dropdownInput, By dropdownOption, By dropdownAllElements) throws
	 * InterruptedException { driver.findElement(dropdownOption).click();
	 * driver.findElement(dropdownInput).sendKeys(dropDownSelectedValue);;
	 * Thread.sleep(1000); List<WebElement> options =
	 * driver.findElements(dropdownAllElements);
	 * 
	 * JavascriptExecutor je = (JavascriptExecutor) driver;
	 * 
	 * for (int i = 0; i < options.size(); i++) {
	 * System.out.println(options.get(i).getText()); try {
	 * if(dropDownSelectedValue.equalsIgnoreCase(options.get(i).getText())) {
	 * WebElement DropDownText = options.get(i);
	 * je.executeScript("arguments[0].scrollIntoView(true);",DropDownText);
	 * Thread.sleep(1000); DropDownText.click(); break; } } catch(Exception ex) {
	 * System.out.println("exception found"); break; } } }
	 * 
	 * public String getCurrentDay (){ Calendar calendar =
	 * Calendar.getInstance(TimeZone.getDefault()); int todayInt =
	 * calendar.get(Calendar.DAY_OF_MONTH); // System.out.println("Today Int: " +
	 * todayInt +"\n"); String todayStr = Integer.toString(todayInt); //
	 * System.out.println("Today Str: " + todayStr + "\n");
	 * 
	 * return todayStr; }
	 * 
	 * public void dropdownListVerifyExpList(By dropDownClick, String num[], By
	 * dropDownAllData) { int count = 0; driver.findElement(dropDownClick).click();
	 * 
	 * List<WebElement> we = driver.findElements(dropDownAllData); loop: for
	 * (WebElement options : we) { for (int i = 0; i < num.length; i++) { if
	 * (options.getText().equals(num[i])) { count++; } } } if (count == num.length)
	 * { System.out.println("matched"); } else { System.out.println("not matched");
	 * } }
	 */

	/**
	 * Function for select today date
	 * 
	 * @return String Current date
	 */
	public String selectTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String todayDate = dateFormat.format(cal.getTime());
		return todayDate;
	}

	/*
	 * public String selectRequiredDate(int value) { DateFormat dateFormat = new
	 * SimpleDateFormat("dd/MM/yyyy"); Calendar cal = Calendar.getInstance();
	 * cal.setTime(new Date()); cal.set(Calendar.DATE, value); String selectedDate =
	 * dateFormat.format(cal.getTime()); return selectedDate; }
	 */

	/**
	 * Function for select future date
	 * 
	 * @return String
	 */
	public String selectFutureDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 3);
		String futureDate = dateFormat.format(cal.getTime());
		return futureDate;
	}

	/**
	 * Function for scrolling to bottom of a page
	 * 
	 * @param url
	 */
	public void scrollingToBottomofAPage(String url) {
		driver.navigate().to(url);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Function for scrolling to element
	 * 
	 * @param url
	 */
	public void scrollingToElementofAPage(String url) {
		driver.navigate().to(url);
		WebElement element = driver.findElement(By.linkText("Import/Export"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * Function for scroll to bottom
	 * 
	 * @param driver
	 */
	public void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Function for scroll up
	 * 
	 * @param driver
	 */
	public void scrollUp(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	/**
	 * Function for scroll to element
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollTo(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	// Scroll Horizontally
	public void scrollRight(WebDriver driver) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(1000,0)");
	}

	/*
	 * Capture screenshot function Parameter - driver, url , methodName
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public void captureScreenShot(WebDriver ldriver, String url, String methodName) {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		// Take screenshot and store as a file format � � � � � �
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the� screenshot to desired location using copyFile method
			File screenshotName = new File(url + methodName + "-" + timestamp + ".png");
			FileUtils.copyFile(src, screenshotName);
			Reporter.log("<br><img src='" + screenshotName + "' height='300' width='300'/><br>");

		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Function for get screenshot in particular location
	 * 
	 * @param screenshotName
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public String getScreenshot(String screenshotName, WebDriver driver) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String imageName = screenshotName + timestamp;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String location = System.getProperty("user.dir") + "/test-output/screenshot/" + imageName + ".png";
		File screenshotLocation = new File(location);
		FileUtils.copyFile(source, screenshotLocation);
		return location;

	}

	/*
	 * Capture screenshot function Parameter - driver, methodName
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public void captureScreenShotUrl(WebDriver ldriver, String methodName) {
		String screenshotUrl = System.getProperty("user.dir") + "/../Library/resources/screenshot/";

		// Take screenshot and store as a file format � � � � � �
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the� screenshot to desired location using copyFile method
			File screenshotName = new File(screenshotUrl + methodName + "-" + System.currentTimeMillis() + ".png");
			FileUtils.copyFile(src, screenshotName);
			Reporter.log("<br><img src='" + screenshotName + "' height='300' width='300'/><br>");

		} catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Function for select checkbox field
	 * 
	 * @param element
	 */
	public void Select_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected())
				System.out.println("Checkbox: " + element + "is already selected");
			else
				// Select the checkbox
				element.click();
		} catch (Exception e) {
			System.out.println("Unable to select the checkbox: " + element);
		}

	}

	/**
	 * Function for select radiobutton
	 * 
	 * @param element
	 */
	public void Select_The_RadioButton(WebElement element) {
		try {
			if (element.isSelected())
				System.out.println("RadioButton: " + element + "is already selected");
			else
				// Select the radioButton
				element.click();
		} catch (Exception e) {
			System.out.println("Unable to select the radioButton: " + element);
		}

	}

	/**
	 * Function for deselect the checkbox
	 * 
	 * @param element
	 */
	public void DeSelect_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected())
				// De-select the checkbox
				element.click();
			else
				System.out.println("Checkbox: " + element + "is already deselected");
		} catch (Exception e) {
			System.out.println("Unable to deselect checkbox: " + element);
		}
	}

	/**
	 * Function for the select check box from list
	 * 
	 * @param element
	 * @param valueToSelect
	 */
	public void Select_The_CheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions)
			if (valueToSelect.equals(option.getText())) {
				option.click();
				break;
			}
	}

	/*
	 * public Properties loadPropertyFile(String filePath) throws IOException { //
	 * Loading Properties file File file=new File(filePath); FileInputStream fis=new
	 * FileInputStream(file); Properties prop=new Properties(); prop.load(fis);
	 * 
	 * return(prop); } public void checkPageIsReady() {
	 * 
	 * JavascriptExecutor js = (JavascriptExecutor)driver;
	 * 
	 * //This loop will rotate for 25 times to check If page Is ready after every 1
	 * second. //You can replace your value with 25 If you wants to Increase or
	 * decrease wait time. for (int i = 0; i < 25; i++) { try { Thread.sleep(1000);
	 * }catch (InterruptedException e) { System.out.println(e); } //To check page
	 * ready state. if
	 * (js.executeScript("return document.readyState").toString().equals("complete")
	 * ) { log.info("Page is Ready"); break; } log.info("Page is not Ready"); } }
	 */

	String parentWindowHandler;

	/**
	 * Function for switch to other window
	 */
	public void switchToOtherWindow() {
		parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext())
			subWindowHandler = iterator.next();
		driver.switchTo().window(subWindowHandler); // switch to popup window
	}

	/**
	 * Function for switch to main window
	 */
	public void switchToMainWindow() {
		driver.switchTo().window(parentWindowHandler);
	}

	/*
	 * Get Data From Excel Sheet according to key from arrayData
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public Object[][] getFilterDataFromExcelSheet(Object[][] arrayData, String key) {

		ArrayList<List<String>> alistFilterredValue = null;
		Object[][] commonData11 = null;
		int k = 0;
		try {
			for (int i = 0; i < arrayData.length; i++) {
				Object[] value = arrayData[i];
				/*
				 * log.info("No of rows"+arrayData.length);
				 * log.info("No Of Columns"+value.length);
				 */
				for (int j = 0; j < value.length; j++)
					// System.out.println("arr[" + i + "][" + j + "] = "+ arrayData[i][j]);
					if (arrayData[i][j].equals(key)) {
						if (alistFilterredValue == null)
							alistFilterredValue = new ArrayList<>();
						List al = Arrays.asList(arrayData[i]);
						alistFilterredValue.add(al);
					}
			}
		} catch (Exception ex) {

		}
		commonData11 = new Object[alistFilterredValue.size()][];
		for (int i = 0; i < alistFilterredValue.size(); i++) {
			commonData11[i] = new Object[alistFilterredValue.get(i).size()];
			List list = alistFilterredValue.get(i);
			for (int j = 0; j < list.size(); j++)
				if (list.get(j) != null)
					commonData11[i][j] = list.get(j);
		}
		/*
		 * for(Object[] str: commonData11) { log.info("ARRAY  ****** " + str);
		 * for(Object strV : str) { if(strV !=null) log.info("VALUE  ****** " + strV); }
		 * }
		 */
		return commonData11;
	}

	/*
	 * Convert Array To List
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public static <T> List<T> convertArrayToList(T array[]) {
		// Create an empty List
		List<T> list = new ArrayList<>();
		// Iterate through the array
		for (T t : array)
			// Add each element into the list
			list.add(t);
		// Return the converted List
		return list;
	}

	/*
	 * Function for get particular value from list if present.
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public String getOptionsList(String value, List<WebElement> optionsList) throws InterruptedException {
		String listValue = "";
		for (WebElement we : optionsList)
			if (we.getText().contains(value))
				listValue = we.getText();
		return listValue;
	}

	/*
	 * Implicit Wait Function With parameter
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public void implicitWaitToAnElement(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/*
	 * Get Text With wait
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public String getTextWithWait(By element) {
		wait = new WebDriverWait(driver, 60);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		return webElement.getText();
	}
	/*
	 * Wait For Element Until Element Clickable
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public void waitUntilElementClickable(By element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Send Keys With wait
	public void sendKeysWithWait(By element, String text) {
		wait = new WebDriverWait(driver, 60);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		webElement.sendKeys(text);
	}

	// Click On Element Using JavascriptExecutor
	public void clickOnElementWithJS(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/*
	 * Function For Wait For Page To Load - Method #1
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/*
	 * Function For Wait For Page To Load - Method #2
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(pageLoadCondition);
	}

	/*
	 * isClick function for Verify element is clickable or not and click on element
	 * Parameter - element
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public boolean isClick(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * isClick function for Verify element is clickable or not Parameter - element
	 * 
	 * @throws Exception
	 * 
	 * @author priyankag
	 */

	public boolean isClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verify, Control Of Field 01/08/2020
	 * 
	 * @author priyankag
	 * @throws InterruptedException
	 */

	public void verifyControlOfField(String fieldName, By fieldXpath, SoftAssert softAssert) {
		waitForPageLoaded();
		try {
			if (isElementPresent(driver.findElements(fieldXpath))) {
				clickOnElement(driver.findElement(fieldXpath));
				implicitWaitToAnElement(30);
				if (fieldName.contains("Date Field")) {
					driver.findElement(fieldXpath).sendKeys(Keys.ENTER);
					implicitWaitToAnElement(30);
				}
			} else
				// TODO: handle exception
				softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
						fieldName + " - Is not Present.");
			// Assert.assertTrue(isElementDisplayed(driver.findElement(fieldXpath)),fieldName+"
			// - Is not Displayed.");
			// Assert.assertTrue(isElementEnabled(driver.findElement(fieldXpath)),fieldName+"
			// - Is not Enabled.");
		} catch (NoSuchElementException e1) {
			// TODO: handle exception
			softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
					fieldName + " - Is not Present on the page.");

		}

	}

	/**
	 * Verify, Select Dropdown Of Field 01/08/2020
	 * 
	 * @author priyankag
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws InterruptedException
	 */

	public Boolean verifySelectDropdownField(String fieldName, String fieldValue, By fieldXpath, SoftAssert softAssert,
			Boolean testedData) throws GeneralSecurityException, IOException, InterruptedException {
		waitForPageLoaded();
		Boolean foundValue = false;
		try {
			if (isElementPresent(driver.findElements(fieldXpath))) {
				if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {
					// new WebDriverWait(driver,
					// 40).until(ExpectedConditions.visibilityOf(driver.findElement(fieldXpath)));
					implicitWaitToAnElement(50);
					Select option = new Select(driver.findElement(fieldXpath));
					List<WebElement> fieldDropDownOptions = option.getOptions();
					if (isElementPresent(fieldDropDownOptions)) {
						if (fieldDropDownOptions.size() > 0) {
							for (WebElement item : fieldDropDownOptions)
								if ((item.getText()).replaceAll("( +)", " ").trim().equalsIgnoreCase(fieldValue)) {
									foundValue = true;
									try {
										option.selectByVisibleText(fieldValue);
										Thread.sleep(1000);
										implicitWaitToAnElement(30);
										Reporter.log("The " + fieldName + " " + fieldValue + " is selected Now.");
									} catch (ElementNotInteractableException e) {
										// TODO: handle exception
										softAssert.assertTrue(false, "Not Interactable options in " + fieldName
												+ " dropdown .In case of filter may be issue occured related to filter button.");
									}
									break;
								}
							if (foundValue) {

								if (testedData != null && testedData == false)
									testedData = false;
								else
									testedData = true;
							} else {
								testedData = false;
								softAssert.assertTrue(foundValue,
										"The " + fieldName + " " + fieldValue + " Not Present.");

							}
							Thread.sleep(3000);
						} else {
							testedData = false;
							Reporter.log("No Data Found in the" + fieldName + " Dropdown Field.");
							softAssert.assertTrue(fieldDropDownOptions.size() > 0,
									"No Record match in the" + fieldName + " Dropdown Field.");
						}
					} else {
						testedData = false;
						softAssert.assertTrue(isElementPresent(fieldDropDownOptions),
								"Options in " + fieldName + " dropdown not present. ");
					}
				} else if (fieldValue.equalsIgnoreCase("")) {
					testedData = true;
					softAssert.assertTrue(false,
							"The Value Of " + fieldName + " Not Present in the data sheet.Please Verify Data.");

				}
				/*
				 * else { testedData = false; Reporter.log("The Value Of"+fieldName+
				 * " Not Present.Please Verify Data.");
				 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
				 * " Should Not Be Blank."); }
				 */
			} else
				softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
						fieldName + " dropdown not present. ");
		} catch (NoSuchElementException e1) {
			Reporter.log("The - " + fieldName + " Not showing..please verify on the page.");
			softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
					"The - " + fieldName + " Not showing..please verify on the page.");
		} catch (Exception e) {
			Reporter.log("In The " + fieldName + " " + fieldValue + " Exception occured.");
			softAssert.assertTrue(foundValue, "In The " + fieldName + " " + fieldValue + " Exception occured.");

		}
		return testedData;
	}

	/**
	 * Verify, Search and Select Dropdown Of Field 01/08/2020
	 * 
	 * @author priyankag
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws InterruptedException
	 */

	public Boolean verifySearchAndSelectDropdownField(String fieldName, String fieldValue, By dropdownInput,
			By dropdownOptions, By dropDownAllElement, SoftAssert softAssert, Boolean testedData)
					throws GeneralSecurityException, IOException, InterruptedException {
		Boolean foundValue = false;
		waitForPageLoaded();
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {
				// new WebDriverWait(driver,
				// 40).until(ExpectedConditions.visibilityOf(driver.findElement(dropdownOptions)));
				if (isElementPresent(driver.findElements(dropdownOptions))) {
					implicitWaitToAnElement(50);
					clickOnElement(driver.findElement(dropdownOptions));
					implicitWaitToAnElement(50);
					sendAnyKeyToElement(driver.findElement(dropdownInput), fieldValue);
					Thread.sleep(2000);
					List<WebElement> fieldDropDownOptions = driver.findElements(dropDownAllElement);
					if (fieldDropDownOptions.size() > 0) {
						for (WebElement item : fieldDropDownOptions)
							if (item.getText().equalsIgnoreCase(fieldValue)) {
								foundValue = true;
								implicitWaitToAnElement(50);
								je.executeScript("arguments[0].scrollIntoView(true);", item);
								Thread.sleep(2000);
								item.click();
								implicitWaitToAnElement(30);
								waitForPageLoaded();
								implicitWaitToAnElement(30);
								scrollUp(driver);
								Thread.sleep(3000);
								// Reporter.log("The "+fieldName+" "+fieldValue+ " is selected Now.");
								break;
							}
						if (foundValue) {

							if (testedData != null && testedData == false)
								testedData = false;
							else
								testedData = true;
						} else {
							testedData = false;
							softAssert.assertTrue(foundValue, fieldValue + " In The " + fieldName + " Not Present.");
							Thread.sleep(3000);
							clickOnElement(driver.findElement(dropdownOptions));

						}
						Thread.sleep(3000);
					} else {

						// When data not match in the dropdown filed or empty dropdown
						Reporter.log("No Data Match in the" + fieldName + " Dropdown Field.");
						softAssert.assertTrue(fieldDropDownOptions.size() > 0,
								"No Record match in the " + fieldName + " Dropdown Field.");
					}
				} else if (fieldValue.equalsIgnoreCase("")) {
					testedData = true;
					softAssert.assertTrue(false,
							"The Value Of " + fieldName + " Not Present in the data sheet.Please Verify Data.");

				}
				/*
				 * else { Reporter.log("The Value Of"+fieldName+
				 * " Not Present.Please Verify Data.");
				 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
				 * " Should Not Be Blank."); }
				 */
			} else {
				testedData = false;
				softAssert.assertTrue(isElementPresent(driver.findElements(dropdownOptions)),
						"The - " + fieldName + " is not present. please verify on the page.");
			}
		} catch (NoSuchElementException e1) {
			String dropdownDisable = dropdownOptions.toString() + "[@tabindex=-1]";
			int index = dropdownDisable.indexOf(":") + 1;
			dropdownDisable = dropdownDisable.substring(index, dropdownDisable.length());
			if (isElementPresent(driver.findElements(By.xpath(dropdownDisable))))
				// System.out.println("The - "+fieldName+ " is disabled..please verify on the
				// page.");
				Reporter.log("The - " + fieldName + " is disabled..please verify on the page.");
			else {
				Reporter.log("The - " + fieldName + " Not showing..please verify on the page.");
				softAssert.assertTrue(isElementPresent(driver.findElements(dropDownAllElement)),
						"The - " + fieldName + " Not showing..please verify on the page.");
			}
		} catch (Exception e) {
			Reporter.log("In The " + fieldName + " " + fieldValue + " Exception occured.");
			softAssert.assertTrue(foundValue, "In The " + fieldName + " " + fieldValue + " Exception occured.");
		}
		return testedData;
	}

	/**
	 * Verify, Customized Dropdown Field 01/08/2020
	 * 
	 * @author priyankag
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws InterruptedException
	 */

	public Boolean verifyCustomizedDropdownField(String fieldName, String fieldValue, By dropdownOptions,
			By dropDownAllElement, SoftAssert softAssert, Boolean testedData)
					throws GeneralSecurityException, IOException, InterruptedException {
		waitForPageLoaded();
		Boolean foundValue = false;
		JavascriptExecutor je = (JavascriptExecutor) driver;
		if (isElementPresent(driver.findElements(dropdownOptions)))
			try {
				if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {
					// new WebDriverWait(driver,
					// 40).until(ExpectedConditions.visibilityOf(driver.findElement(dropdownOptions)));
					implicitWaitToAnElement(50);
					clickOnElement(driver.findElement(dropdownOptions));

					Thread.sleep(2000);
					List<WebElement> fieldDropDownOptions = driver.findElements(dropDownAllElement);
					if (fieldDropDownOptions.size() > 0) {
						for (WebElement item : fieldDropDownOptions)
							if (item.getText().equalsIgnoreCase(fieldValue)) {
								foundValue = true;
								try {
									je.executeScript("arguments[0].scrollIntoView(true);", item);
									Thread.sleep(1000);
									item.click();
									implicitWaitToAnElement(30);
									waitForPageLoaded();
									scrollUp(driver);
									implicitWaitToAnElement(30);
									// Reporter.log("The "+fieldName+" "+fieldValue+ " is selected Now.");
								} catch (ElementNotInteractableException e) {
									// TODO: handle exception
									softAssert.assertTrue(false,
											"Not Interactable options in " + fieldName + " dropdown .");
								}

								break;
							}
						if (foundValue) {

							if (testedData != null && testedData == false)
								testedData = false;
							else
								testedData = true;
						} else {
							testedData = false;
							softAssert.assertTrue(foundValue, fieldValue + " In The " + fieldName + " Not Present.");
							Thread.sleep(3000);
							clickOnElement(driver.findElement(dropdownOptions));
						}

					} else {
						Reporter.log("No Data Found in the" + fieldName + " Dropdown Field.");
						softAssert.assertTrue(fieldDropDownOptions.size() > 0,
								"No Record match in the " + fieldName + " Dropdown Field.");
					}
				} else if (fieldValue.equalsIgnoreCase("")) {
					testedData = true;
					softAssert.assertTrue(false,
							"The Value Of " + fieldName + " Not Present in the data sheet.Please Verify Data.");
				}
				/*
				 * else { Reporter.log("The Value Of"+fieldName+
				 * " Not Present.Please Verify Data.");
				 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
				 * " Should Not Be Blank."); }
				 */
			} catch (NoSuchElementException e1) {

				String dropdownDisable = dropdownOptions.toString() + "[@tabindex=-1]";
				int index = dropdownDisable.indexOf(":") + 1;
				dropdownDisable = dropdownDisable.substring(index, dropdownDisable.length());

				if (isElementPresent(driver.findElements(By.xpath(dropdownDisable)))) {
					System.out.println("The - " + fieldName + " is disabled..please verify on the page.");
					Reporter.log("The - " + fieldName + " is disabled..please verify on the page.");
				} else {
					Reporter.log("The - " + fieldName + " Not showing..please verify on the page.");
					softAssert.assertTrue(isElementPresent(driver.findElements(dropDownAllElement)),
							"The - " + fieldName + " Not showing..please verify on the page.");
				}

			} catch (Exception e) {
				Reporter.log("In The " + fieldName + " " + fieldValue + " Exception occured.");
				softAssert.assertTrue(foundValue, "In The " + fieldName + " " + fieldValue + " Exception occured.");

			}
		else {
			testedData = false;
			softAssert.assertTrue(isElementPresent(driver.findElements(dropdownOptions)),
					"The - " + fieldName + " Not showing..please verify on the page.");
		}
		return testedData;
	}

	/**
	 * Function for verify button is clickable
	 * 
	 * @param fieldName
	 * @param fieldXpath
	 * @param softAssert
	 */
	public void verifyButton(String fieldName, By fieldXpath, SoftAssert softAssert) {
		try {
			if (isElementPresent(driver.findElements(fieldXpath))) {
				isClickable(driver.findElement(fieldXpath));
				implicitWaitToAnElement(30);
				Reporter.log(fieldName + " clicked Successfully.");
				// TODO: handle exception
				softAssert.assertTrue(isClickable(driver.findElement(fieldXpath)), fieldName + " - Is not clickable..");
			} else
				softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
						fieldName + " - Is not present..");
		} catch (Exception e) {
			// TODO: handle exception
			softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
					fieldName + " - Is not Present on the page.");
		}
	}

	/**
	 * Verify, Field is Clickable 01/08/2020
	 * 
	 * @author priyankag
	 * @throws InterruptedException
	 */
	public void verifyClickableField(String fieldName, By fieldXpath, SoftAssert softAssert) {

		if (isElementPresent(driver.findElements(fieldXpath)))
			softAssert.assertTrue(isClickable(driver.findElement(fieldXpath)), fieldName + " - Is not clickable.");
		else
			softAssert.assertTrue(isElementPresent(driver.findElements(fieldXpath)), fieldName + " -Is not present.");
	}

	/**
	 * Verify, Checkbox option List 24/08/2020
	 * 
	 * @author priyankag
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws InterruptedException
	 */
	public Boolean verifyCheckboxOptionList(String fieldName, String fieldValue, By dropDownAllElement,
			SoftAssert softAssert, Boolean testedData) throws InterruptedException {
		Boolean foundValue = false;
		List<WebElement> fieldDropDownOptions = null;
		try {
			if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {

				fieldDropDownOptions = driver.findElements(dropDownAllElement);
				if (fieldDropDownOptions.size() > 0) {
					if (!fieldValue.equals("All")) {
						for (WebElement item : fieldDropDownOptions)
							if (item.getText().equalsIgnoreCase(fieldValue)) {
								foundValue = true;
								Thread.sleep(1000);
								implicitWaitToAnElement(30);
								break;
							}
					} else if (fieldValue.equals("All"))
						foundValue = true;
					if (foundValue) {

						if (testedData != null && testedData == false)
							testedData = false;
						else
							testedData = true;
					} else {
						testedData = false;
						softAssert.assertTrue(foundValue, fieldValue + " In The " + fieldName + " Not Present.");
					}
				}

				else {
					Reporter.log("No Data Found in the " + fieldName + " Multi select Dropdown Field.");
					softAssert.assertTrue(fieldDropDownOptions.size() > 0,
							" No Record Found in the " + fieldName + " Dropdown Field.");
				}
			} else if (fieldValue.equalsIgnoreCase("")) {
				testedData = true;
				softAssert.assertTrue(false,
						"The Value Of " + fieldName + " Not Present in the data sheet.Please Verify Data.");

			}
			/*
			 * else { Reporter.log("The Value Of"+fieldName+
			 * " Not Present.Please Verify Data.");
			 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
			 * " Should Not Be Blank."); }
			 */
		} catch (NoSuchElementException e1) {
			Reporter.log("The - " + fieldName + " Not showing..please verify on the page.");
			softAssert.assertTrue(isElementPresent(driver.findElements(dropDownAllElement)),
					"The - " + fieldName + " Not showing..please verify on the page.");
		} catch (Exception e) {
			/*
			 * if(isElementPresent(driver.findElements(dropDownAllElement))) {
			 * Reporter.log("In The "+fieldName+" "+fieldValue+ " value Not Showing.");
			 * softAssert.assertTrue(foundValue,"In The "+fieldName+" "+fieldValue+
			 * " value Not Showing.");
			 * 
			 * }
			 * 
			 * else{
			 */
			Reporter.log("In The " + fieldName + " " + fieldValue + " Exception occured.");
			softAssert.assertTrue(foundValue, "In The " + fieldName + " " + fieldValue + " Exception occured.");
			// }
		}
		return testedData;

	}

	/**
	 * Verify, Elemnet Presence and Click 27/08/2020
	 * 
	 * @author priyankag
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws InterruptedException
	 */
	public void verifyElementPresenceClick(String fieldName, By fieldXpath) {
		waitForPageLoaded();
		if (isElementPresent(driver.findElements(fieldXpath))) {
			scrollUp(driver);
			// scrollTo(driver, driver.findElement(fieldXpath));
			clickOnElement(driver.findElement(fieldXpath));
			waitForPageLoaded();
		} else
			Assert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
					fieldName + " - not present on the page. Please verify.");
	}

	/**
	 * Function for click on element with JS after check presence of element
	 * 
	 * @param fieldName
	 * @param fieldXpath
	 */
	public void verifyElementPresenceClickWithJs(String fieldName, By fieldXpath) {
		waitForPageLoaded();
		if (isElementPresent(driver.findElements(fieldXpath))) {
			clickOnElementWithJS(driver.findElement(fieldXpath));
			waitForPageLoaded();
		} else
			Assert.assertTrue(isElementPresent(driver.findElements(fieldXpath)),
					fieldName + " - not present on the page. Please verify.");

	}

	/**
	 * Function for verify length of the page
	 * 
	 * @param pageLengthdropDownSelectedValue
	 * @param pageLengthButton
	 * @param pageLengthDropdownAllElements
	 * @param pagination
	 * @param softAssert
	 * @throws InterruptedException
	 */
	public void verifyPageLength(String pageLengthdropDownSelectedValue, By pageLengthButton,
			By pageLengthDropdownAllElements, By pagination, SoftAssert softAssert) throws InterruptedException {

		int noOfEntries = getNumberOfEntries();
		System.out.println("Total number of entries in list are :- " + noOfEntries);

		selectCustomizeDropDownValue(pageLengthdropDownSelectedValue, pageLengthButton, pageLengthDropdownAllElements);

		int noOfRecordsSelected = getNumberOfRecordsSelected(pageLengthdropDownSelectedValue);
		System.out.println("Total number of entries are slected to display on page:- " + noOfRecordsSelected);

		if (noOfRecordsSelected < noOfEntries) {
			boolean displayPagination = isElementDisplayed(driver.findElement(pagination));
			System.out.println("pagination :" + driver.findElement(pagination).getText());
			softAssert.assertTrue(displayPagination,
					"Selected Records to display on page is greater than the total number of Records.So, Pagination is not dispalyed");

		}

	}

	/**
	 * Function for get number of records selected
	 * 
	 * @param selectPageLengthFromPageLengthDropdown
	 * @return Integer total selected value
	 */
	public int getNumberOfRecordsSelected(String selectPageLengthFromPageLengthDropdown) {
		String dropDownSelectedValue = selectPageLengthFromPageLengthDropdown.trim();
		String[] SelectedValue = dropDownSelectedValue.split(" ");
		String totalSelectedValue = SelectedValue[SelectedValue.length - 2];
		System.out.println("totalSelectedValue : " + totalSelectedValue);
		return Integer.parseInt(totalSelectedValue);
	}

	/*
	 * Below method is used to get the total number of entries in the table
	 */
	public int getNumberOfEntries() {
		String entriesTxt = driver.findElement(By.xpath("//div[@class='summary']")).getText().trim();
		String[] aEntriesText = entriesTxt.split(" ");
		String totalEntriesText = aEntriesText[aEntriesText.length - 2];
		System.out.println("totalEntriesText : " + totalEntriesText);
		return Integer.parseInt(totalEntriesText);
	}

	/*
	 * This method will return true if the elements are in AscendingOrder else
	 * return false
	 */
	public static boolean checkAscendingOrder(LinkedList<String> product_names) {
		String previous = ""; // empty string

		for (String current : product_names) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}
		return true;
	}

	/*
	 * Below is the method to read the CSV file and get the number of entries
	 * present in the exported csv file. It takes the file name as the parameter
	 */
	public int getRecordsCountInCSV(String downloadPath, String latestdownloadedFileName) {
		int lineNumberCount = 0;
		try {
			if (!latestdownloadedFileName.isEmpty() || latestdownloadedFileName != null) {
				String filePath = downloadPath + System.getProperty("file.separator") + latestdownloadedFileName;
				System.out.println("filePath :" + filePath);
				File file = new File(filePath);
				if (file.exists()) {
					System.out.println("File found :" + latestdownloadedFileName);
					FileReader fr = new FileReader(file);
					LineNumberReader linenumberreader = new LineNumberReader(fr);
					while (linenumberreader.readLine() != null)
						lineNumberCount++;
					// To remove the header
					lineNumberCount = lineNumberCount - 1;
					System.out.println("Total number of lines found in downloades csv : " + (lineNumberCount));
					linenumberreader.close();
				} else
					System.out.println("File does not exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lineNumberCount;

	}

	/*
	 * Below method is used to get the latest file from the directory. It takes the
	 * folder path as the parameter and returns the file which is recently added to
	 * the folder.
	 */
	private File getLatestFilefromDir(String dirPath) {
		//dirPath = "/home/sonali@dayalgroup.hq/Downloads";
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0)
			return null;

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++)
			if (lastModifiedFile.lastModified() < files[i].lastModified())
				lastModifiedFile = files[i];
		return lastModifiedFile;
	}

	/*
	 * Below method takes the download directory and the file name, which will check
	 * for the file name mention in the directory and will return 'True' if the
	 * document is available in the folder else 'false'. When we are sure of the
	 * file name, we can make use of this method to verify.
	 */
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		// System.out.println("filename"+fileName);
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++)
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;

		return flag;
	}

	/*
	 * The below method takes two parameters, first takes the folder path and the
	 * file extension .it will return true if the file with the specific extension
	 * is available in the specified folder.
	 */

	/* Check the file from a specific directory with extension */
	private boolean isFileDownloaded_Ext(String dirPath, String ext) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0)
			flag = false;

		for (int i = 1; i < files.length; i++)
			if (files[i].getName().contains(ext))
				flag = true;
		return flag;
	}

	/**
	 * This method is used to verify Export functionality.
	 * @param exportdropDownSelectedValue
	 * @param exportButton
	 * @param exportDropdownAllElements
	 * @throws InterruptedException
	 */
	public void verifyExportFunctionality(String exportdropDownSelectedValue, By exportButton, By exportDropdownAllElements,
			SoftAssert softAssert, String downloadPath) throws InterruptedException {
		selectCustomizeDropDownValue(exportdropDownSelectedValue, exportButton, exportDropdownAllElements);
		Thread.sleep(4000);
		File file = getLatestFilefromDir(downloadPath);
		Thread.sleep(4000);
		System.out.println("file : " + file);
		String latestdownloadedFileName = file.getName();
		System.out.println("File Downloaded is :- " + latestdownloadedFileName);

		if (exportdropDownSelectedValue.equalsIgnoreCase("CSV")) {
			/* VerifyDownloadWithFileName */
			softAssert.assertTrue(isFileDownloaded(downloadPath, latestdownloadedFileName),
					"Failed to download Expected CSV document");
			/* VerifyDownloadWithFileExtension */
			softAssert.assertTrue(isFileDownloaded_Ext(downloadPath, ".csv"),
					"Failed to download document which has extension .csv");

		}

		if (exportdropDownSelectedValue.equalsIgnoreCase("Excel")) {
			softAssert.assertTrue(isFileDownloaded(downloadPath, latestdownloadedFileName),
					"Failed to download Expected Excel document");
			softAssert.assertTrue(isFileDownloaded_Ext(downloadPath, ".xls"),
					"Failed to download document which has extension .xls");

		}
		if (exportdropDownSelectedValue.equalsIgnoreCase("PDF")) {
			softAssert.assertTrue(isFileDownloaded(downloadPath, latestdownloadedFileName),
					"Failed to download Expected PDF document");
			softAssert.assertTrue(isFileDownloaded_Ext(downloadPath, ".pdf"),
					"Failed to download document which has extension .pdf");

		}
		//delete downloaded file
	}


	public void verifyExportFile(SoftAssert softAssert) throws InterruptedException {

		String exportdropDownSelectedValue ="Excel";
		By exportButtonDropdown = By.xpath("//div[@class='btn-group']/button[@title='Export']");
		By exportDropdownAllElements =By.xpath("//button[@title='Export']/following-sibling::ul[@class='dropdown-menu dropdown-menu-right']/li/a");
		String downloadPath = System.getProperty("user.dir")+"/resources/DownloadFiles";
		By exportButton = By.xpath("//div[@class='pull-right top_btn']//a");

		boolean exportButtonDropdownPresent =isElementPresent(driver.findElements(exportButtonDropdown));
		System.out.println(" Dropdown exportButtonPresent::"+exportButtonDropdownPresent);

		if(exportButtonDropdownPresent) {
			System.out.println("Dropdown exportButtonPresent");
			//selectCustomizeDropDownValue(exportdropDownSelectedValue, exportButton, exportDropdownAllElements);
			implicitWaitToAnElement(30);
			/*****************/
			driver.findElement(exportButton).click();
			implicitWaitToAnElement(30);
			System.out.println("1111111111");
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//je.executeScript("arguments[0].scrollIntoView(true)");
			clickOnElementWithJS(driver.findElement(By.xpath("//button[@title='Export']/following-sibling::ul[@class='dropdown-menu dropdown-menu-right']/li[3]/a")));

			//driver.findElement(By.xpath("//button[@title='Export']/following-sibling::ul[@class='dropdown-menu dropdown-menu-right']/li[3]/a")).click();			
			/**************/
		}

		else if(isElementPresent(driver.findElements(exportButton))) {	
			System.out.println("exportButtonPresent");
			clickOnElement(driver.findElement(exportButton));
		}

//		File file = getLatestFilefromDir(downloadPath);
//		implicitWaitToAnElement(20);	
//		System.out.println("file : " + file);
//		String latestdownloadedFileName = file.getName();
//		System.out.println("File Downloaded is :- " + latestdownloadedFileName);
//
//		softAssert.assertTrue(isFileDownloaded(downloadPath, latestdownloadedFileName),
//				"Failed to download Expected Excel document");
//		softAssert.assertTrue(isFileDownloaded_Ext(downloadPath, ".xls"),
//				"Failed to download document which has extension .xls");

		//		try  
		//		{    
		//			//file to be delete  .txt=latestdownloadedFileName   
		//			//File f= new File("E:\\demo.txt"); 
		//			File f= new File(downloadPath+latestdownloadedFileName); 
		//			System.out.println(f);
		//			//returns Boolean value  
		//			if(f.delete())                    
		//			{  
		//				//getting and printing the file name  
		//				System.out.println(f.getName() + " deleted");   
		//			}  
		//			else  
		//			{  
		//				System.out.println("failed");  
		//			}  
		//		}  
		//		catch(Exception e)  
		//		{  
		//			e.printStackTrace();  
		//		}  
	}





	/**
	 * This method is used to verify the order of list(ascending/descending)
	 * 
	 * @param changeOrderButtonOfList
	 * @param products_List
	 * @throws InterruptedException
	 */
	public void verifySortOrder(By changeOrderButtonOfList, By products_List, SoftAssert softAssert)
			throws InterruptedException {

		String defaultOrderOfList = driver.findElement(changeOrderButtonOfList).getAttribute("class");
		System.out.println("Default Order Of List : " + defaultOrderOfList);
		click(driver.findElement(changeOrderButtonOfList));
		Thread.sleep(2000);

		// create an LinkedList, because it preserves insertion order
		List<WebElement> products_Webelement = new LinkedList<WebElement>();

		// store the products (web elements) into the linkedlist
		products_Webelement = driver.findElements(products_List);

		// create another empty linked list of type string.
		LinkedList<String> product_names = new LinkedList<String>();

		// loop through all the elements of the product_webelement list and store it
		// into the product_names list
		for (int i = 0; i < products_Webelement.size(); i++) {

			// product name of column 2
			String sTemp = products_Webelement.get(i).getText();
			product_names.add(sTemp.toLowerCase().trim());
		}
		System.out.println("list(product_names) of all products -" + product_names);

		boolean ascendingOrder = checkAscendingOrder(product_names);
		if (defaultOrderOfList.equals("asc"))
			softAssert.assertFalse(ascendingOrder,
					"Products names should be Sorted in Descending Order but found Ascending Order");

		if (defaultOrderOfList.equals("desc"))
			softAssert.assertTrue(ascendingOrder,
					"Products names should be Sorted in Ascending Order but found in Descending Order");
	}

	/**
	 * Convert long type deltaTime to format hh:mm:ss:mi.
	 * 
	 * @param deltaTime
	 * @return delta time
	 * @author priyankag@dayalgroup.hq
	 * 
	 */
	public String convertDeltaTimeToString(long deltaTime) {
		StringBuffer retBuf = new StringBuffer();

		long milli = deltaTime;

		long seconds = deltaTime / 1000;

		long minutes = seconds / 60;

		long hours = minutes / 60;
		retBuf.append(hours + ":" + minutes + ":" + seconds);

		// Date myDate = new Date(deltaTime);
		// SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		DateFormat simple = new SimpleDateFormat("HH:mm:ss:SSS");

		Date result = new Date(deltaTime);
		// String myTime = formatter.format(myDate);

		//		return retBuf.toString();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String stringDate = simpleDateFormat.format(new Date(deltaTime));
		// System.out.println("+++++++++:" + stringDate); // Thu, 29 Oct 2015 23:00:00
		// GMT

		return (stringDate);
	}

	/**
	 * For select spinner in mobile app.
	 * 
	 * @param spinnerOption   Xpath of spinner option list.
	 * @param listAllElements Xpath of all list data.
	 * @param spinnerValue    Value name for select from spinner.
	 * @throws InterruptedException
	 */
	public Boolean verifySelectSpinner(By spinnerOption, By listAllElements, String fieldValue, String fieldName,
			SoftAssert softAssert, Boolean testedData) throws InterruptedException {

		Boolean foundValue = false;
		JavascriptExecutor je = (JavascriptExecutor) driver;

		try {
			if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {
				implicitWaitToAnElement(50);
				clickOnElement(driver.findElement(spinnerOption));
				Thread.sleep(2000);

				List<WebElement> fieldDropDownOptions = driver.findElements(listAllElements);
				if (fieldDropDownOptions.size() > 0) {
					System.out.println(fieldDropDownOptions.size());
					for (WebElement webElement : fieldDropDownOptions)
						System.out.println(webElement.getText());

					//					for (int i = 0; i <= fieldDropDownOptions.size(); i++) {
					//						System.out.println("Field Dropdown Option");
					//						System.out.println(fieldDropDownOptions.get(i).getText());
					//					}
					//					System.out.println("++++++++++++++++++++++++++++");
					// driver.findElement(By.name(fieldValue)).click();
					//					driver.findElement(By.xpath("//android.widget.TextView[@text = '" + fieldValue + "']")).click();
					//					System.out.println("=====================================");

					/*
					 * for (WebElement item : fieldDropDownOptions) if
					 * (item.getText().equalsIgnoreCase(fieldValue)) { foundValue = true; try {
					 * implicitWaitToAnElement(60); item.click(); implicitWaitToAnElement(30);
					 * implicitWaitToAnElement(30); // System.out.println("The " + fieldName + " " +
					 * fieldValue + " is selected Now."); Reporter.log("The " + fieldName + " " +
					 * fieldValue + " is selected Now."); } catch (ElementNotInteractableException
					 * e) { // TODO: handle exception softAssert.assertTrue(false,
					 * "Not Interactable options in " + fieldName + " dropdown ."); }
					 * 
					 * break; }
					 */
					if (foundValue) {
						if (testedData != null && testedData == false)
							testedData = false;
						else
							testedData = true;
					} else {
						testedData = false;
						softAssert.assertTrue(foundValue, fieldValue + " In The " + fieldName + " Not Present.");
						Thread.sleep(3000);
						clickOnElement(driver.findElement(listAllElements));
					}

				} else {
					Reporter.log("No Data Found in the" + fieldName + " Dropdown Field.");
					softAssert.assertTrue(fieldDropDownOptions.size() > 0,
							"No Record match in the " + fieldName + " Dropdown Field.");
				}
			} else if (fieldValue.equalsIgnoreCase("")) {
				testedData = true;
				// softAssert.assertTrue(false,"The Value Of "+fieldName+ " Not Present in the
				// data sheet.Please Verify Data.");
				driver.findElement(spinnerOption).click();
				Thread.sleep(2000);
				List<WebElement> listOfElements = driver.findElements(listAllElements);
				if (isElementPresent(driver.findElements(listAllElements)))
					listOfElements.get(1).click();
			}
			/*
			 * else { Reporter.log("The Value Of"+fieldName+
			 * " Not Present.Please Verify Data.");
			 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
			 * " Should Not Be Blank."); }
			 */
		} catch (

				NoSuchElementException e1) {

			String dropdownDisable = spinnerOption.toString() + "[@tabindex=-1]";
			int index = dropdownDisable.indexOf(":") + 1;
			dropdownDisable = dropdownDisable.substring(index, dropdownDisable.length());
			if (isElementPresent(driver.findElements(By.xpath(dropdownDisable)))) {
				System.out.println("The - " + fieldName + " is disabled..please verify on the page.");
				Reporter.log("The - " + fieldName + " is disabled..please verify on the page.");
			} else {
				Reporter.log("The - " + fieldName + " Not showing..please verify on the page.");
				softAssert.assertTrue(isElementPresent(driver.findElements(listAllElements)),
						"The - " + fieldName + " Not showing..please verify on the page.");
			}
		} catch (Exception e) {
			Reporter.log("In The " + fieldName + " " + fieldValue + " Exception occured.");
			softAssert.assertTrue(foundValue, "In The " + fieldName + " " + fieldValue + " Exception occured.");

		}
		return testedData;
	}

	public Boolean verifyItemList(By selectFieldValueFromList, By firstListItem, By searchField, By listAllElements,
			String fieldValue, String appName, String fieldName, SoftAssert softAssert, Boolean testedData)
					throws InterruptedException {

		Boolean foundValue = false;

		if (fieldValue.equals("")) {
			if (isElementPresent(driver.findElements(listAllElements))) {
				foundValue = true;
				clickOnElement(driver.findElement(firstListItem));
			} else
				softAssert.assertTrue(isElementPresent(driver.findElements(listAllElements)),
						"In" + appName + " - " + fieldName + " Not Present.");
		} else if (fieldValue != null && !fieldValue.equalsIgnoreCase("")) {
			clearAndSendKeysToElement((driver.findElement(searchField)), fieldValue);
			Thread.sleep(2000);
			implicitWaitToAnElement(10);
			if (isElementPresent(driver.findElements(listAllElements))) {
				foundValue = true;
				clickOnElement(driver.findElement(selectFieldValueFromList));
			}
		}
		if (foundValue) {
			if (testedData != null && testedData == false)
				testedData = false;
			else
				testedData = true;
		} else {
			testedData = false;
			softAssert.assertTrue(foundValue, fieldValue + " In The " + fieldName + " Not Present.");
			Thread.sleep(3000);
		}
		return testedData;
	}

	public static String readUsingApacheCommonsIO(String fileName) throws IOException {
		return FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
	}

	/**
	 * Function for append string to file
	 * 
	 * @param fileName
	 * @param str
	 */
	public static void appendStrToFile(String fileName, String str) {
		try {

			// Open given file in append mode.
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(str);
			out.close();
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		}
	}

	/**
	 * This method for write content in html file.
	 * 
	 * @param fileContent File content add in html file
	 * @param fileName    File name
	 * @throws IOException
	 */
	public static void WriteToFile(String fileContent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		// String tempFile = projectPath + File.separator+fileName;
		String tempFile = projectPath + File.separator + fileName;

		// String tempFile = fileName;

		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.setLength(0);
			/*
			 * try { File newFileName = new File(projectPath + File.separator+
			 * "backup_"+fileName); file.renameTo(newFileName); file.createNewFile(); }
			 * catch (IOException e) { e.printStackTrace(); }
			 */
		}
		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		// writer.write("");
		// writer.flush();
		writer.write(fileContent);
		writer.close();

	}

	/**
	 * This method used for serach from list.
	 * 
	 * @param data            Data value want to search from list.
	 * @param dropDownAllData Xpath for dropdown.
	 * @throws InterruptedException
	 */
	public void searchList(String data, By dropDownAllData) throws InterruptedException {

		List<WebElement> options = driver.findElements(dropDownAllData);
		if (options.size() > 0)
			for (int i = 0; i < options.size(); i++)
				try {
					if (data.equalsIgnoreCase(options.get(i).getText())) {
						WebElement DropDownText = options.get(i);
						Thread.sleep(3000);
						DropDownText.click();
						log.info("value is selected");
						break;
					}
				} catch (Exception ex) {
					log.info("exception found");
					break;
				}
	}

	/**
	 * This method is used to select Desired option from list.
	 * 
	 * @param xpathList
	 * @param desiredOption
	 * @param softAssert
	 * @throws InterruptedException
	 */
	public void selectDesiredOptionFromList(By xpathList, String desiredOption) throws InterruptedException {
		List<WebElement> List = driver.findElements(xpathList);
		System.out.println("List size : " + List.size());
		if (List.size() > 0) {
			for (int i = 0; i < List.size(); i++) {
				if (List.get(i).getText().equals(desiredOption)) {
					System.out.println("gmailAccountList.get(i).getText():" + List.get(i).getText() + desiredOption);
					;
					Thread.sleep(3000);
					List.get(i).click();
					Thread.sleep(4000);
					break;
				} else {
					// softAssert.assertEquals(List.get(i).getText(),desiredOption);
					System.out.println("Desired option not present.");
				}

			}

		}
	}

	public void printValue(String message) {

		System.out.println(message);
	}

	public void printLogValue(String message) {

		log.info(message);
	}

	/**
	 * Function for reporter log
	 * 
	 * @param message
	 */
	public void reporterLog(String message) {
		Reporter.log(message);

	}

	/**
	 * Function for tooltip text on mouseover
	 * 
	 * @param actionsEle
	 * @param toolTipEle
	 * @param softAssert
	 * @param actualTooltipTxt
	 */
	public void toolTipMethod(WebElement actionsEle, WebElement toolTipEle, SoftAssert softAssert,
			String actualTooltipTxt) {

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
		// Using actions class to do mouse hover
		Actions action = new Actions(driver);
		action.moveToElement(actionsEle).build().perform();
		// Get the Tool Tip Text
		String toolTipTxt = toolTipEle.getText();
		// Using assert to verify the expected and actual value
		softAssert.assertEquals("We ask for your attachment name only for statistical purposes.", toolTipTxt);

		String tooltipTxt = actionsEle.getAttribute("title");
		softAssert.assertEquals(actualTooltipTxt, tooltipTxt);

	}

	/**
	 * 
	 * @throws AWTException
	 */
	public void FileDownload_Robo() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * Function for Upload file using Robot class
	 * 
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void FileUpload_Robo(String browseElementLinkText, String filePath)
			throws InterruptedException, AWTException {

		WebElement browse = driver.findElement(By.linkText(browseElementLinkText));
		// using linkText, to click on browse element
		browse.click(); // Click on browse option on the webpage
		Thread.sleep(2000); // suspending execution for specified time period

		// creating object of Robot class
		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}

	/**
	 * Function for add attachment
	 * 
	 * @param attachmentNameXpath
	 * @param attachmentName
	 * @param attachmentDescriptionXpath
	 * @param attachmentDescription
	 * @param selectFileButtonXpath
	 * @param attahmentPath
	 * @param saveButtonXpath
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void addPhotoAttachment(String appname ,String POMname ,By attachmentNameXpath, String attachmentName, String attachmentSize, String attachmentType ,By attachmentDescriptionXpath,
			String attachmentDescription, By selectFileButtonXpath, String attahmentPath, By saveButtonXpath)
					throws InterruptedException, IOException {
		System.out.println("addPhotoAttachment........");
		//		if(appname.contains("HRMS")) {
		//			if (!attachmentName.equals(null)) {
		//				sendAnyKeyToElement(driver.findElement(attachmentNameXpath), attachmentName);
		//				if (!attachmentDescription.equals(null)) {
		//					sendAnyKeyToElement(driver.findElement(attachmentDescriptionXpath), attachmentDescription);
		//				}
		//				System.out.println(attahmentPath);
		//				driver.findElement(selectFileButtonXpath).sendKeys(attahmentPath);
		//				clickOnElement(driver.findElement(saveButtonXpath));
		//			} 
		//		}

		if(appname.contains("HRMS") &&  POMname.contains("Create Employee")){
			if ((!selectFileButtonXpath.equals(null)) && (!attahmentPath.equals(null))) {			
				System.out.println(attahmentPath);
				driver.findElement(selectFileButtonXpath).sendKeys(attahmentPath);

			} 
		}

		if(appname.contains("dClub")) {
			if ((!selectFileButtonXpath.equals(null)) && (!attahmentPath.equals(null))) {			
				System.out.println(attahmentPath);
				driver.findElement(selectFileButtonXpath).sendKeys(attahmentPath);

			} 
		}


		File f = new File(attahmentPath);
		long fileSize = f.length();
		System.out.format("The size of the file: %d bytes : ", fileSize);
		String latestdownloadedAttachmentName = f.getName();
		System.out.println("Uploaded File name is :" + latestdownloadedAttachmentName);
		String typeOfAttachment = "";
		//
		String[] split = latestdownloadedAttachmentName.split("\\.");
		String ext = split[split.length - 1];
		System.out.println("ext : " + ext);
		//
		int i = latestdownloadedAttachmentName.lastIndexOf('.');
		if (i >= 0) {
			typeOfAttachment = latestdownloadedAttachmentName.substring(i + 1);
		}
		System.out.println("typeOfAttachment :" + typeOfAttachment);
		System.out.println("MegaBytes ::" + getFileSizeMegaBytes(f) + "KiloBytes ::" + getFileSizeKiloBytes(f)
		+ "Bytes ::" + getFileSizeBytes(f));

	}
	public void verifyAddPhotoAttachment( String attachmentName, String attachmentSize, String attachmentType, String attahmentPath ) {

		//	if(attachmentName.equals)

	}



	private static String getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024) + " mb";
	}

	private static String getFileSizeKiloBytes(File file) {
		return (double) file.length() / 1024 + "  kb";
	}

	private static String getFileSizeBytes(File file) {
		return file.length() + " bytes";
	}
	public void addDocumentWithAttachmentTypeDropdown(By plusIconXath, By selectAttachmentTypeDropdownxpath , String AttachmentTypeOption, String attahmentPath,String attachmentName, String attachmentSize, String attachmentType, By selectFileButtonXpath, By saveButtonXpath, By closePopuIconXpath,By SizeExceedErrorMessageXpath ) throws InterruptedException, IOException {	
		implicitWaitToAnElement(50);
		clickOnElementWithJS(driver.findElement(plusIconXath));
		if(!AttachmentTypeOption.equals(null)) {
			selectDropDown(driver.findElement(selectAttachmentTypeDropdownxpath), AttachmentTypeOption);
			System.out.println(attahmentPath);
			implicitWaitToAnElement(2000);
			driver.findElement(selectFileButtonXpath).sendKeys(attahmentPath);
			implicitWaitToAnElement(2000);
			clickOnElement(driver.findElement(saveButtonXpath));

			if(isElementPresent(driver.findElements(SizeExceedErrorMessageXpath))) {
				System.out.println("Size of attachmnet is exceed to 2MB");
				clickOnElement(driver.findElement(closePopuIconXpath));
			}
		}
		else{
			captureScreenShot(driver);
			System.out.println("attachment type sholud be selected");
		}

		File f = new File(attahmentPath);
		long fileSize = f.length();
		System.out.format("The size of the file: %d bytes : ", fileSize);
		String latestdownloadedFileName = f.getName();
		System.out.println("Uploaded File name is :" + latestdownloadedFileName);
		String extension = "";
		//
		String[] split = latestdownloadedFileName.split("\\.");
		String ext = split[split.length - 1];
		System.out.println("ext : "+ext);
		//
		int i = latestdownloadedFileName.lastIndexOf('.');
		if (i >= 0) {
			extension = latestdownloadedFileName.substring(i+1);
		}
		System.out.println("extension :"+extension);
		System.out.println("MegaBytes ::" +getFileSizeMegaBytes(f )+ "KiloBytes ::"+getFileSizeKiloBytes(f )+ "Bytes ::" +getFileSizeBytes(f ));
	}

	public void VerifyaddDocument(By viewDocumentButtonXpath ,By attachmnetCountXpath,By attachmetNameAfterAddAttachmnetXpath,String AttachmentType,String AttachmentName, String attahmentSize ,String AttachmnetTypeOptionsFromDropdown,SoftAssert softAssert) throws InterruptedException, IOException {
		String attachmentCountAfterAddingAttachment = driver.findElement(attachmnetCountXpath).getText();				
		System.out.println("attachmentCountAfterAddingAttachment ==="+attachmentCountAfterAddingAttachment);
		if(!(attachmentCountAfterAddingAttachment).equals("0")) {
			clickOnElement(driver.findElement(viewDocumentButtonXpath));
			String attachmentNameInPopup =  driver.findElement(attachmetNameAfterAddAttachmnetXpath).getText();
			System.out.println("attachmentNameInPopup===="+attachmentNameInPopup);

			String[] split = attachmentNameInPopup.split("\\.");
			String attachmnetTypeInPopup = split[split.length - 1];

			String str = attachmnetTypeInPopup;
			String separator ="(";
			System.out.println("str ::"+str);
			int attachmnetTypeInPopUp = str.lastIndexOf(separator);
			System.out.println("attachmnetTypeInPopUp::"+attachmnetTypeInPopUp);
			String TypeInpopUp =str.substring(0,attachmnetTypeInPopUp);
			System.out.println("TypeInpopUp ::"+TypeInpopUp);

			String attachmnetNameWithAttachmnetType =AttachmentName+"("+AttachmnetTypeOptionsFromDropdown+")";	    
			if(attachmentNameInPopup.equals(attachmnetNameWithAttachmnetType) && (TypeInpopUp.equals(AttachmentType))) {

			}
			else {
				captureScreenShot(driver);
				System.out.println("name and type are not same");
				softAssert.assertEquals(AttachmentName, attachmentNameInPopup);
				softAssert.assertEquals(AttachmentType, attachmnetTypeInPopup);
			}
		}
		else {
			System.out.println("There is no attachment");
			captureScreenShot(driver);
			softAssert.assertEquals((driver.findElement(attachmnetCountXpath).getText()), !((driver.findElement(attachmnetCountXpath).getText()).equals("0")));
		}

	}
}
