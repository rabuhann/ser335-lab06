"SER335 LAB6 TASK1"

LoginPannel.java

lines 131-135
Monitor using observer pattern

Lines 165-203
Class Validation extends Observable

Lines 207-218
Login Activity Observer, logs the failed login attempt

put the validation method inside a whole new observable class called "Validation" and set the observer object named LoginObserver as its observer. So, whenever the login attempt fails, the observer is triggered and the record is logged.



TASK 2 PART 4
ValidationFactory.java
The ValidationFactory reads in the property from the jfm335.properties file and attempts to create the objects of type fetched from the properties file. If the attempt fails, it creates the NullValidateUserStrategy object and ultimately returns the resultant object. Also, it's not mentioned where this ValidationFacotry should be implemented within the code (or what is the caller of this method to which the created object should be returned). So, I just left the class unimplemented in the actual code.