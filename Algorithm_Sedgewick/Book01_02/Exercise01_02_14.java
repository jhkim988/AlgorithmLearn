public class Exercise01_02_14 {
	// 1.2.14
	// Date의 equals()구현을 기본 모델로 하여 Transaction의 equals()메서드를 구현하라
	class Date {
		private final int year;
		private final int month;
		private final int day;

		Date(int y, int m, int d) {
			year = y;
			month = m;
			day = d;
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

		public boolean equals(Object x) {
			if (this == x)
				return true;
			if (x == null)
				return false;
			if (this.getClass() != x.getClass())
				return false;
			Date that = (Date) x;
			if (this.day != that.day)
				return false;
			if (this.month != that.month)
				return false;
			if (this.year != that.year)
				return false;
			return true;
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

		public boolean equals(Object x) {
			if (this == x)
				return true;
			if (x == null)
				return false;
			if (this.getClass() != x.getClass())
				return false;
			Transaction that = (Transaction) x;
			if (!who.equals(that.who()))
				return false;
			if (!when.equals(that.when()))
				return false;
			if (amount != that.amount)
				return false;
			return true;
		}
	}
}
