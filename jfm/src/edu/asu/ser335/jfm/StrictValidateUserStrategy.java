package edu.asu.ser335.jfm;

public class StrictValidateUserStrategy implements IValidateUserStrategy {
    
    @Override
    public boolean validateUser(String uName, String pwd) {
        // TODO Auto-generated method stub
        if(pwd.length() < 6) {
            return false;
        }
        
        int upperCase = 0, lowerCase = 0, digit = 0, special = 0;
        for(char c : pwd.toCharArray()) {
        
            if(!Character.isDigit(c) && !Character.isAlphabetic(c) && !isSpecialChar(c)) {
                return false;
            }
            
            upperCase += Character.isUpperCase(c) ? 1 : 0;
            lowerCase += Character.isLowerCase(c) ? 1 : 0;
            digit += Character.isDigit(c) ? 1 : 0;
            special += isSpecialChar(c) ? 1 : 0;
        }
        
        return upperCase >= 1 && lowerCase >= 1 && digit >= 1 && special >= 1;
    }
// !_=@%
    private boolean isSpecialChar(char ch) {
        return ch == '!' || ch == '_' || ch =='=' || ch == '@' || ch == '%'; 
    }
}
