package at.yomi.functor;

import java.util.ArrayList;
import java.util.Collection;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.functor.parallel.ParallelFilter;

public class FilterBenchmark extends BaseBenchmark {
	public static void bmFilterEven() {
		new AbstractBenchmark("Filter even") {
			@Override
			public void benchmark() {
				new Filter<Integer>(Utils.isEvenFunctor).apply(data);
			}
		};

		new AbstractBenchmark("[5] Filter even") {
			@Override
			public void benchmark() {
				new ParallelFilter<Integer>(Utils.isEvenFunctor, WORKER_COUNT, COMMIT_INTERVAL)
						.apply(data);
			}
		};

		new AbstractBenchmark("Iterate filter even") {
			@Override
			public void benchmark() {
				final Collection<Integer> c = new ArrayList<Integer>();

				for (final Integer i : data)
					if (0 == i % 2)
						c.add(i);
			}
		};
	}
}
