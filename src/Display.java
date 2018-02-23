
public class Display extends BankApplication {

	private static final long serialVersionUID = 1L;

	public static void displayDetails(int currentItem) {	
		
		BankAccount tableGet = table.get(currentItem);
		accountIDTextField.setText(tableGet.getAccountID()+"");
		accountNumberTextField.setText(tableGet.getAccountNumber());
		surnameTextField.setText(tableGet.getSurname());
		firstNameTextField.setText(tableGet.getFirstName());
		accountTypeTextField.setText(tableGet.getAccountType());
		balanceTextField.setText(tableGet.getBalance()+"");
		if(accountTypeTextField.getText().trim().equals("Current"))
			overdraftTextField.setText(tableGet.getOverdraft()+"");
		else
			overdraftTextField.setText("Only applies to current accs");
	}
}
