public class Exercise01_02_19 {
	// 1.2.19
	// 연습 문제 1.2.13에서 나오는 Date와 Transaction에 대해서 표 1-43과 같은 포맷의 문자열을 받아
	// 의미에 맞게 내부 값을 초기화하는 생성자를 구현하라.
	
	class Date {
		private final int year;
		private final int month;
		private final int day;

		Date(int y, int m, int d) {
			year = y;
			month = m;
			day = d;
		}

		Date(String str) {
			String[] splitResult = str.split("/");
			year = Integer.parseInt(splitResult[0]);
			month = Integer.parseInt(splitResult[1]);
			day = Integer.parseInt(splitResult[2]);
		}
		
		int year() {
			return year;
		}

		int month() {
			return month;
		}

		int day() {
			return day;
		}

		public String toString() {
			return year + "/" + month + "/" + day;
		}
	}

	class Transaction {
		private final String who;
		private final Date when;
		private final double amount;

		Transaction(String who, Date when, double amount) {
			this.who = who;
			this.when = when;
			this.amount = amount;
		}

		Transaction(String transaction) {
			// jhKim/2021/03/06/3.14
			String[] parsingResult = transaction.split("/");
			who = parsingResult[0];
			when = new Date(Integer.parseInt(parsingResult[1]), Integer.parseInt(parsingResult[2]),
					Integer.parseInt(parsingResult[3]));
			amount = Double.parseDouble(parsingResult[4]);
		}

		String who() {
			return who;
		}

		Date when() {
			return when;
		}

		double amount() {
			return amount;
		}
		
		public String toString() {
			return who + when.toString() + amount;
		}
	}
}
