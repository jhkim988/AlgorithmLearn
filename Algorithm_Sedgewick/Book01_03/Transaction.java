
public class Transaction {
	private String who;
	private Date when;
	private double amount;
	
	Transaction(String who, Date when, double amount) {
		this.who = who;
		this.when = when;
		this.amount = amount;
	}
	
	Transaction(String trnsctn) {
		String[] strSplit = trnsctn.split("/"); // who/date/amount
		who = strSplit[0];
		when = new Date(strSplit[1]);
		amount = Double.parseDouble(strSplit[2]);
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
}
