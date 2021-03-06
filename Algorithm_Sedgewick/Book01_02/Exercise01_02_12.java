import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class SmartDate {
	private int year;
	private int month;
	private int day;
	private static final int[][] days = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };

	SmartDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;

		if (month < 1 || month > 12)
			throw new IllegalArgumentException();

		if (day > days[isLeap(year)][month - 1] || day < 1)
			throw new IllegalArgumentException();
	}

	int isLeap(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
	}

	public String toString() {
		return year + "/" + month + "/" + day + "/" + dayOfTheWeek();
	}

	private int yearCalculate(SmartDate that) {
		int y = this.year - that.year;
		int day = 0;
		int i = 0;
		if (y > 0) {
			while (y-- != 0) {
				day += (isLeap(that.year + i) == 1) ? 366 : 365;
				i++;
			}
			return day;
		} else if (y < 0) {
			while (y++ != 0) {
				day -= (isLeap(this.year + i) == 1) ? 366 : 365;
				i++;
			}
			return day;
		} else {
			return day;
		}
	}

	private int monthCalculate(SmartDate that) {
		int year = (this.year > that.year) ? this.year : that.year;
		int m = this.month - that.month;
		int d = this.day - that.day;
		int day = 0;
		if (m > 0) {
			// Set same year..(year calculate), this > that
			// days[isLeap(year)][that.month - 1] - that.day + Months + this.day
			day += days[isLeap(year)][that.month - 1] + d;
			m--;
			int i = 0;
			while (m-- != 0) {
				i++;
				day += days[isLeap(year)][that.month - 1 + i];
			}
			return day;
		} else if (m < 0) {
			// Set same year.. this < that
			// days[isLeap(year)][this.month - 1] - this.day + Months + that.month
			day -= days[isLeap(year)][this.month - 1] + d;
			m++;
			int i = 0;
			while (m++ != 0) {
				i++;
				day -= days[isLeap(year)][this.month - 1 + i];
			}
			return day;
		} else {
			return d;
		}
	}

	int substraction(SmartDate that) {
		int day = 0;
		day += yearCalculate(that);
		day += monthCalculate(that);
		return day;
	}

	String dayOfTheWeek() {
		String[] dayOfWeek = { "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday" };
		SmartDate baseDate = new SmartDate(2000, 1, 1); // Saturday
		return dayOfWeek[(substraction(baseDate) + 5) % 7];
	}
}

public class Exercise01_02_12 {
	// 1.2.12
	// SmartDate에 요일을 문자열로 출력하는 dayOfTheWeek() 메서드를 추가하라.
	// 즉, 날짜의 요일에 맞게
	// "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday"
	// 중 하나를 출력한다.
	// 2000년 이후 날짜만 사용된다고 가정해도 된다.

	public static void main(String[] args) {
		int T = 10;
		for(int i = 0; i < T; i++) {
			int year = StdRandom.uniform(2000, 2100);
			int month = StdRandom.uniform(1, 13);
			int day = StdRandom.uniform(1, 29);
			StdOut.println(new SmartDate(year, month, day));
		}
	}
}
