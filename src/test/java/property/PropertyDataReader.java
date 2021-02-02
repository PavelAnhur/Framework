package property;

import java.util.ResourceBundle;

public class PropertyDataReader {
	
	private static final ResourceBundle browserResourceBundle
		= ResourceBundle.getBundle(System.getProperty("browser", "chrome"));
	
	private static final ResourceBundle businessValueResourceBundle
		= ResourceBundle.getBundle(System.getProperty("businessValues", "firstValueObject"));
	
	public static String getTestBrowser(String key) {
		return browserResourceBundle.getString(key);
	}
	
	public static String getTestBusinessValues(String key) {
		return businessValueResourceBundle.getString(key);
	}
}