package at.yomi.mp;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.functor.MapFold;
import at.yomi.functor.Utils;
import at.yomi.functor.parallel.ParallelMapFold;
import at.yomi.mp.prime.PrimeAggregator;

public class MPBenchmark extends BaseBenchmark {
	public static void bmSumPrimes() {
		new AbstractBenchmark("[MP] PrimeReceiver (isPrime) (sum)") {
			@Override
			public void benchmark() throws InterruptedException {
				final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, data);

				pa.startCalculating();
				pa.getPrimeSum();
			}
		};

		new AbstractBenchmark("[MP] PrimeListReceiver (isPrime) (sum)") {
			@Override
			public void benchmark() throws InterruptedException {
				final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, data);

				pa.startCalculating();
				pa.getPrimeSum();
			}
		};

		new AbstractBenchmark("MapFold (isPrime) (sum)") {
			@Override
			public void benchmark() throws Exception {
				new MapFold<Integer,Integer,Integer>(Utils.intPrimeFunctor, Utils.sumFunctor)
						.apply(data, 0);
			}
		};

		new AbstractBenchmark("[P] MapFold (isPrime) (sum)") {
			@Override
			public void benchmark() throws Exception {
				new ParallelMapFold<Integer,Integer,Integer>(Utils.intPrimeFunctor,
						Utils.sumFunctor).apply(data, 0);
			}
		};

		new AbstractBenchmark("Iterating (isPrime) (sum)") {
			@Override
			public void benchmark() throws Exception {
				Integer sum = 0;

				for (final Integer i : data)
					sum += Utils.isPrime(i) ? i : 0;
			}
		};
	}
}
