package at.yomi.mp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.benchmark.Utils;
import at.yomi.functional.functor.Folder;
import at.yomi.mp.prime.PrimeAggregator;

public class TestMP extends BaseTest {
	@Test
	public void testPrimes() throws InterruptedException {
		Integer primeSum = 0;
		final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, data);

		for (final Integer i : data)
			primeSum += Utils.isPrime(i) ? i : 0;

		pa.startCalculating();
		pa.waitForShutDown();

		assertEquals(primeSum, pa.getPrimeSum());
	}

	@Test
	public void testAddNumbers() throws InterruptedException {
		final AdderReceiver<Integer> r = new AdderReceiver<Integer>();

		for (final Integer i : data)
			r.new AddMessage(null, i).send(r);

		r.shutDown();
		r.waitForShutDown();
		assertEquals(new Folder<Integer,Long>() {
			public Long fold(final Integer a, final Long e) {
				return new Long(a + e);
			}
		}.apply(data, new Long(0)), r.count);
	}
}
