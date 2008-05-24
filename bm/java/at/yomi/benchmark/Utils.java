package at.yomi.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
	public static Boolean isPrime(final Integer a) {
		for (int i = 2; i < Math.sqrt(a); i++)
			if (a % i == 0)
				return new Boolean(false);
		return new Boolean(true);
	}

	public static List<Integer> getData(final Integer size) {
		final List<Integer> c = new ArrayList<Integer>(size);
		final Random r = new Random();

		for (Integer i = 0; i < size; i++)
			c.add(r.nextInt());
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
