
public class Exercise01_01_25 {
	// 1.1.25
	// ��Ŭ���� �˰����� ���� �ƴ� ������ ���� �� p, q�� ���ؼ� �ִ������� ���س��� �����϶�
	// a = x * b + r0		= x * rn
	// b = x * r0 + r1 		= x * rn
	// r0 = x * r1 + r2 
	// r1 = x * r2 + r3
	// r2 = x * r3 + r4
	// ...
	// rn-2 = x * rn-1 + rn		= x * rn
	// rn-1 = x * rn 
	
	// 1. a|rn, b|rn, rn <= gcd
	// 2. For each c such that a | c and b | c, a = mc, b = nc => r0 = a - xb  => r0 | c
	// r1 = b - x * r0 => r1 | c
	// r2 = r0 - x *r1
	// Suppose ri | c. (i<=k) Then rk+1 = rk-1 - x*rk, rk+1 | c
	// rn | c
	// Since c is any number satisfying condition... rn | gcd, gcd <= rn
}
