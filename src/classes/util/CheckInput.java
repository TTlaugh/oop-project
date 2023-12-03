package classes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CheckInput {

	public static String toDateFormat(String dateStr) {
		InputValidator validator = new dateValidator("dd-MM-yyyy");
		return validator.isValid(dateStr) ? dateStr : null;
	}

	public static String toStrInteger(String str) {
		Pattern pattern = Pattern.compile("-?\\d+");
		if (pattern.matcher(str).matches())
			return str;
		return null;
	}

	public static Integer toIntNumeric(String str) {
		Pattern pattern = Pattern.compile("\\d+");
		if (pattern.matcher(str).matches()) {
			return Integer.parseInt(str);
		}
		return -1;
	}

	public static String toStrNumberic(String str) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		if (pattern.matcher(str).matches())
			return str;
		return null;
	}

	public static String toStrNumberic(String str, int low, int high) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		if (pattern.matcher(str).matches())
			if (Integer.parseInt(str) >= low && Integer.parseInt(str) <= high)
				return str;
		return null;
	}

	public static Integer toIntNumeric(String str, int low, int high) {
		Pattern pattern = Pattern.compile("\\d+");
		if (pattern.matcher(str).matches()) {
			int intValue = Integer.parseInt(str);
			if (intValue >= low && intValue <= high) {
				return intValue;
			}
		}
		return -1;
	}

	public static boolean toYesNo(String yn) {
		if (yn.equalsIgnoreCase("yes") || yn.equalsIgnoreCase("y") || yn.equalsIgnoreCase(""))
			return true;
		return false;
	}

	public static String toRole(String username) {
		InputValidator adminValidator = new regexValidator("^[aA][dD][mM][iI][nN]$");
		InputValidator professorValidator = new regexValidator("^IT\\d{3}\\d{3}$");
		InputValidator studentValidator = new regexValidator("^IT\\d{4}\\d\\d{3}$");
		if (adminValidator.isValid(username))
			return "admin";
		else if (professorValidator.isValid(username))
			return "professor";
		else if (studentValidator.isValid(username))
			return "student";
		return null;
	}

	public static String toFullName(String fullName) {
		InputValidator validator = new regexValidator("^[a-zA-Z_]{4,}$");
		return validator.isValid(fullName) ? fullName : null;
	}

	public static String toGender(String gender) {
		if (gender.equalsIgnoreCase("male"))
			return "male";
		else if (gender.equalsIgnoreCase("female"))
			return "female";
		return "other";
	}

	public static String toPhoneNumber(String phoneNumber) {
		InputValidator validator = new regexValidator("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
		return validator.isValid(phoneNumber) ? phoneNumber : null;
	}
}
