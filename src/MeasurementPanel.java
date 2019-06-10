import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MeasurementPanel extends JPanel {
	/*
	 * Declaring all variables and components to be used within the GUI
	 */
	private String[] measurementList = {
			"Inches/Centimeters",
			"Miles/Kilometres",
			"Pounds/Kilograms",
			"Gallons/Litres",
			"Feet/Metres",
			"Celcius/Kelvin",
			"Acres/Hectare"
	};
	
	private JTextField measurementField;
	private JLabel measurementResultLabel;
	public static JLabel globalCountLabel;
	private JLabel measurementInputLabel;
	private JButton measurementConvertButton;
	public static JButton globalClearButton;
	private JComboBox < String > measurementCombo;
	public static JCheckBox globalReverseCalculation;
	private String measurementUserInputField;
	private double measurementFieldValue;
	private double measurementResult;
	private String measurementTitle;
	ActionListener convertListener = new ConvertListener();
	ActionListener ButtonListener = new ButtonListener();

	public static int globalCount = 0;
	private static Date now = new Date();

	/*
	 * All factors used during conversion
	 */
	private double inchTOcm, milesTOkilometres, poundsTOkilograms,
	gallonsTOlitres, feetTOmetres, celsiusTOkelvin,
	acresTOhectares;

	public MeasurementPanel(String myTitle) {
		measurementTitle = myTitle;
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(measurementTitle),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		/*
		 * Creating two listeners which will be used "convertListener" is used for
		 * conversions "convertDeleteListener" is used to set the values of
		 * conversionField, resultLabel, conversionCountLabel and calcCount all used to
		 * display details about number of conversions etc.
		 */

		measurementCombo = new JComboBox <String>(measurementList);
		measurementCombo.setBackground(Color.white);
		measurementCombo.setOpaque(true);

		globalReverseCalculation = new JCheckBox("Reverse Conversion");

		/*
		 * Creating inputLabel and conversionCountLabel
		 */
		measurementInputLabel = new JLabel("Enter value:");
		globalCountLabel = new JLabel("Conversion Count: ");

		/*
		 * Creating both the convertButon and clearButton Adding listeners to both
		 * buttons which determine when they are clicked, and what action should be
		 * carried out when done so.
		 */
		measurementConvertButton = new JButton("Convert");
		globalClearButton = new JButton("Clear");

		/*
		 * Creating both the resultLabel and conversionField
		 */
		measurementResultLabel = new JLabel("");
		measurementField = new JTextField(5);

		/*
		 * Adding all created assets to the JFrame. Adding toolTips to each of the
		 * created assets. Adding Listeners to select assets to carry out certain tasks.
		 */
		add(measurementCombo);
		measurementCombo.setToolTipText(
				"In this Combobox please specify the conversion which you would like to be carried out.");

		add(measurementInputLabel);
		add(measurementField);
		measurementField.setToolTipText("In this box please specify the value you want to convert.");
		measurementField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				measurementConvertButton.doClick();
			}
		});

		add(measurementConvertButton);
		measurementConvertButton.setToolTipText("Please click this button when you want to convert your chosen value.");
		measurementConvertButton.addActionListener(ButtonListener);
		measurementConvertButton.addActionListener(convertListener);

		add(globalClearButton);
		globalClearButton.setToolTipText("Please click this button if you want to clear all values onscreen..");
		globalClearButton.addActionListener(ButtonListener);

		add(measurementResultLabel);
		add(globalCountLabel);

		add(globalReverseCalculation);
		globalReverseCalculation.setBackground(Color.WHITE);
		globalReverseCalculation.setToolTipText("If you want to reverse the chosen calculation, please tick this box");

		/*
		 * Setting background colour.
		 */
		setBackground(Color.WHITE);
	}

	public class ConvertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			 * Trimming all leading and trailing white spaces from the text the user has
			 * input.
			 */
			measurementUserInputField = measurementField.getText().trim();

			if (!(measurementUserInputField.length() < 0)) {
				/*
				 * Simply trying to convert input to a double, if that fails outputting a Dialog
				 * box to users screen stating why Making sure that if the conversion fails,
				 * calCount is actually set to zero, then clicking the Clear Button to clear all
				 * values on screen
				 */
				try {
					measurementFieldValue = Double.parseDouble(measurementUserInputField);
				} catch (NumberFormatException exception) {
					measurementField.requestFocus();
					/*
					 * Invoke formatExceptionDialog() method from ToolClass()					 
					 * 
					 */
					ToolClass.formatExceptionDialog();
					ToolClass.logError(now, ToolClass.ERROR.CRITICAL, "MeasurementPanel.java", 146, exception.toString());
					globalClearButton.doClick();
					return;
				}

				/*
				 * Setup the correct factor/offset values depending on required conversion
				 */
				switch (measurementCombo.getSelectedIndex()) {
					case 0:
						if (!globalReverseCalculation.isSelected()) {
							// Inches -> CM
							globalCount++;
							inchTOcm = 2.54;
							ToolClass.convertMulti(inchTOcm, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// CM -> Inches
							globalCount++;
							inchTOcm = 2.54;
							ToolClass.convertDivi(inchTOcm, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
					case 1:
						if (!globalReverseCalculation.isSelected()) {
							// Miles -> Kilometres
							globalCount++;
							milesTOkilometres = 1.609344;
							ToolClass.convertMulti(milesTOkilometres, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Kilometres -> Miles
							globalCount++;
							milesTOkilometres = 1.609344;
							ToolClass.convertDivi(milesTOkilometres, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
					case 2:
						if (!globalReverseCalculation.isSelected()) {
							// Pounds -> Kilogrammes
							globalCount++;
							poundsTOkilograms = 0.4535;
							ToolClass.convertMulti(poundsTOkilograms, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Kilogrammes -> Pounds
							globalCount++;
							poundsTOkilograms = 0.4535;
							ToolClass.convertDivi(poundsTOkilograms, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
					case 3:
						if (!globalReverseCalculation.isSelected()) {
							// Gallons -> Litres
							globalCount++;
							gallonsTOlitres = 0.21997;
							ToolClass.convertDivi(gallonsTOlitres, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Litres -> Gallons
							globalCount++;
							gallonsTOlitres = 0.21997;
							ToolClass.convertMulti(gallonsTOlitres, measurementFieldValue);
							break;
						}
					case 4:
						if (!globalReverseCalculation.isSelected()) {
							// Feet -> Metres
							globalCount++;
							feetTOmetres = 0.3048;
							ToolClass.convertMulti(feetTOmetres, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Metres -> Feet
							globalCount++;
							feetTOmetres = 0.3048;
							ToolClass.convertDivi(feetTOmetres, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
					case 5:
						if (!globalReverseCalculation.isSelected()) {
							// Celsius -> Kelvin
							globalCount++;
							celsiusTOkelvin = 273.15;
							ToolClass.convertPlus(celsiusTOkelvin, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Kelvin -> Celsius
							globalCount++;
							celsiusTOkelvin = 273.15;
							ToolClass.convertNeg(celsiusTOkelvin, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
					case 6:
						if (!globalReverseCalculation.isSelected()) {
							// Acres -> Hectares
							globalCount++;
							acresTOhectares = 0.404685642;
							ToolClass.convertMulti(acresTOhectares, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						} else {
							// Hectares -> Acres
							globalCount++;
							acresTOhectares = 0.404685642;
							ToolClass.convertDivi(acresTOhectares, measurementFieldValue);
							measurementResult = ToolClass.getResult();
							break;
						}
				}

				/*
				 * Formatting output to two decimal places and changing result label to reflect
				 * result of conversion
				 */
				String numAsString = String.format("%.2f", measurementResult);
				measurementResultLabel.setText(numAsString);
			} else {
				if (measurementUserInputField.isEmpty() || CurrencyPanel.currencyUserInputField.isEmpty()) {
					/*
					 * if the text value is empty, simply display a dialog box stating so
					 */
					globalCountLabel.setText("Conversion Count: " + globalCount);
					ToolClass.emptyTextFieldDialog();
					ToolClass.logError(now, ToolClass.ERROR.MINOR, "MeasurementPanel.java", 281, "Empty Textfield");
				}
			}
		}
	}

	/*
	 * Listener used for listening to button presses, etc
	 */
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			/*
			 * Using e.getSource to determine which button was pressed, doing some actions
			 * depending on which it was.
			 */
			if (e.getSource() == measurementConvertButton && !measurementUserInputField.isEmpty()) {
				globalCountLabel.setText("Conversion Count: " + globalCount);
			} else {
				if (e.getSource() == globalClearButton) {
					measurementField.setText("");
					measurementResultLabel.setText("");
					CurrencyPanel.currencyConversionLabel.setText("");
					CurrencyPanel.currencyConversionField.setText("");
					globalCount = 0;
					globalCountLabel.setText("Conversion Count: " + globalCount);
				}
			}
		}

	}

}