import edu.princeton.cs.algs4.StdOut;

class SmartDateOld {
	private int year;
	private int month;
	private int day;

	SmartDateOld(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;

		if (month < 1 || month > 12)
			throw new IllegalArgumentException();

		int[][] days = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (day > days[isLeap()][month - 1] || day < 1)
			throw new IllegalArgumentException();
	}

	int isLeap() {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
	}

	public String toString() {
		return year + " / " + month + " / " + day;
	}
}

public class Exercise01_02_11 {
	// 1.2.11
	// Date 클래스를 수정하여 날짜 값이 유효하지 않을 때
	// 익셉션을 발생시키는 SmartDate 클래스를 만들어보라.
	

	
	public static void main(String[] args) {
		SmartDateOld date1 = new SmartDateOld(2020, 2, 28);
		SmartDateOld date2 = new SmartDateOld(2020, 2, 29);
//		SmartDateOld date3 = new SmartDateOld(2020, 2, 30);
//		SmartDateOld date4 = new SmartDateOld(2019, 2, 29);
//		SmartDateOld date5 = new SmartDateOld(2021, 13, 5); 
//		SmartDateOld date6 = new SmartDateOld(2021, -3, -48);
		
		StdOut.println(date1);
		StdOut.println(date2);
//		StdOut.println(date3);
//		StdOut.println(date4);
//		StdOut.println(date5);
//		StdOut.println(date6);
		
	}
}
