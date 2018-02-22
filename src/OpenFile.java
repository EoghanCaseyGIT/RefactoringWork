import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OpenFile extends BankApplication {
	

	private static final long serialVersionUID = 1L;

	public static void openFileRead() {

		table.clear();

		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

		} else {
		}

		try // open file
		{
			if (fc.getSelectedFile() != null)
				input = new RandomAccessFile(fc.getSelectedFile(), "r");
		} // end try
		catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "File Does Not Exist.");
		} // end catch

	} // end method openFile
	
	

}
