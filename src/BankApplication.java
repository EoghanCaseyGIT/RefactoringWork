
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import net.miginfocom.swing.MigLayout;

public class BankApplication extends JFrame {
	
	private static final long serialVersionUID = 1L;
	ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
	static HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	public final static int TABLE_SIZE = 29;
	
	JMenuBar menuBar;
	JMenu navigateMenu, recordsMenu, transactionsMenu, fileMenu, exitMenu;
	JMenuItem nextItem, prevItem, firstItem, lastItem, findByAccount, findBySurname, listAll, createItem, modifyItem, deleteItem, setOverdraft, setInterest, deposit, withdraw, calcInterest, open, save, saveAs, closeApp;

	JButton firstItemButton, lastItemButton, nextItemButton, prevItemButton;
	JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	static JTextField overdraftTextField, accountIDTextField, accountNumberTextField, firstNameTextField, surnameTextField, accountTypeTextField, balanceTextField;
	static JFileChooser fc;
	static JTable jTable;
	static double interestRate;
	static int currentItem;
	public static RandomAccessFile input;
	public static RandomAccessFile output;
	static String fileToSaveAs = "";
	static boolean openValues;
	
	public BankApplication() {
		
		super("Bank Application");
		
		initComponents();
	}
	
	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());
		
		accountIDLabel = new JLabel("Account ID: ");
		accountIDTextField = new JTextField(15);
		accountIDTextField.setEditable(false);
		
		displayPanel.add(accountIDLabel, "growx, pushx");
		displayPanel.add(accountIDTextField, "growx, pushx, wrap");
		
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(false);
		
		displayPanel.add(accountNumberLabel, "growx, pushx");
		displayPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(false);
		
		displayPanel.add(surnameLabel, "growx, pushx");
		displayPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(false);
		
		displayPanel.add(firstNameLabel, "growx, pushx");
		displayPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(false);
		
		displayPanel.add(accountTypeLabel, "growx, pushx");
		displayPanel.add(accountTypeTextField, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setEditable(false);
		
		displayPanel.add(balanceLabel, "growx, pushx");
		displayPanel.add(balanceTextField, "growx, pushx, wrap");
		
		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setEditable(false);
		
		displayPanel.add(overdraftLabel, "growx, pushx");
		displayPanel.add(overdraftTextField, "growx, pushx, wrap");
		
		add(displayPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

		nextItemButton = new JButton(new ImageIcon("next.png"));
		prevItemButton = new JButton(new ImageIcon("prev.png"));
		firstItemButton = new JButton(new ImageIcon("first.png"));
		lastItemButton = new JButton(new ImageIcon("last.png"));
		
		buttonPanel.add(firstItemButton);
		buttonPanel.add(prevItemButton);
		buttonPanel.add(nextItemButton);
		buttonPanel.add(lastItemButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		navigateMenu = new JMenu("Navigate");
    	
	    	nextItem = new JMenuItem("Next Item");
	    	prevItem = new JMenuItem("Previous Item");
	    	firstItem = new JMenuItem("First Item");
	    	lastItem = new JMenuItem("Last Item");
	    	findByAccount = new JMenuItem("Find by Account Number");
	    	findBySurname = new JMenuItem("Find by Surname");
	    	listAll = new JMenuItem("List All Records");
	    	
	    	navigateMenu.add(nextItem);
	    	navigateMenu.add(prevItem);
	    	navigateMenu.add(firstItem);
	    	navigateMenu.add(lastItem);
	    	navigateMenu.add(findByAccount);
	    	navigateMenu.add(findBySurname);
	    	navigateMenu.add(listAll);
	    	
	    	menuBar.add(navigateMenu);
	    	
	    	recordsMenu = new JMenu("Records");
	    	
	    	createItem = new JMenuItem("Create Item");
	    	modifyItem = new JMenuItem("Modify Item");
	    	deleteItem = new JMenuItem("Delete Item");
	    	setOverdraft = new JMenuItem("Set Overdraft");
	    	setInterest = new JMenuItem("Set Interest");
	    	
	    	recordsMenu.add(createItem);
	    	recordsMenu.add(modifyItem);
	    	recordsMenu.add(deleteItem);
	    	recordsMenu.add(setOverdraft);
	    	recordsMenu.add(setInterest);
	    	
	    	menuBar.add(recordsMenu);
	    	
	    	transactionsMenu = new JMenu("Transactions");
	    	
	    	deposit = new JMenuItem("Deposit");
	    	withdraw = new JMenuItem("Withdraw");
	    	calcInterest = new JMenuItem("Calculate Interest");
	    	
	    	transactionsMenu.add(deposit);
	    	transactionsMenu.add(withdraw);
	    	transactionsMenu.add(calcInterest);
	    	
	    	menuBar.add(transactionsMenu);
	    	
	    	fileMenu = new JMenu("File");
	    	
	    	open = new JMenuItem("Open File");
	    	save = new JMenuItem("Save File");
	    	saveAs = new JMenuItem("Save As");
	    	
	    	fileMenu.add(open);
	    	fileMenu.add(save);
	    	fileMenu.add(saveAs);
	    	
	    	menuBar.add(fileMenu);
	    	
	    	exitMenu = new JMenu("Exit");
	    	
	    	closeApp = new JMenuItem("Close Application");
	    	
	    	exitMenu.add(closeApp);
	    	
	    	menuBar.add(exitMenu);
	    	
	    	setDefaultCloseOperation(EXIT_ON_CLOSE);
		
			setOverdraft.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.overdraft();
				}
			});
		
			
			ActionListener first = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionMethods.first();
				}
			};
			
	
			
			ActionListener next = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.next();
				}
			};
			
			
	
			ActionListener prev = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionMethods.prev();		
				}
			};
		
			ActionListener last = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionMethods.last();
				}
			};
			
			nextItemButton.addActionListener(next);
			nextItem.addActionListener(next);
			
			prevItemButton.addActionListener(prev);
			prevItem.addActionListener(prev);
	
			firstItemButton.addActionListener(first);
			firstItem.addActionListener(first);
	
			lastItemButton.addActionListener(last);
			lastItem.addActionListener(last);
			
			deleteItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){						
							ActionMethods.deleteItem();			
				}
			});
			
			createItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new CreateBankDialog(table);		
					
				}	
			});
			
			
			modifyItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.modify();
				}
			});
			
			setInterest.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.setInterest();
				}
			});
			
			listAll.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.listAll();
				}
			});
			
			open.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.open();
				}
			});
			
			save.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					writeFile();
				}
			});
			
			saveAs.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					saveFileAs();
				}
			});
			
			
			findBySurname.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					ActionMethods.findBySurname();
				}
			});
			
			findByAccount.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.findByAccount();			
				}
			});
			
			deposit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.deposit();
				}
			});
			
			withdraw.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.withdraw();
				}
			});
			
			calcInterest.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ActionMethods.calcInterest();
				}
			});
			
			closeApp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int answer = JOptionPane.showConfirmDialog(BankApplication.this, "Do you want to save before quitting?");
					if (answer == JOptionPane.YES_OPTION) {
						saveFileAs();
						dispose();
					}
					else if(answer == JOptionPane.NO_OPTION)
						dispose();
					else if(answer==0);									
				}
			});	
		}
	
			public static void writeFile(){
				SaveToFile.saveToFile();
				CloseFile.closeFile();
			}
			
			public static void saveFileAs(){
				SaveToFileAs.saveToFileAs();
				SaveToFile.saveToFile();	
				CloseFile.closeFile();
			}
			
			public static void readFile(){
				OpenFile.openFileRead();
			    ReadRecords.readRecords();
			    CloseFile.closeFile();		
			}
			
			
			public static void main(String[] args) {
				BankApplication ba = new BankApplication();
				ba.setSize(1200,400);
				ba.pack();
				ba.setVisible(true);
			}
		
		
	}
	