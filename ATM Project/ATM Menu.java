package javaATMProject;

import java.awt.GridLayout;
import javax.swing.*;

public class ATMMenu extends JFrame {
	
	public ATMMenu(String cardNumber) {
		setTitle("ATM Menu");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(5,1,5,5));
		
		JButton checkBalance = new JButton("Check Balance");
		JButton withdraw = new JButton("Withdraw");
		JButton changePin = new JButton("Change PIN");
		JButton miniStatement = new JButton("Print MiniStatement");
		JButton logout = new JButton("Log Out");
		
		checkBalance.addActionListener(e->
		JOptionPane.showMessageDialog(null, "Balance: "+ ATMOperations.getBalance(cardNumber))
		);
		
		withdraw.addActionListener(e->{
			String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw: ");
			double amount = Double.parseDouble(amountStr);
			if(ATMOperations.withdrawMoney(cardNumber, amount)) {
				JOptionPane.showMessageDialog(null, "Withdraw Sucessfull");
			}else {
				JOptionPane.showMessageDialog(null, "Withdraw Failed");
			}
		});
		
		changePin.addActionListener(e->{
			String newPin = JOptionPane.showInputDialog("Enter New Pin: ");
			ATMOperations.changePin(cardNumber, newPin);
			JOptionPane.showMessageDialog(null, "PIN changed sucessfully");
		});
		
		miniStatement.addActionListener(e->
		ATMOperations.printMiniStatement(cardNumber));
		
		logout.addActionListener(e->{
		JOptionPane.showMessageDialog(null, "Logout succesfully");
		dispose();
		
		SwingUtilities.invokeLater(()-> new ATMGUI());
		});
		
		add(checkBalance);
		add(withdraw);
		add(changePin);
		add(miniStatement);
		add(logout);
		
		setVisible(true);
		
	}

}
