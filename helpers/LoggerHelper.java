package helpers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	
	private static boolean root = false;
	
	/**@author ankitra
	 * @param cls
	 * @return
	 */
	public static Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/../Library/resources/log4jfile/log4j.properties");
		root = true;
		return Logger.getLogger(cls);
		
	}
	
}
