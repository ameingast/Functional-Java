package at.yomi.functional.functor;

import java.util.ArrayList;
import java.util.Collection;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.benchmark.annotations.BMIgnore;

public class FilterBenchmark extends BaseBenchmark {
	@BMIgnore
	@BM
	public static void bmFilterEven() {
		new AbstractBenchmark("Filter even") {
			@Override
			public void benchmark() {
				new Filter<Integer>() {
					@Override
					public boolean filter(final Integer a) {
						return 0 == a % 2;
					}
				}.apply(data);
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
