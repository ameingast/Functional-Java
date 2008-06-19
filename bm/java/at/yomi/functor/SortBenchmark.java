package at.yomi.functor;

import java.util.Collections;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.utils.Functors;

public abstract class SortBenchmark extends BaseBenchmark {
	public static void bmSort() {
		new AbstractBenchmark("Collections.sort(data)") {
			@Override
			public void benchmark() throws Exception {
				Collections.sort(data);
			}
		};

		new AbstractBenchmark("qSort Functor") {
			@Override
			public void benchmark() throws Exception {
				Functors.qSort.apply(data);
			}
		};
	}
}
