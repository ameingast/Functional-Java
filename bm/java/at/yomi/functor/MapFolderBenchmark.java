package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.benchmark.Utils;
import at.yomi.benchmark.annotations.BM;
import at.yomi.functor.parallel.ParallelMapFolder;
import at.yomi.functor.parallel.StrictParallelMapFolder;

public class MapFolderBenchmark extends BaseBenchmark {
	@BM
	public static void bmCountEven() {
		new AbstractBenchmark("MapFold (even) (count)") {
			@Override
			public void benchmark() {
				new MapFolder<Integer,Boolean,Integer>() {
					@Override
					public Integer fold(final Boolean b, final Integer e) {
						return b ? e + 1 : e;
					}

					@Override
					public Boolean map(final Integer a) {
						return 0 == a % 2;
					}
				}.apply(data, 0);
			}
		};

		new AbstractBenchmark("Iterating count even") {
			@Override
			public void benchmark() {
				Integer evens = 0;

				for (final Integer i : data)
					if (0 == i % 2)
						evens++;
			}
		};
	}

	@BM
	public static void bmCountPrimes() {
		new AbstractBenchmark("MapFold (TPoly) (count)") {
			@Override
			public void benchmark() {
				new MapFolder<Integer,Float,Float>() {
					@Override
					public Float fold(final Float b, final Float e) {
						return b + e;
					}

					@Override
					public Float map(final Integer a) {
						return Utils.tpoly(a);
					}
				}.apply(data, new Float(0));
			}
		};

		new AbstractBenchmark("[5] MapFold (TPoly) (sum)") {
			@Override
			public void benchmark() {
				new ParallelMapFolder<Integer,Float,Float>(5) {
					@Override
					public Float fold(final Float b, final Float e) {
						return e + b;
					}

					@Override
					public Float map(final Integer a) {
						return Utils.tpoly(a);
					}
				}.apply(data, new Float(0));
			}
		};

		new AbstractBenchmark("[5] StrictMapFold (TPoly) (sum)") {
			@Override
			public void benchmark() {
				new StrictParallelMapFolder<Integer,Float,Float>(5) {
					@Override
					public Float fold(final Float b, final Float e) {
						return e + b;
					}

					@Override
					public Float map(final Integer a) {
						return Utils.tpoly(a);
					}
				}.apply(data, new Float(0));
			}
		};

		new AbstractBenchmark("Iterating (Tpoly) (sum)") {
			@Override
			public void benchmark() {
				Float k = new Float(0);

				for (final Integer i : data)
					k += Utils.tpoly(i);
			}
		};
	}
}
