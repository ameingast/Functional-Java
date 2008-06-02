package at.yomi.mp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functor.Utils;
import at.yomi.mp.arith.ArithReceiver;
import at.yomi.mp.prime.PrimeAggregator;

public class TestMP extends BaseTest {
	@Test
	public void testPrimes() throws InterruptedException {
		Integer primeSum = 0;
		final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, smallData);

		for (final Integer i : smallData)
			primeSum += Utils.isPrime(i) ? i : 0;

		pa.startCalculating();
		assertEquals(primeSum, pa.getPrimeSum());
	}

	@Test
	public void testAddNumbers() throws InterruptedException {
		final ArithReceiver r = new ArithReceiver();

		for (final Integer i : smallData)
			r.addNumber(i);
		assertEquals(Utils.sumFold.apply(smallData, 0), r.getResult());
	}
}
