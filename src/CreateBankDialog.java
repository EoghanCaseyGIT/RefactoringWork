
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import net.miginfocom.swing.MigLayout;

public class CreateBankDialog extends JFrame {


	private static final long serialVersionUID = 1L;
	private final static int TABLE_SIZE = 29;
	Random rand = new Random();

	HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	
	
	
	// Constructor code based on that for the Create and Edit dialog classes in the Shapes exercise.

	JLabel accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	
	
	final JTextField accountNumberTextField, firstNameTextField, surnameTextField, accountTypeTextField, balanceTextField, overdraftTextField;
	
	CreateBankDialog(HashMap<Integer, BankAccount> accounts) {
		
		super("Add Bank Details");
		
		table = accounts;
		
		setLayout(new BorderLayout());
		
		JPanel dataPanel = new JPanel(new MigLayout());
		
		
		String[] comboTypes = {"Current", "Deposit"};
		
		JComboBox<String> comboBox = new JComboBox<>(comboTypes);
		
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(true);
		
		dataPanel.add(accountNumberLabel, "growx, pushx");
		dataPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(true);
		
		dataPanel.add(surnameLabel, "growx, pushx");
		dataPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(true);
		
		dataPanel.add(firstNameLabel, "growx, pushx");
		dataPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(true);
		
		dataPanel.add(accountTypeLabel, "growx, pushx");	
		dataPanel.add(comboBox, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setText("0.0");
		balanceTextField.setEditable(false);
		
		dataPanel.add(balanceLabel, "growx, pushx");
		dataPanel.add(balanceTextField, "growx, pushx, wrap");
		
		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setText("0.0");
		overdraftTextField.setEditable(false);
		
		dataPanel.add(overdraftLabel, "growx, pushx");
		dataPanel.add(overdraftTextField, "growx, pushx, wrap");
		
		add(dataPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				String accountNumber = accountNumberTextField.getText();
				
				
				String surname = surnameTextField.getText();
				String firstName = firstNameTextField.getText();
			
				String accountType = comboBox.getSelectedItem().toString();

		
				if (accountNumber != null && Integer.parseInt(accountNumber) >= 0 && accountNumber.length()==8 && !surname.isEmpty()  && !firstName.isEmpty()  && accountType != null) {
					try {
						
						boolean accNumTaken=false;
							
						int accountID = 1;
						
						 for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {
							 
							 while(accountID == entry.getValue().getAccountID()){
								 accountID++;
							 }		 
						 }
					 
							for (Map.Entry<Integer, BankAccount> entry : table.entrySet()) {					
								 if(entry.getValue().getAccountNumber().trim().equals(accountNumberTextField.getText())){
									 accNumTaken=true;	
									 
								 }
							 }
						
						if(!accNumTaken){
						
						
							BankAccount account = new BankAccount(accountID, accountNumber, surname, firstName, accountType, 0.0, 0.0);
						
							
							int key = Integer.parseInt(account.getAccountNumber());
							
							int hash = (key%TABLE_SIZE);
							
							while(table.containsKey(hash)){
								hash = hash+1;
							}
							table.put(hash, account);
						}
						else{
							JOptionPane.showMessageDialog(null, "Account Number must be unique");
						}
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Number format exception");					
					}
				}
				else JOptionPane.showMessageDialog(null, "Please make sure all fields have values, and Account Number is a unique 8 digit number");
				dispose();
			}
			
		});
		
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setSize(400,800);
		pack();
		setVisible(true);

	}
	
	
	
}