package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.Utils;
import at.yomi.benchmark.annotations.BM;
import at.yomi.functor.parallel.ParallelMap;

public class MapBenchmark extends BaseBenchmark {
	@BM
	public static void bmIsPrime() {
		new AbstractBenchmark("Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new Map<Integer,Boolean>() {
					@Override
					public Boolean map(final Integer a) {
						return Utils.isPrime(a);
					}
				}.apply(data);
			}
		};

		new AbstractBenchmark("[5] Mapping (isPrime)") {
			@Override
			public void benchmark() {
				new ParallelMap<Integer,Integer>(5) {
					@Override
					public Integer map(final Integer a) {
						return Utils.isPrime(a) ? a : 0;
					}
				}.apply(data);
			}
		};

		new AbstractBenchmark("Iterating (isPrime)") {
			@Override
			public void benchmark() {
				final List<Integer> primes = new ArrayList<Integer>();

				for (final Integer i : data)
					primes.add(Utils.isPrime(i) ? i : 0);
			}
		};
	}
}
