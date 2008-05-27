package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.functor.parallel.ParallelMap;

public class MapBenchmark extends BaseBenchmark {
	@BM
	public static void bmIsPrime() {
		new AbstractBenchmark("Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new Map<Integer,Boolean>(Utils.isPrimeFunctor).apply(data);
			}
		};

		new AbstractBenchmark("[5] Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new ParallelMap<Integer,Boolean>(Utils.isPrimeFunctor, WORKER_COUNT,
						COMMIT_INTERVAL).apply(data);
			}
		};

		new AbstractBenchmark("Iterating (isPrime)") {
			@Override
			public void benchmark() {
				for (final Integer i : data)
					Utils.isPrime(i);
			}
		};
	}
}
