import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ActionMethods extends BankApplication{

	private static final long serialVersionUID = 1L;

	public static void overdraft() {
		if(table.get(currentItem).getAccountType().trim().equals("Current")){
			String newOverdraftStr = JOptionPane.showInputDialog(null, "Enter new Overdraft", JOptionPane.OK_CANCEL_OPTION);
			overdraftTextField.setText(newOverdraftStr);
			table.get(currentItem).setOverdraft(Double.parseDouble(newOverdraftStr));
		}
		else
			JOptionPane.showMessageDialog(null, "Overdraft only applies to Current Accounts");
	
	}
	
	public static void findBySurname() {
		
		String sName = JOptionPane.showInputDialog("Search for surname: ");
		boolean found = false;
		
		 for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			   
			 BankAccount val = entry.getValue();
			 
			if(sName.equalsIgnoreCase((val.getSurname().trim()))){
				 found = true;
				 accountIDTextField.setText(val.getAccountID()+"");
				 accountNumberTextField.setText(val.getAccountNumber());
				 surnameTextField.setText(val.getSurname());
				 firstNameTextField.setText(val.getFirstName());
				 accountTypeTextField.setText(val.getAccountType());
				 balanceTextField.setText(val.getBalance()+"");
				 overdraftTextField.setText(val.getOverdraft()+"");
			 }
		 }		
		 if(found)
			 JOptionPane.showMessageDialog(null, "Surname  " + sName + " found.");
		 else
			 JOptionPane.showMessageDialog(null, "Surname " + sName + " not found.");
	}
	
	
	public static void deleteItem() {
		
		table.remove(currentItem);
		JOptionPane.showMessageDialog(null, "Account Deleted");
		

		currentItem=0;
		while(!table.containsKey(currentItem)){
			currentItem++;
		}
		Display.displayDetails(currentItem);
	}
	
	public static void setInterest() {
		 String interestRateStr = JOptionPane.showInputDialog("Enter Interest Rate: (do not type the % sign)");
		 if(Double.parseDouble(interestRateStr)>100) {
			 JOptionPane.showMessageDialog(null, "Can't have interest rates higher than 100, hopefully");
			 
			 
		 } else {
			 
		 
		 if(interestRateStr!=null)
			 interestRate = Double.parseDouble(interestRateStr);
		 }
	}
	
	public static void modify() {
		surnameTextField.setEditable(true);
		firstNameTextField.setEditable(true);
		
		openValues = true;
	}
	
	public static void listAll() {
		JFrame frame = new JFrame("TableDemo");
		
		String col[] = {"ID","Number","Name", "Account Type", "Balance", "Overdraft"};
		
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		jTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setAutoCreateRowSorter(true);
		
		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
		   
		    
		    BankAccount val = entry.getValue();
			Object[] objs = {val.getAccountID(), val.getAccountNumber(), 
		    				val.getFirstName().trim() + " " + val.getSurname().trim(), 
		    				val.getAccountType(), val.getBalance(), 
		    				val.getOverdraft()};

		    tableModel.addRow(objs);
		}
		frame.setSize(600,500);
		frame.add(scrollPane);
        frame.setVisible(true);		
	}

	
	public static void open() {
		readFile();
		currentItem=0;
		while(!table.containsKey(currentItem)){
			currentItem++;
		}
		Display.displayDetails(currentItem);
	}
	
	public static void findByAccount() {
		String accNum = JOptionPane.showInputDialog("Search for account number: ");
		boolean found = false;
	
		 for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			   
			 BankAccount val = entry.getValue();
			if(accNum.equals(val.getAccountNumber().trim())){
				 found = true;
				 accountIDTextField.setText(val.getAccountID()+"");
				 accountNumberTextField.setText(val.getAccountNumber());
				 surnameTextField.setText(val.getSurname());
				 firstNameTextField.setText(val.getFirstName());
				 accountTypeTextField.setText(val.getAccountType());
				 balanceTextField.setText(val.getBalance()+"");
				 overdraftTextField.setText(val.getOverdraft()+"");						
				 
			 }			 
		 }
		 if(found)
			 JOptionPane.showMessageDialog(null, "Account number " + accNum + " found.");
		 else
			 JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
	}
	
	public static void deposit() {
			String accNum = JOptionPane.showInputDialog("Account number to deposit into: ");
			boolean found = false;
			if(accNum!=null) {
	
			
			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
				if(accNum.equals(entry.getValue().getAccountNumber().trim())){
					found = true;
					String toDeposit = JOptionPane.showInputDialog("Account found, Enter Amount to Deposit: ");
					if(toDeposit!=null) {
						
					
						if (Double.parseDouble(toDeposit)<0) {
							JOptionPane.showMessageDialog(null, "Can't deposit a negative amount.");
						}
						else {
							
					
						entry.getValue().setBalance(entry.getValue().getBalance() + Double.parseDouble(toDeposit));
						Display.displayDetails(entry.getKey());
						}
					} else {
						Display.displayDetails(entry.getKey());
					}
				}
			}
			if (!found)
				JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
		
		} else {
			
			Display.displayDetails(currentItem);
		}
	}
	
	public static void withdraw() {
		String accNum = JOptionPane.showInputDialog("Account number to withdraw from: ");
		if(accNum!=null) {

		
			for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
				

				BankAccount val = entry.getValue();
				if(accNum.equals(val.getAccountNumber().trim())){
					
					//added in, taken from 484
					String toWithdraw = JOptionPane.showInputDialog("Account found, Enter Amount to Withdraw: ");
					if(toWithdraw!=null) {

						if(val.getAccountType().trim().equals("Current")){
							if(Double.parseDouble(toWithdraw) > val.getBalance() + val.getOverdraft())
								JOptionPane.showMessageDialog(null, "Transaction exceeds overdraft limit");
							else{
								val.setBalance(val.getBalance() - Double.parseDouble(toWithdraw));
								Display.displayDetails(entry.getKey());
							}
						}
						else if(val.getAccountType().trim().equals("Deposit")){
							if(Double.parseDouble(toWithdraw) <= val.getBalance()){
								val.setBalance(val.getBalance()-Double.parseDouble(toWithdraw));
								Display.displayDetails(entry.getKey());
							}
							else
								JOptionPane.showMessageDialog(null, "Insufficient funds.");
						}
					} else 
						{
							Display.displayDetails(entry.getKey());
						}	
				}	
				else
					JOptionPane.showMessageDialog(null, "Account Not Found");
			}
		}  else {
			
			Display.displayDetails(currentItem);
		}
	}
	
	public static void calcInterest() {
		for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
			if(entry.getValue().getAccountType().equals("Current")){
				double equation = 1 + ((interestRate)/100);
				entry.getValue().setBalance(entry.getValue().getBalance()*equation);
				//System.out.println(equation);
				JOptionPane.showMessageDialog(null, "Balances Updated");
				
				Display.displayDetails(entry.getKey());
			}
		}
	}
	
	public static void next() {
		ArrayList<Integer> keyList = new ArrayList<Integer>();
		int i=0;

			while(i<TABLE_SIZE){
				i++;
				if(table.containsKey(i))
					keyList.add(i);
			}
			
			int maxKey = Collections.max(keyList);
	
			saveOpenValues();	
	
				if(currentItem<maxKey){
					currentItem++;
					while(!table.containsKey(currentItem)){
						currentItem++;
					}
				}
				Display.displayDetails(currentItem);			
		}
	
	
	public static void saveOpenValues(){		
		if (openValues){
			surnameTextField.setEditable(false);
			firstNameTextField.setEditable(false);
				
			table.get(currentItem).setSurname(surnameTextField.getText());
			table.get(currentItem).setFirstName(firstNameTextField.getText());
		}
	}	
	
	public static void first() {

		saveOpenValues();
		
		while(!table.containsKey(currentItem)){
			currentItem++;
		}
		Display.displayDetails(currentItem);
	}
	
	public static void prev() {
		
		ArrayList<Integer> keyList = new ArrayList<Integer>();
		int i=0;

		while(i<TABLE_SIZE){
			i++;
			if(table.containsKey(i))
				keyList.add(i);
		}
		
		int minKey = Collections.min(keyList);
		//System.out.println(minKey);
		
		if(currentItem>minKey){
			currentItem--;
			while(!table.containsKey(currentItem)){
				currentItem--;
			}
		}
		Display.displayDetails(currentItem);		
	}
	
	
	public static void last() {
		saveOpenValues();
		
		
		while(!table.containsKey(currentItem)){
			currentItem--;
			
		}
		
		Display.displayDetails(currentItem);
	}
	
}
