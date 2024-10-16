package utils.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final static Logger log = LogManager.getLogger(ConfigManager.class);
    protected Properties properties;
    public ConfigManager(String propertiesFilePath){
        this.properties=new Properties();
        try (FileInputStream inputStream=new FileInputStream(propertiesFilePath)){
            this.properties.load(inputStream);
            log.info("Configuration loaded successfully from : {}",propertiesFilePath);
        } catch (IOException e) {
            log.error("Failed to load File: {} Exception: {}", propertiesFilePath,e.getMessage());
        }
    }
    public String getProperty(String key) {
        String value = this.properties.getProperty(key);
        if (value == null) {
            log.warn("Property key: '{}' not found in the properties file.", key);
        }
        return value;
    }
    // Overloaded method to get property value with a default
    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

}
