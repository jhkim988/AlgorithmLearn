
public class Date {
	private int year;
	private int month;
	private int day;
	
	Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	Date(String dateString) { // yyyy/mm/dd
		String[] dates = dateString.split("/");
		year = Integer.parseInt(dates[0]);
		month = Integer.parseInt(dates[1]);
		day = Integer.parseInt(dates[2]);
	}
	
	public int year() {
		return year;
	}
	
	public int month() {
		return month;
	}
	public int day() {
		return day;
	}
}
