package property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyValue {

	private static Properties properties = getProperties();
	private static InputStream inputStream;

	public static String getBrowser() {
		return properties.getProperty("browser");
	}

	public int getGPUNumberValue() {
		return Integer.parseInt(properties.getProperty("gpuNumber"));
	}

	public String getGpuType() {
		return properties.getProperty("gpuType");
	}

	public int getLocalSSDNumber() {
		return Integer.parseInt(properties.getProperty("localSSDNumber"));
	}

	public String getDatacenterLocation() {
		return properties.getProperty("datacenterLocation");
	}

	public int getCommittedUsageNumber() {
		return Integer.parseInt(properties.getProperty("committedUsageNumber"));
	}

	private static Properties getValuesFormPropertyFile() throws IOException {
		Properties properties = new Properties();
		try {
			String propertyFileName = System.getProperty("environment") + ".properties";
			inputStream = PropertyValue.class.getClassLoader().getResourceAsStream(propertyFileName);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			Objects.requireNonNull(inputStream).close();
		}
		return properties;
	}

	private static Properties getProperties() {
		try {
			properties = getValuesFormPropertyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}