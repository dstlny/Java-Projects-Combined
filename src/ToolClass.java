import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


/**
 * Merged both Dialog class and Calculation class into one unified ToolClass.
 * Easier for maintenance.
 */
public class ToolClass {

	private static double result;
	private static Date now = new Date();

	public static void setResult(double setResult){
		result = setResult;
	}

	public static double getResult(){
		return result;
	}

	public static enum ERROR {
		BAD,
		MINOR,
		CRITICAL,
		EXIT_STATUS;
	}

	/*
	 * Just some methods to generate results
	 */
	public static void convertMulti(double factor, double input) {
		setResult(input * factor);
	}

	public static void convertDivi(double factor, double input) {
		setResult(input / factor);
	}

	public static void convertPlus(double factor, double input) {
		setResult(input + factor);
	}

	public static void convertNeg(double factor, double input) {
		setResult(input - factor);
	}

	/*
	 * Just some methods for dialogBoxes
	 */
	public static void emptyTextFieldDialog() {
		JOptionPane.showMessageDialog(null, "The textfield cannot be empty!", "Empty textfield detected",
				JOptionPane.ERROR_MESSAGE);
		MeasurementPanel.globalClearButton.doClick();
	}

	public static void formatExceptionDialog() {
		JOptionPane.showMessageDialog(null,
				"The value entered may not be valid. \nPlease ensure that you are entering a valid value!",
				"Invalid value detected", JOptionPane.ERROR_MESSAGE);
		MeasurementPanel.globalClearButton.doClick();
		MeasurementPanel.globalCountLabel.setText("Conversion Count: " + MeasurementPanel.globalCount);
	}

	public static void aboutProgramDialog() {
		JOptionPane.showMessageDialog(null,
				"This application allows the user to convert between different measurements, \nincluding cross conversion between different currencies and File based conversion. \nLuke Elam \n<html><i>Copyright � 2018, Luke Elam, All Rights Reserved\n</i>\n As well as allows a user to find out whether or not file-system contents have been potentially tampered with or not.</html>",
				"About this application", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void incorrectCurrencyNameDialog() {
		JOptionPane.showMessageDialog(null, "A currency name appears to be corrupt.",
				"Error " + "[" + MainPanel.globalErrorCount + "] -> Corrupt currency name detected!",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void lineCorruptDialog() {
		JOptionPane.showMessageDialog(null, "A line appears to be corrupt or is missing a comma.",
				"Error " + "[" + MainPanel.globalErrorCount + "] -> Corrupt line detected!", JOptionPane.ERROR_MESSAGE);
	}

	public static void incorrectCurrencyDialog() {
		JOptionPane.showMessageDialog(null, "A currency appears to be corrupt.",
				"Error " + "[" + MainPanel.globalErrorCount + "] -> Corrupt currency detected!",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void incorrectFactorDialog() {
		JOptionPane.showMessageDialog(null, "A factor appears to be corrupt.",
				"Error " + "[" + MainPanel.globalErrorCount + "] -> Corrupt factor detected!",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void incorrectSymbolDialog() {
		JOptionPane.showMessageDialog(null, "A symbol appears to be corrupt.",
				"Error " + "[" + MainPanel.globalErrorCount + "] -> Corrupt symbol detected!",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void incorrectFilePathDialog() {
		JOptionPane.showMessageDialog(null, "File path is incorrect, or file does not exist.", "File not found",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void selectionCancelledDialog() {
		JOptionPane.showMessageDialog(null, "File selection cancelled by user.", "Selection cancelled.",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void availableCurrenciesDialog() {
		if (CurrencyPanel.currencyCombo.getSelectedItem().equals("THB") && CurrencyPanel.currencyCombo.getSelectedIndex() == 0) {
			/*
			 * If the first index in the combo is THB, then we know it's the
			 * incorrect_currency file, thus do the following
			 */
			JOptionPane.showMessageDialog(null,
					"Due to this circumstance, the ComboBox has been disabled. \nThis will be re-enabled if you import a file which does not have corrupt values.",
					"Currencies available are:" + " [" + Currencies.getName() + "]", JOptionPane.INFORMATION_MESSAGE);
			CurrencyPanel.currencyCombo.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null,
					"You can select the currency you would like to convert to and from\nin the ComboBox within the Currency Conversion section.",
					"Currencies available are: " + Arrays.toString(Currencies.currencyName),
					JOptionPane.INFORMATION_MESSAGE);
			CurrencyPanel.currencyCombo.setEnabled(true);
		}
	}

	public static void fileSystemError() {
		JOptionPane.showMessageDialog(null,
				"The path you are trying to access cannot be accessed due to a file in the directory being in use by the filesystem at this current time.",
				"ERROR ACCESSING PATH", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void exceptionIO() {
		JOptionPane.showMessageDialog(null,
				"An error occured trying to read from this directory",
				"A IOException HAS OCCURED", JOptionPane.ERROR_MESSAGE);
	}

	public static void selectionCancelled() {
		JOptionPane.showMessageDialog(null,
				"Selection has been cancelled by the user.",
				"File / Directory selection cancelled.", JOptionPane.WARNING_MESSAGE);
	}

	public static void FilePossiblyTampered(Set<String> string, Set<String> name) {
		UIManager.put("OptionPane.minimumSize",new Dimension(300,100)); 
			for(String s : string) {
				for(String t : name ) {
					JOptionPane.showMessageDialog(null, s,  t + " HAS BEEN TAMPERED", JOptionPane.WARNING_MESSAGE);
			    }
			}
		}

	public static void fileNotFound(String filename) {
		JOptionPane.showMessageDialog(null,
				"File " + filename + " was not found.",
				null, JOptionPane.WARNING_MESSAGE);
	}

	public static void logError(Date date, ERROR error, String file, int line, String error_string){
		File debug_log = new File("debug_log.txt");
		String full_error = "<"+date+ " [ERROR_LEVEL : "+error+"]> @ " + file + ":" + line  + " : " + error_string;

		try {
			BufferedWriter WRITE_DEBUG =  new BufferedWriter(new  FileWriter(debug_log, true));	
			WRITE_DEBUG.write(full_error);
			WRITE_DEBUG.newLine();
			WRITE_DEBUG.close();
		} catch(Exception e){
			logError(now, ERROR.CRITICAL, "ToolClass.java", 178, e.toString());
		}
			
	}

}