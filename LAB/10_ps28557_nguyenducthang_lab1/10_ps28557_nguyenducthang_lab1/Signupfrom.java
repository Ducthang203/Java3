
package Lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
    
public class Signupfrom implements ActionListener {
    private final JFrame frame;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    private final JButton signUpButton;
    private final JButton cancelButton;

    public Signupfrom() {
        // Create the frame and its components
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);

        signUpButton = new JButton("Sign Up");
        cancelButton = new JButton("Cancel");

        // Add the components to the frame
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);
        panel.add(signUpButton);
        panel.add(cancelButton);
        frame.add(panel);

        // Add action listeners to the buttons
        signUpButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Show the frame
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            // Get the values from the text fields
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Check if the password and confirm password match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
                passwordField.setText("");
                confirmPasswordField.setText("");
            } else if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            } else {
                JOptionPane.showMessageDialog(frame, "You have successfully signed up!");
            }
        } else if (e.getSource() == cancelButton) {
            // Close the frame
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        Signupfrom signUp = new Signupfrom();
    }
}

