package property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyValue {

	private Properties properties = getProperties();
	private InputStream inputStream;

	public String getBrowser() {
		return properties.getProperty("browser");
	}

	public int getGpuNumber() {
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

	private Properties getValuesFormPropertyFile() throws IOException {
		Properties properties = new Properties();
		try {
			String propertyFileName = System.getenv("environment") + ".properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

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

	private Properties getProperties() {
		try {
			properties = getValuesFormPropertyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}