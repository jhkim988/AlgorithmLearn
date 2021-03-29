// Q2. Hashing with wrong hashCode() or equals().
// Suppose that you implement a data type OlympicAthlete for use in a java.util.HashMap.
// Describe what happens if you override hashCode() but not equals().
// Describe what happens if you override equals() but not hashCode().
// Describe what happens if you override hashCode()
// but implement public boolean equals(OlympicAthlete that) instead of public boolean equals(Object that).

// sol)
// Suppose Athlete class has name(key), id(value)
// 1. Override hashCode(), Not equals()
// - Return value of equals() method of Object class is distinct each instance.
// - If two objects have same name, then those have same hashCode.
// - But Those are not same by equals() method.
// - Therefore collision occurs when same name athlete are inputed
// - and those don't replace, but hashing resolve collision.(duplicate)
// 2. Override equals(), Not hashCode()
// - Return value of hashCode() method of Object class is distinct each instance.
// - Although two objects have same name, those hashcode are not same.
// - Therefore Store in distinct position of hash table, not replace latter from former.
// 3. Override hashCode(), boolean equals(OlympicAthlete that)
// - Two null objects are not same by equals() method, even if hashmap allow null key and null value.

public class Interview10_02_HashingWithWrong {
	public static void main(String[] args) {
		
	}
}
