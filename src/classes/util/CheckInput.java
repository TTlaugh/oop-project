package classes.util;

import java.util.regex.Pattern;

public class CheckInput {

	public static String toStrNumberic(String str) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		if (pattern.matcher(str).matches())
			return str;
		return null;
	}

	public static boolean toYesNo(String yn) {
		if (yn.equalsIgnoreCase("yes") || yn.equalsIgnoreCase("y") || yn.equalsIgnoreCase(""))
			return true;
		return false;
	}
}
