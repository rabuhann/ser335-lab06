package edu.asu.ser335.jfm;

public class NullValidateUserStrategy implements IValidateUserStrategy {

    @Override
    public boolean validateUser(String uName, String pwd) {
        // TODO Auto-generated method stub
        return !uName.isEmpty() && !pwd.isEmpty();
    }

}
