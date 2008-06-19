package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.functor.parallel.ParallelMap;

public class MapBenchmark extends BaseBenchmark {
	public static void bmIsPrime() {
		new AbstractBenchmark("Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new Map<Integer,Boolean>(Utils.isPrimeFunctor).apply(data);
			}
		};

		new AbstractBenchmark("[P] Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new ParallelMap<Integer,Boolean>(Utils.isPrimeFunctor).apply(data);
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
