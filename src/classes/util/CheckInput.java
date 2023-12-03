package classes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CheckInput {

	public static String toDateFormat(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return dateStr;
		}
		return null;
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
		Pattern admin = Pattern.compile("\\badmin\\b", Pattern.CASE_INSENSITIVE);
		Pattern professor = Pattern.compile("\\bIT\\d{3}\\d{3}\\b");
		Pattern student = Pattern.compile("\\bIT\\d{4}\\d\\d{3}\\b");
		if (admin.matcher(username).matches())
			return "admin";
		else if (professor.matcher(username).matches())
			return "professor";
		else if (student.matcher(username).matches())
			return "student";
		return null;
	}

	public static String toFullName(String fullName) {
		Pattern pattern = Pattern.compile("^[a-zA-Z_]{4,}$");
		if (pattern.matcher(fullName).matches())
			return fullName;
		return null;
	}

	public static String toGender(String gender) {
		if (gender.equalsIgnoreCase("male"))
			return "male";
		else if (gender.equalsIgnoreCase("female"))
			return "female";
		return "other";
	}

	public static String toPhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
		if (pattern.matcher(phoneNumber).matches())
			return phoneNumber;
		return null;
	}
}
