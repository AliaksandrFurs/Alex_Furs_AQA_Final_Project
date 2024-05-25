package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties configurationProperties;
    private static Properties credentialeProperties;

    public static Properties getConfigurationProperties() {

        if (configurationProperties == null) {
            try {
                configurationProperties = new Properties();
                configurationProperties.load(new FileInputStream("configuration.properties"));
            } catch (IOException e) {

                Logging.logError("Check your configuration file");

            }
        }
        return configurationProperties;
    }

    public static Properties getCredentialeProperties(){
        if(credentialeProperties == null){
            try{
                credentialeProperties = new Properties();
                credentialeProperties.load(new FileInputStream("credentiales.properties"));
                Logging.logInfo("Loading credentials properties successfull");

            }catch(IOException e){

                Logging.logError("Check your credential properties file");

            }
        }
        return credentialeProperties;
    }

    public static String getBrowserName(){

        return Configuration.getConfigurationProperties().getProperty("browser");
    }

    public static String getLogin(){

        return Configuration.getCredentialeProperties().getProperty("login");
    }

    public static String getPassword(){

        return Configuration.getCredentialeProperties().getProperty("password");
    }


}

