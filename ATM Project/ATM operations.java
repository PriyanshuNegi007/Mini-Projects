package javaATMProject;

import java.sql.*;

public class ATMOperations {
	
	public static boolean authenticateUser(String cardNumber, String pin) {
		try(Connection conn = DatabaseConnection.getConnection()) {
			
			String sql ="SELECT * FROM users WHERE card_number = ? AND pin = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, cardNumber);
			stmt.setString(2, pin);
			
			ResultSet result = stmt.executeQuery();
			
			return result.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static double getBalance(String cardNumber) {
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = "SELECT balance FROM users WHERE card_number = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cardNumber);

			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				return result.getDouble("balance");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public static boolean withdrawMoney(String cardNumber, double amount) {
		try(Connection conn= DatabaseConnection.getConnection()){
		    
			conn.setAutoCommit(false);
			
			//Check Balance
			double balance = getBalance(cardNumber);
			
			if(balance < amount) {
				System.out.println("Insufficient Funds! you're poor");
				return false;
			}
			
			//Deduct amount from balance
			String sql ="UPDATE users SET balance = balance - ? WHERE card_number =?";
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setDouble(1, amount);
			stmt.setString(2, cardNumber);
			
			int rowsUpdated = stmt.executeUpdate();
			
			
			//Record transaction
			if(rowsUpdated > 0) {
				String transSql  = "INSERT INTO transactions(card_number,transaction_type,amount) VALUES (?,'Withdrawal', ?)";
			
				try(PreparedStatement transStmt = conn.prepareStatement(transSql)){
				
				transStmt.setString(1, cardNumber);
				transStmt.setDouble(2, amount);
				
				transStmt.executeUpdate();
				}
				
				conn.commit();
				return true;
			}else {
				conn.rollback();
				return false;
			}
		  }
			
		}catch(SQLException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	public static void changePin(String cardNumber, String newPin) {
		try(Connection conn = DatabaseConnection.getConnection()){
			
			String sql = "UPDATE users SET pin = ? WHERE card_number = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newPin);
			stmt.setString(2, cardNumber);
			
			stmt.executeUpdate();
			
			System.out.println("Pin updated Succesfully bhulna mat!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void printMiniStatement(String cardNumber) {
		try(Connection conn = DatabaseConnection.getConnection()){
			
			String sql = "SELECT * FROM transactions WHERE card_number = ? ORDER BY transaction_date DESC LIMIT 5";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cardNumber);
			
			ResultSet result = stmt.executeQuery();
			System.out.println("Last 5 transactions: ");
			while(result.next()) {
				System.out.println(result.getString("transaction_type") + "-" + result.getDouble("amount") + "-" + result.getTimestamp("transaction_date"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
