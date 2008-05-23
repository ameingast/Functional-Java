package at.yomi.functional.functor;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	protected static Boolean isPrime(final Integer a) {
		for (int i = 2; i < Math.sqrt(a); i++)
			if (a % i == 0)
				return new Boolean(false);
		return new Boolean(true);
	}

	protected static List<Integer> getData(final Integer size) {
		final List<Integer> c = new ArrayList<Integer>(size);

		for (Integer i = 1; i < 100000; i++)
			c.add(i);
		return c;
	}

	public static Float tpoly(final Integer k) {
		float mu = (float) 10.0;
		float x, s;
		float pu = (float) 0.0;
		int i;
		int j, n;
		final float pol[] = new float[100];

		n = 50;
		x = (float) 0.2;
		for (i = 0; i < n; i++) {
			for (j = 0; j < 100; j++) {
				mu = (mu + (float) 2.0) / (float) 2.0;
				pol[j] = mu;
			}
			s = (float) 0.0;
			for (j = 0; j < 100; j++) {
				s = x * s + pol[j];
			}
			pu += s;
		}
		return new Float(pu);
	}
}
