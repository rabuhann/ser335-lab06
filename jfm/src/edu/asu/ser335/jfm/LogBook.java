package edu.asu.ser335.jfm;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogBook {
    
    final static DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("[MM-dd-yyyy, HH:mm:ss]");
    
    public static void logEvent(String event) {
        
       LocalDateTime timeStamp = LocalDateTime.now();
      
        
        try(PrintWriter writer = new PrintWriter("resources\\jfmlog.txt")) {
            
            writer.append(event+"\t"+timeStamp.format(timeStampFormat));
            writer.flush();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
