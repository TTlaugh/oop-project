package classes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface InputValidator {
	boolean isValid(String str);
}

class regexValidator implements InputValidator {

	private String regex;

	public regexValidator(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean isValid(String str) {
		Pattern pattern = Pattern.compile(this.regex);
		if (pattern.matcher(str).matches())
			return true;
		return false;
	}

}

class dateValidator implements InputValidator {

	private String dateFormat;

	public dateValidator(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean isValid(String str) {
		DateFormat sdf = new SimpleDateFormat(this.dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}