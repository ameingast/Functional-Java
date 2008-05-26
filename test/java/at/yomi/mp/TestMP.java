package at.yomi.mp;

import org.junit.Test;

public class TestMP extends BaseTest {
	// @Test
	// public void testPrimes() throws InterruptedException {
	// Integer primeSum = 0;
	// final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, data);
	//
	// for (final Integer i : data)
	// primeSum += Utils.isPrime(i) ? i : 0;
	//
	// pa.startCalculating();
	// pa.waitForShutDown();
	//
	// assertEquals(primeSum, pa.getPrimeSum());
	// }

	@Test
	public void testAddNumbers() throws InterruptedException {
		final LongAdder r = new LongAdder();

		for (final Integer i : data)
			r.addNumber(new Long(i));
		r.shutDown();
		r.waitForShutDown();

		// assertEquals(new Fold<Integer,Long>(sumFunctor) {}.apply(data, new Long(0)), r.count);
	}
}
