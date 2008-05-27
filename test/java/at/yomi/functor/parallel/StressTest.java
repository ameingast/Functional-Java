package at.yomi.functor.parallel;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import org.junit.Test;

import at.yomi.functor.Utils;
import at.yomi.functor.f.SideEffect1;

public class StressTest {
	@Test
	public void testStress() {
		final List<Boolean> actual = new ArrayList<Boolean>();

		for (final Integer i : Functors.data)
			actual.add(Utils.isPrime(i));

		new AbstractStressTest<List<Boolean>>() {
			@Override
			public List<Boolean> test() {
				return Functors.pPrimeMap.apply(Functors.data);
			}
		}.run(new SideEffect1<List<Boolean>>() {
			@Override
			public void apply(final List<Boolean> s) {
				assertArrayEquals(actual.toArray(), s.toArray());
			}
		});
	}

	public abstract class AbstractStressTest<A> {
		private final Integer ITERATIONS = 3;
		private final Integer INSTANTIATIONS = 30;

		private final Semaphore sem = new Semaphore(-1 * (INSTANTIATIONS * ITERATIONS) + 1);

		public abstract A test();

		public void run(final SideEffect1<A> sideEffect) {
			final List<A> results = new Vector<A>();

			for (Integer i = 0; i < ITERATIONS; i++)
				for (Integer j = 0; j < INSTANTIATIONS; j++)
					new Thread() {
						public void run() {
							results.add(test());
							sem.release();
						}
					}.start();
			try {
				sem.acquire();
			} catch (final InterruptedException e) {}
			for (final A a : results)
				sideEffect.apply(a);
		}
	}
}
