package javaATMProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
	
	private JTextField cardNumberField;
	private JPasswordField pinField;
	
	public ATMGUI(){
		setTitle("ATM Login");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel inputpanel = new JPanel(new GridLayout(2,5,5,5));
		
		JLabel cardLabel = new JLabel("Card Number:");
		cardNumberField = new JTextField();
		JLabel pinLabel = new JLabel("PIN:");
		pinField = new JPasswordField();
		
		inputpanel.add(cardLabel);
		inputpanel.add(cardNumberField);
		inputpanel.add(pinLabel);
		inputpanel.add(pinField);
		
		JButton loginButton = new JButton("Login");
		JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		buttonpanel.add(loginButton);
		
		add(inputpanel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cardNumber = cardNumberField.getText();
				String pin = new String(pinField.getPassword());
				
				if(ATMOperations.authenticateUser(cardNumber, pin)) {
					new ATMMenu(cardNumber);
					dispose();
				}else {
					JOptionPane.showInternalMessageDialog(null, "Invalid Card Number or Password!");
				}
			}
		}  );
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ATMGUI();
	}
}
