package edu.asu.ser335.jfm;

public class StrictValidateUserStrategy implements IValidateUserStrategy {
    
    @Override
    public boolean validateUser(String uName, String pwd) {
        // TODO Auto-generated method stub
        if(pwd.length() < 6) {
            return false;
        }
        
        for(char c : pwd.toCharArray()) {
        
            if(!Character.isDigit(c) && !Character.isAlphabetic(c) && !isSpecialChar(c)) {
                return false;
            }
        }
        
        return false;
    }
// !_=@%
    private boolean isSpecialChar(char ch) {
        return ch == '!' || ch == '_' || ch =='=' || ch == '@' || ch == '%'; 
    }
}
