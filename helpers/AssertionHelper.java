package helpers;

import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * 
 * Assertion Helper class.
 *
 */
public class AssertionHelper {
	
	private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);
	
	public static void verifyText(String actualMsg, String expextedMsg) {
		log.info("Verify test: " + actualMsg + "with " + expextedMsg);
		Assert.assertEquals(actualMsg, expextedMsg);
	}
	
	public static void makeTrue() {
		log.info("making script PASS.");
		Assert.assertTrue(true);
	}
	
	public static void makeTrue(String message) {
		log.info("making script PASS." + message);
		Assert.assertTrue(true, message);
	}
	
	public static void makeFalse() {
		log.info("making script FAIL.");
		Assert.assertTrue(false);
	}
	
	public static void makeFalse(String message) {
		Assert.assertTrue(false, message);
	}
	
	public static void verifyTrue(boolean status) {
		Assert.assertTrue(status);
	}
	
	public static void verifyFalse(boolean status) {
		Assert.assertFalse(status);
	}
	
	public static void verifyNull(String s1) {
		Assert.assertNull(s1);
	}
	
	public static void verifyNotNull(String s1) {
		Assert.assertNotNull(s1);
	}
}
