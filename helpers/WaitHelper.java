package helpers;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for WaitHelper.
 */
public class WaitHelper {
	
	private WebDriver driver;
	
	Logger log = LoggerHelper.getLogger(WaitHelper.class);
	/**
	 * Constructor
	 * @param driver the instance of the WebDriver
	 */
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Method to set implicit wait.
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	/**
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryMiliSec, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	
	/**
	 * Method to wait for element visible.
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryMiliSec
	 */
	public void waitForElementVisible(WebElement element, int timeOutInSeconds, int pollingEveryMiliSec) {
		log.info("Waiting for: " + element.toString() + "for: " + timeOutInSeconds + " seconds." );
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now.");
	}
	
	
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + "for: " + timeOutInSeconds + " seconds." );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now.");
	}
	
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + "for: " + timeOutInSeconds + " seconds." );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is not invisible now.");
		return status;
	}
	
	
	/**
	 * Method to wait For Element Not Present.
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementNotPresent1(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + "for: " + timeOutInSeconds + " seconds." );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Element is not invisible now.");
	}
	
	
	/**
	 * Method for wait till the Element will visible.
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementLocated(By element, int timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + "for: " + timeOutInSeconds + " seconds." );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		log.info("Element is clickable now.");
	}
}
