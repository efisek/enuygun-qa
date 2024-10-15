package config;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private static Properties properties;

    //to read data by FileInputStream
    static {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
