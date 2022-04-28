package edu.asu.ser335.jfm;
import  java.util.*;
import java.io.*;

public class ValidationFactory {
    
    private static final String propertyFilePath = "jfm335.properties";
    
    public Object createValidationStrategy() {
        
        Object strategyObject;
        
        try {
            
            // open property file
            Properties property = new Properties();
            property.load(new FileReader(propertyFilePath));
            
            // read property from file (path to class code)
            String className = property.getProperty("validateuserstrategy.property");
            
            // fetch the class
            Class<?> physicalClass = Class.forName(className);
            
            // make object of given class
            strategyObject = physicalClass.newInstance(); 
            
        }catch(Exception exception1) {
            
            // default object 
            strategyObject = new NullValidateUserStrategy();
        }
        
        return strategyObject;
    }

}
