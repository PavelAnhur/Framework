package property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyValueManager {
	
	private static final Properties properties = getValuesFormPropertyFile();
	private static InputStream inputStream;
	private static final ResourceBundle resourceBundle
		= ResourceBundle.getBundle(System.getProperty("businessValues", "values"));
	
	public static String getBrowser() {
		return properties.getProperty("browser");
	}
	
	public static String getTestData(String key) {
		return resourceBundle.getString(key);
	}
	
	private static Properties getValuesFormPropertyFile() {
		Properties properties;
		
		properties = new Properties();
		try {
			String propertyFileName = System.getProperty("browser", "chrome") + ".properties";
			inputStream = PropertyValueManager.class.getClassLoader().getResourceAsStream(propertyFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}
			
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				Objects.requireNonNull(inputStream).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
}