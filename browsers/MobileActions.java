package browsers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import helpers.LoggerHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * This class contains common functions for mobile testing.
 * 
 * @author priyankag@dayalgroup.hq
 *
 */
public class MobileActions {
	AndroidDriver<AndroidElement> driver;

	Logger log = LoggerHelper.getLogger(MobileActions.class);

	public MobileActions(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	/**
	 * This method used for scroll screen upward to downward.
	 */
	public void verticalScrollDownToScreen() {

		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int starty = (int) (height * 0.80);
		int endy = (int) (height * 0.20);
		driver.swipe(x, starty, x, endy, 500);
	}

	/**
	 * This method used for scroll screen downward to upward.
	 */
	public void verticalScrollUpToScreen() {
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int starty = (int) (height * 0.20);
		int endy = (int) (height * 0.80);
		driver.swipe(x, starty, x, endy, 500);
	}

	/**
	 * This method used for scroll screen horizontally.
	 */
	public void horizontalScroll() {
		Dimension size;
		size = driver.manage().window().getSize();
		int x_start = (int) (size.width * 0.60);
		int x_end = (int) (size.width * 0.30);
		int y = 130;
		driver.swipe(x_start, y, x_end, y, 4000);
	}

	/**
	 * This method used for search from list.
	 * 
	 * @param listElement
	 * @param data
	 * @param listData
	 * @throws InterruptedException
	 */
	public void searchList(By listElement, String data, By listData) throws InterruptedException {

		List<AndroidElement> we = driver.findElements(listElement);
		log.info(" List size:" + we.size());
		for (WebElement value : we) {
			System.out.println(value.findElement(listData).getText());
			if (value.findElement(listData).getText().equalsIgnoreCase(data)) {
				value.findElement(listData).click();
				Thread.sleep(2000);
				break;
			}
		}
	}

	/**
	 * This method used for serach from list.
	 * 
	 * @param data            Data value want to search from list.
	 * @param dropDownAllData Xpath for dropdown.
	 * @throws InterruptedException
	 */
	public void searchList(String data, By dropDownAllData) throws InterruptedException {

		List<AndroidElement> options = driver.findElements(dropDownAllData);
		if (options.size() > 0) {
			for (int i = 0; i < options.size(); i++) {
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
		}
	}

	/**
	 * This method used for select value from customized dropdown.
	 * 
	 * @param dropDownSelectedValue
	 * @param dropdownOption
	 * @param dropdownAllElements
	 * @throws InterruptedException
	 */
	public void selectCustomizeDropDownValue(String dropDownSelectedValue, By dropdownOption, By dropdownAllElements)
			throws InterruptedException {
		driver.findElement(dropdownOption).click();
		List<AndroidElement> options = driver.findElements(dropdownAllElements);
		if (options.size() > 0) {
			for (int i = 0; i < options.size(); i++) {
				try {
					if (dropDownSelectedValue.equalsIgnoreCase(options.get(i).getText())) {
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
		} else {
			log.info("No record found in dropdown.");
		}
	}

	/**
	 * This method for select date.
	 * 
	 * @param dateField Xpath of date field.
	 * @param date      Date.
	 * @param okButton  Xpath of ok button.
	 * @throws InterruptedException
	 */
	public void selectDate(AndroidElement dateField, String date, AndroidElement okButton) throws InterruptedException {

		clickOnElement(dateField);
		Thread.sleep(3000);
		clickOnElement(driver.findElementByXPath("//android.view.View[contains(@text,'" + date + "')]"));
		Thread.sleep(3000);
		clickOnElement(okButton);
	}

	/**
	 * Method for scroll up to string name.
	 * 
	 * @param ele Xpath for scroll to particular element.
	 * @param str String name for scrolling upto.
	 * @return
	 */
	public MobileElement scrollToExactElement(AndroidElement ele, String str) {
		return ((AndroidElement) ele).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + str + "\"));");
	}

	public MobileElement ScrollToElement(AndroidElement ele, String str) {
		return ((AndroidElement) ele)
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
						+ "new UiSelector().textContains(\"" + str + "\"));");

	}

	public void ScrollStepWise(AndroidElement ele, int step) {
		((AndroidElement) ele)
		.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollForward(" + step + "))");
	}

	/**
	 * For select spinner in mobile app.
	 * 
	 * @param spinnerOption   Xpath of spinner option list.
	 * @param listAllElements Xpath of all list data.
	 * @param spinnerValue    Value name for select from spinner.
	 * @throws InterruptedException
	 */
	public void selectSpinner(WebElement spinnerOption, By listAllElements, String spinnerValue)
			throws InterruptedException {

		spinnerOption.click();
		Thread.sleep(5000);
		List<AndroidElement> listOfElements = driver.findElements(listAllElements);
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
	 * Method for select list view.
	 * 
	 * @param spinnerAllElements Xpath to select all elements from list.
	 * @param spinnerValue       For select value from spinner.
	 * @throws InterruptedException
	 */
	public void selectListView(List<AndroidElement> spinnerAllElements, String spinnerValue)
			throws InterruptedException {

		List<AndroidElement> listOfElements = spinnerAllElements;
		for (int i = 0; i < listOfElements.size(); i++) {
			if (selectElementFromList(i).getText().equals(spinnerValue)) {
				Thread.sleep(3000);
				selectElementFromList(i).click();
				Thread.sleep(4000);
				break;
			}
		}
	}

	public void waitForElement(AndroidElement value) {
		// wait for field
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(value));
	}

	public AndroidElement selectElementFromList(int indexNo) {
		String xpath = "//android.widget.ListView/android.widget.LinearLayout[@index='" + indexNo
				+ "']/android.widget.LinearLayout/android.widget.LinearLayout[@index='1']/android.widget.TextView";
		return driver.findElementByXPath(xpath);
	}

	public void clearAndSendKeysToElement(AndroidElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public void clickOnElement(AndroidElement element) {
		element.click();
	}

	public String getText(AndroidElement element) {
		return element.getText();
	}

	public void sendAnyKeyToElement(AndroidElement element, String text) {
		element.sendKeys(text);
	}

	public void clickAndSendKeysToElement(AndroidElement element, String text) {
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Method for scroll from top to bottom.
	 */
	public void scroll() {
		Dimension dimensions = driver.manage().window().getSize();
		int Startpoint = (int) (dimensions.getHeight() * 0.5);
		int scrollEnd = (int) (dimensions.getHeight() * 0.5);
		driver.swipe(200, Startpoint, 200, scrollEnd, 2000);
	}

	/**
	 * Method for scroll as per text name.
	 * 
	 * @param text Up to scrolling text name.
	 */
	public void scrollTo(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))");
	}

	/**
	 * Method for select radio button.
	 * 
	 * @param element Xpath of radioButton
	 */
	public void Select_The_RadioButton(AndroidElement element) {
		try {
			if (element.isSelected()) {
				System.out.println("RadioButton: " + element + "is already selected");
			} else {
				// Select the radioButton
				element.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to select the radioButton: " + element);
		}

	}

	/**
	 * 
	 * This method return remainning data (Data After Next Index From Last Index Of
	 * wordToFind).
	 * 
	 * @param wordToFind String you want to find from logcat.
	 * @return Message getting from logcat.
	 * @throws IOException
	 */
	public String captureLogcatData(String wordToFind) throws IOException {

		String messageToGet = "";
		List<LogEntry> logs = driver.manage().logs().get("logcat").getAll();
		for (LogEntry logData : logs) {
			if (logData.getMessage().contains(wordToFind)) {
				String text = logData.getMessage();
				Pattern word = Pattern.compile(wordToFind);
				Matcher match = word.matcher(text);
				while (match.find()) {
					log.info("Print Logss Data==" + logData.getMessage());
					log.info("First Index Of Word To Find==" + logData.getMessage().indexOf(wordToFind));
					log.info("Starting and End Index of word to find==" + match.start() + " - " + (match.end() - 1));
					log.info("Char At Next Index Of Char At End Index=="
							+ logData.getMessage().charAt((match.end() - 1) + 1));
					log.info("String From Next Index Of Char At End Index=="
							+ logData.getMessage().substring((match.end() - 1) + 1));
					messageToGet = logData.getMessage().substring((match.end() - 1) + 1);
				}
			}
		}
		return messageToGet;
	}

	/**
	 * For select spinner in mobile app.
	 * 
	 * @param spinnerOption   Xpath of spinner option list.
	 * @param listAllElements Xpath of all list data.
	 * @param spinnerValue    Value name for select from spinner.
	 * @throws InterruptedException
	 */
	/*
	 * public Boolean verifySelectSpinner(By spinnerOption,By listAllElements,String
	 * fieldValue,String fieldName,SoftAssert softAssert,Boolean testedData) throws
	 * InterruptedException {
	 * 
	 * driver.findElement(spinnerOption).click(); Thread.sleep(5000);
	 * List<AndroidElement> listOfElements= driver.findElements(listAllElements);
	 * for (int i = 0; i <listOfElements.size(); i++) { try{ if
	 * (listOfElements.get(i).getText().equals(spinnerValue)) { Thread.sleep(3000);
	 * listOfElements.get(i).click(); Thread.sleep(4000); break; } } catch(Exception
	 * ex) { log.info("exception found"); break; } } }
	 * 
	 * 
	 * 
	 * waitForPageLoaded(); Boolean foundValue = false; JavascriptExecutor je =
	 * (JavascriptExecutor) driver;
	 * 
	 * try { if(fieldValue!=null && !fieldValue.equalsIgnoreCase("")) { //new
	 * WebDriverWait(driver,
	 * 40).until(ExpectedConditions.visibilityOf(driver.findElement(dropdownOptions)
	 * )); implicitWaitToAnElement(50);
	 * clickOnElement(driver.findElement(spinnerOption)); Thread.sleep(2000);
	 * List<WebElement> fieldDropDownOptions = driver.findElements(listAllElements);
	 * if(fieldDropDownOptions.size()>0) { for (WebElement item :
	 * fieldDropDownOptions) { if(item.getText().equalsIgnoreCase(fieldValue)) {
	 * foundValue = true; try {
	 * je.executeScript("arguments[0].scrollIntoView(true);",item);
	 * Thread.sleep(1000); item.click(); implicitWaitToAnElement(30);
	 * waitForPageLoaded(); scrollUp(driver); implicitWaitToAnElement(30);
	 * Reporter.log("The "+fieldName+" "+fieldValue+ " is selected Now."); } catch
	 * (ElementNotInteractableException e) { // TODO: handle exception
	 * softAssert.assertTrue(false,"Not Interactable options in "
	 * +fieldName+" dropdown ."); }
	 * 
	 * break; } } if(foundValue) {
	 * 
	 * if(testedData !=null && testedData==false) { testedData = false; } else {
	 * testedData = true; } } else { testedData = false;
	 * softAssert.assertTrue(foundValue,fieldValue+" In The "
	 * +fieldName+" Not Present."); Thread.sleep(3000);
	 * clickOnElement(driver.findElement(spinnerOption)); }
	 * 
	 * } else { Reporter.log("No Data Found in the"+fieldName+ " Dropdown Field.");
	 * softAssert.assertTrue(fieldDropDownOptions.size()>0,"No Record match in the "
	 * +fieldName+ " Dropdown Field."); } } else if
	 * (fieldValue.equalsIgnoreCase("")) { testedData = true;
	 * softAssert.assertTrue(false,"The Value Of "+fieldName+
	 * " Not Present in the data sheet.Please Verify Data.");
	 * 
	 * } else { Reporter.log("The Value Of"+fieldName+
	 * " Not Present.Please Verify Data.");
	 * softAssert.assertFalse(fieldValue.equalsIgnoreCase(null), "The "+fieldName+
	 * " Should Not Be Blank."); } } catch(NoSuchElementException e1) {
	 * 
	 * String dropdownDisable = spinnerOption.toString()+"[@tabindex=-1]"; int index
	 * = dropdownDisable.indexOf(":")+1; dropdownDisable =
	 * dropdownDisable.substring(index,dropdownDisable.length());
	 * if(isElementPresent(driver.findElements(By.xpath(dropdownDisable)))) {
	 * System.out.println("The - "+fieldName+
	 * " is disabled..please verify on the page."); Reporter.log("The - "+fieldName+
	 * " is disabled..please verify on the page."); } else {
	 * Reporter.log("The - "+fieldName+ " Not showing..please verify on the page.");
	 * softAssert.assertTrue(isElementPresent(driver.findElements(listAllElements))
	 * ,"The - "+fieldName+ " Not showing..please verify on the page."); } } catch
	 * (Exception e) { Reporter.log("In The "+fieldName+" "+fieldValue+
	 * " Exception occured.");
	 * softAssert.assertTrue(foundValue,"In The "+fieldName+" "+fieldValue+
	 * " Exception occured.");
	 * 
	 * } return testedData;
	 * 
	 * }
	 */
	/**
	 * This method is used to select Desired option from list.
	 * @param xpathList
	 * @param desiredOption
	 * @param softAssert
	 * @throws InterruptedException
	 */
	public void selectDesiredOptionFromList(By xpathList , String desiredOption) throws InterruptedException {
		List<AndroidElement> List= driver.findElements(xpathList);
		System.out.println("List size : "+List.size());
		if(List.size()>0) {
			for (int i = 0; i <List.size(); i++) {
				if (List.get(i).getText().equals(desiredOption)) {
					System.out.println("gmailAccountList.get(i).getText():"+List.get(i).getText()+desiredOption);;
					Thread.sleep(3000);
					List.get(i).click();
					Thread.sleep(4000);
					break;
				}
				else {             
					//softAssert.assertEquals(List.get(i).getText(),desiredOption);
					System.out.println("Desired option not present.");
				}

			}

		}
	}
}
