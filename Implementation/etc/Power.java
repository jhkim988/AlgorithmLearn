public class Power {
	// recursive:
	static long pow_recur(long base, long exp) {
		if (exp == 0)
			return 1;
		if (exp == 1)
			return base;
		long half = pow_recur(base, exp / 2);
		if (exp % 2 == 0)
			return half * half;
		return half * half * base;
	}

	// non-recursive
	static long pow(long base, long exp) {
		long ret = 1;
		long pow = base;
		while (exp > 0) {
			if ((exp & 1) == 1)
				ret *= pow;
			pow *= pow;
			exp >>= 1;
		}
		return ret;
	}
}
