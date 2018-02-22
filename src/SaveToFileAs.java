import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveToFileAs extends BankApplication {
	
	private static final long serialVersionUID = 1L;

	public static void saveToFileAs()
	   {
		
		fc = new JFileChooser();
		
		 int returnVal = fc.showSaveDialog(null);
	   if (returnVal == JFileChooser.APPROVE_OPTION) {
	       File file = fc.getSelectedFile();
	     
	       fileToSaveAs = file.getName();
	       JOptionPane.showMessageDialog(null, "Accounts saved to " + file.getName());
	   } else {
	       JOptionPane.showMessageDialog(null, "Save cancelled by user");
	   }
  	    
	         try {
	        	 if(fc.getSelectedFile()==null){
	        		 JOptionPane.showMessageDialog(null, "Cancelled");
	        	 }
	        	 else
	        		 output = new RandomAccessFile(fc.getSelectedFile(), "rw" );
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      
	     
	   }

}
