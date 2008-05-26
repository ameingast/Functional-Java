package at.yomi.functor;

import java.util.ArrayList;
import java.util.Collection;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.functor.parallel.ParallelFilter;

public class FilterBenchmark extends BaseBenchmark {
	@BM
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
				new ParallelFilter<Integer>(Utils.isEvenFunctor).apply(data);
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
