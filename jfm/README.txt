“SER335 LAB5 ACTIVITY2 TASK1”
AddUserPannel.java
Line 122 and below
To throw a more specific type of exception to inform the user what exactly happened. Chose this spot because it's the part that handles the "Add User" operation.

UsersSingleton.java
Line 96-Line 110
The printstacktrace method dumps the stack trace contents onto the terminal window. In order not print out to stdout (the terminal window), I commented it out and threw the exception instead so that the relevant message is shown to the user on the UI.

UsersSingleton.java
Lines 86 and onwards
Seperated the "reading from" and "writing to" code into different try blocks, and gave each the relevant and specific catch blocks. This is because both of them are individually capable of throwing the exception.

“SER335 LAB5 ACTIVITY2 TASK2”
AddUserPannel.java
Line 116 and onwards
Earlier, the empty input fields were being handled technically using a try-catch block (if any input field was empty, a relevant exception was being thrown from the UsersSingleton.createPasswordMapping(userName, password, role) method). Now that we test the input, prior to attempt to add user, using conditional checking, we technically move this code out of the try-catch block, ensurring it doesn't need an exception to be thrown (had there been any try-catch block individually for this code at all).