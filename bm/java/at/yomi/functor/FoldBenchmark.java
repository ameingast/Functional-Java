package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.functor.parallel.ParallelFold;

public class FoldBenchmark extends BaseBenchmark {
	@BM
	public static void bmProduct() {
		new AbstractBenchmark("Folding (*)") {
			@Override
			public void benchmark() {
				new Fold<Integer,Integer>(Utils.mulFunctor).apply(data, 1);
			}
		};

		new AbstractBenchmark("[5] Folding (*)") {
			@Override
			public void benchmark() throws Exception {
				new ParallelFold<Integer>(Utils.mulFunctor, WORKER_COUNT, COMMIT_INTERVAL).apply(
						data, 1);
			}
		};

		new AbstractBenchmark("Iterating (*)") {
			@Override
			public void benchmark() {
				Integer r = 1;

				for (final Integer i : data)
					r *= i;
			}
		};
	}
}
