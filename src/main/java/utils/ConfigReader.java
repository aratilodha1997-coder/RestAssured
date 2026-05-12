package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    public static String getProperty(String key) {

        properties = new Properties();

        try {

            InputStream inputStream =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream("config.properties");

            properties.load(inputStream);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return properties.getProperty(key);
    }
}