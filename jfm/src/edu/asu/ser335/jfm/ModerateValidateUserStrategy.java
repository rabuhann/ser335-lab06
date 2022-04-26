package edu.asu.ser335.jfm;

public class ModerateValidateUserStrategy implements IValidateUserStrategy {
    
    @Override
    public boolean validateUser(String uName, String pwd) {
        // TODO Auto-generated method stub
        if(pwd.length() < 3) {
            return false;
        }
        
        int letter = 0, digit = 0;
        for(char c : pwd.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                return false;
            }
            
            digit += Character.isDigit(c) ? 1 : 0;
            letter += Character.isAlphabetic(c) ? 1 : 0;
        }
               
        return digit >= 1 && letter >= 1;
    }
}
