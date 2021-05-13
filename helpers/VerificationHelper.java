package helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author priyankag@dayalgroup.hq
 *
 */
public class VerificationHelper {

	private WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	/**
	 * Constructor
	 * @param driver
	 */
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * Method to check element is Displayed.
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present.." + element.getText());
			return true;
		}
		catch(Exception e) {
			log.error("element is not present..", e.getCause());
			return false;
		}
	}
	/**
	 * Method to check element is not Displayed.
	 * @param element
	 * @return
	 */
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.." + element.getText());
			return false;
		}
		catch(Exception e) {
			log.error("element is not displayed..", e.getCause());
			return true;
		}
	}
	/**
	 * Method to read value from element.
	 * @param element
	 * @return null
	 */
	public String readValueFromElement(WebElement element) {
		if(null == element) {
			log.info("Webelement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("element text is:" + element.getText());
			return element.getText();
		}
		else {
			return null;
		}
	}
	/**
	 * Method for get text.
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		if(null == element) {
			log.info("Webelement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("element text is:" + element.getText());
			return element.getText();
		}
		else {
			return null;
		}
	}
	/**
	 * Method to check - is text present.
	 * @param element
	 * @param text 
	 * @return
	 */
	public boolean isTextPresent(WebElement element, String text){
		try{
			boolean b = element.getText().contains(text);
			log.info("text is present: " + element.getText());
			return b;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * Method to check -is element select.
	 * @param element
	 * @return
	 */
	public boolean isSelected(WebElement element) {
		try {
			element.isSelected();
			log.info("element is present.." + element.getText());
			return true;
		}
		catch(Exception e) {
			log.error("element is not present..", e.getCause());
			return false;
		}
	}
	/**
	 * Method to check -is element enable.
	 * @param element 
	 * @return
	 */
	public boolean isEnabled(WebElement element) {
		try {
			element.isEnabled();
			log.info("element is enabled.." + element.getText());
			return true;
		}
		catch(Exception e) {
			log.error("element is not enabled..", e.getCause());
			return false;
		}
	}
}
