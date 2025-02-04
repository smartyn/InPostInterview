package helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Configuration {

    private static final String configFile = "conf.properties";

    private static String getConfigurationPropertyValueByKey(String propertiesFileName, String keyName) {
        String keyValue = "";
        try {
            keyValue = System.getProperty(keyName);
            if (keyValue != null && !keyValue.startsWith("${")) {
                log.trace(String.format("System property value of: '%s' is '%s'", keyName, keyValue));
            } else {
                keyValue = getPropertyValue(propertiesFileName, keyName);
                log.trace(String.format("Property value of: '%s' from %s file is %s", keyName, propertiesFileName, keyValue));
                log.trace("Property value of: '" + keyName + "' taken from '" + propertiesFileName + "' is: '" + keyValue + "'");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return keyValue;
    }

    private static String getPropertyValue(String propertiesFileName, String keyName) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (Exception e) {
            log.error(e.getMessage());
        } return properties.getProperty(keyName);
    }

    private static String getRegionConfigurationFileName() {
        return String.format("region%s.properties", getSelectedRegion());
    }

    public static String getConfigurationValueFromRegionConfigurationFile(String keyName) {
        return getConfigurationPropertyValueByKey(getRegionConfigurationFileName(), keyName);
    }
    public static String getSelectedRegion() {
        return getConfigurationPropertyValueByKey(configFile, "region");
    }

    public static String getBrowser() {
        return getConfigurationPropertyValueByKey(configFile, "browser");
    }

    public static String getChrome() {
        return getConfigurationPropertyValueByKey(configFile, "chromeDriverPath");
    }

    public static String getFirefox() {
        return getConfigurationPropertyValueByKey(configFile, "geckoDriverPath");
    }

    public static String getTestType() {
        return getConfigurationPropertyValueByKey(configFile, "testType");
    }
}
