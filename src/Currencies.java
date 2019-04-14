public class Currencies {

	static String[] currencyName = new String[8];
	static Double[] currencyFactor = new Double[8];
	static String[] currencySymbol = new String[8];
	static int i = 0;
	static int j = 0;
	static int k = 0;

	/*
	 * Method simply dumps all Strings which are passed as 
	 * arguments to it into a List called "currencyArr"
	 */
	public static void currency(String...currencyArr) {
		for (String value: currencyArr) {

			if (value.matches("^[a-zA-Z]*$")) {
				currencyName[i] = value;
				System.out.println("currencyArr[" + i + "] = " + currencyName[i]);
				i++;
			}
		}
	}

	/*
	 * returns the name of the factor
	 */
	public static String getName() {
		return currencyName[CurrencyPanel.currencyCombo.getSelectedIndex()];
	}

	/*
	 * Method simply dumps all Doubles which are passed as 
	 * arguments to it into a List called "factorArr"
	 */
	public static void factor(Double...factorArr) {
		for (Double value: factorArr) {

			currencyFactor[j] = value;
			System.out.println("factorArr[" + j + "] = " + currencyFactor[j]);
			j++;
		}
	}

	/*
	 * returns the factor to be used
	 */
	public static Double getFactor() {
		return currencyFactor[CurrencyPanel.currencyCombo.getSelectedIndex()];
	}


	/*
	 * Method simply dumps all Strings which are passed as 
	 * arguments to it into a List called "symbolArr"
	 */
	public static void symbol(String...symbolArr) {
		for (String value: symbolArr) {

			currencySymbol[k] = value;

			System.out.println("symbolArr[" + k + "] = " + currencySymbol[k]);
			k++;
		}
	}

	/*
	 * gets the current symbol
	 */
	public static String getSymbol() {
		return currencySymbol[CurrencyPanel.currencyCombo.getSelectedIndex()];
	}

	/*
	 * returns the default symbol - in this case the pound sign if reversing calc
	 */
	public static String getDefault() {
		return "£";
	}
}