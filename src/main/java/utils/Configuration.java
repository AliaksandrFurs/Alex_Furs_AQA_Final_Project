package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties credentialeProperties;

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

    public static String getLogin(){
        return Configuration.getCredentialeProperties().getProperty("login");

    }

    public static String getPassword(){
        return Configuration.getCredentialeProperties().getProperty("password");
    }

    public static String getInvalidLogin(){
        return  Configuration.getCredentialeProperties().getProperty("invalidLogin");
    }

    public static String getInvalidPassword(){
        return Configuration.getCredentialeProperties().getProperty("invalidPassword");
    }
}

