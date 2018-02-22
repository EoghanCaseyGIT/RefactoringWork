import java.io.EOFException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ReadRecords extends BankApplication {
	
	private static final long serialVersionUID = 1L;

	public static void readRecords()
	   {
	
	      RandomAccessBankAccount record = new RandomAccessBankAccount();

	      try // read a record and display
	      {
	         while ( true )
	         {
	            do
	            {
	            	if(input!=null)
	            		record.read( input );
	            } while ( record.getAccountID() == 0 );

	       
	            
	            BankAccount ba = new BankAccount(record.getAccountID(), record.getAccountNumber(), record.getFirstName(),
	                    record.getSurname(), record.getAccountType(), record.getBalance(), record.getOverdraft());
	            
	            
	            Integer key = Integer.valueOf(ba.getAccountNumber().trim());
			
				int hash = (key%TABLE_SIZE);
		
				
				while(table.containsKey(hash)){
			
					hash = hash+1;
				}
				
	            table.put(hash, ba);
		

	         } // end while
	      } // end try
	      catch ( EOFException eofException ) // close file
	      {
	         return; // end of file was reached
	      } // end catch
	      catch ( IOException ioException )
	      {
	    	  JOptionPane.showMessageDialog(null, "Error reading file.");
	         System.exit( 1 );
	      } // end catch
	   }

	
	

}
