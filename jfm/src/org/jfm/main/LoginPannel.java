/**
 * 
 */
package org.jfm.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.asu.ser335.jfm.LogBook;
import edu.asu.ser335.jfm.RolesSingleton;
import edu.asu.ser335.jfm.SaltsSingleton;
import edu.asu.ser335.jfm.UsersSingleton;
import io.whitfin.siphash.SipHasher;

/**
 * @author Nikhil Hiremath
 *
 */

public class LoginPannel extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JLabel labelRole = new JLabel("Enter Role: ");
	private JLabel message;
	private JTextField textUsername = new JTextField(20);
	private JPasswordField fieldPassword = new JPasswordField(20);
	// private JTextField textRole = new JTextField(20);
	private JButton buttonLogin = new JButton("Login");
	private JPanel newPanel;

	private JComboBox<String> roleList;
	public static String role;

	public static MainFrame mainFrame;
	
	public LoginPannel() {
		super("Login Pannel");

		// create a new panel with GridBagLayout manager
		newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		// UserName
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelUsername, constraints);

		constraints.gridx = 1;
		newPanel.add(textUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		// Password
		newPanel.add(labelPassword, constraints);

		constraints.gridx = 1;
		newPanel.add(fieldPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		// Role
		newPanel.add(labelRole, constraints);
		constraints.gridx = 1;

		// newPanel.add(textRole, constraints);
		// drop down

		roleList = new JComboBox<String>(RolesSingleton.getRoleMapping().getDisplayRoles());

		// add to the parent container (e.g. a JFrame):
		newPanel.add(roleList, constraints);

		// System.out.println("Selected role: " + role);

		constraints.gridx = 0;
		constraints.gridy = 3;

		message = new JLabel();
		newPanel.add(message, constraints);
		constraints.gridx = 1;

		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(buttonLogin, constraints);
		// Adding the listeners to components..

		buttonLogin.addActionListener(this);

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel"));

		// add the panel to this frame
		add(newPanel);

		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String userName = textUsername.getText();
		String password = String.valueOf(fieldPassword.getPassword());
		role = (String) roleList.getSelectedItem();
		
		/* monitor using observer pattern */
		LoginMonitor loginMonitor = new LoginMonitor();
		Validation validation = new Validation();
		validation.addObserver(loginMonitor);

		// Validate User and redirect to Main application on successful login
		if(validation.validateUser(userName, password, role)) {
			message.setText(" Hello " + userName + "");

			// on successful login redirect to next screen
			mainFrame = new MainFrame(role);
			// Validate frames that have preset sizes
			mainFrame.validate();
			// Center the window
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = mainFrame.getSize();
			if (frameSize.height > screenSize.height) {
				frameSize.height = screenSize.height;
			}
			if (frameSize.width > screenSize.width) {
				frameSize.width = screenSize.width;
			}
			mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			mainFrame.setVisible(true);

			// Close Login Window (JPanel)
			dispose();
		} else {
			message.setText(" Invalid user.. ");
		}
	}

	class Validation extends Observable {

	    String uName, pwd, role;
	    
	    // Login Validation
	    public boolean validateUser(String uName, String pwd, String role) {
	        
	        // SER335 TODO: Implement your validation code here.
	        if(uName.isEmpty() || pwd.isEmpty() || role.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Missing Input Field!");
	            return false;
	        }
	        
	        /* store them for later access */
	        this.uName = uName;
	        this.pwd = pwd;
	        this.role = role;
	        
	        try {
	            
	            String userSalt = SaltsSingleton.getUserSalts().getUserSalt(uName);
	            long generatedSaltedPassword = SipHasher.hash(userSalt.getBytes(), pwd.getBytes());
	            long storedSaltedPassword = Long.parseLong(UsersSingleton.getUserPasswordMapping().get(uName));
	            
	            if(generatedSaltedPassword != storedSaltedPassword) {         
	                setChanged();
	                notifyObservers();
	                return false;
	            }
	            
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, e.getMessage());
	            return false;
	        }       
	        return true;
	    }
	}
	
	/* Login Activity Observer, logs the failed login attempt */
	class LoginMonitor implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            // TODO Auto-generated method stub
            Validation validation = (Validation)o;
            LogBook.logEvent(String.format("Login Failed [name=%s, password=%s, role=%s]", validation.uName, validation.pwd, validation.role));
        }
	    
	}
	
}
