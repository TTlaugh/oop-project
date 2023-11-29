package classes.util;

public class Date {

	private String day;
	private String month;
	private String year;

	public Date(String day, String month, String year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date(String ddmmyyyy) {
		String arrOfStr[] = ddmmyyyy.split("-");
		this.day = arrOfStr[0];
		this.month = arrOfStr[1];
		this.year = arrOfStr[2];
	}

	public Date() {
		this.day = null;
		this.month = null;
		this.year = null;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return day + "-" + month + "-" + year;
	}

}
