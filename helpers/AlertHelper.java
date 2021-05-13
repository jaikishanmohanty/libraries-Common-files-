package helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
/**Alert Helper Class.
 */
public class AlertHelper {
	private WebDriver driver;
	Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
	
	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}

	public String getAlertMessage () {
		return driver.switchTo().alert().getText();
	}
	
	public void ignoreAlertWindow() {
		driver.switchTo().alert().dismiss();
	}
	
	public void acceptAlertWindow() {
		driver.switchTo().alert().accept();
	}
	
	public void asdfAlertWindow() {
		driver.switchTo().alert().notify();
	}
}
