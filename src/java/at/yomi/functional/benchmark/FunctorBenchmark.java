package at.yomi.functional.benchmark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import at.yomi.benchmark.Benchmark;
import at.yomi.functional.functor.Filter;
import at.yomi.functional.functor.Folder;
import at.yomi.functional.functor.MapFolder;
import at.yomi.functional.functor.Mapper;
import at.yomi.functional.functor.parallel.ParallelMapper;

public class FunctorBenchmark {
	private static final Integer SIZE = 100000;

	private static List<Integer> data = getData(SIZE);

	public static void main(String[] args) {
		bmMapper();
		// bmFolder();
		// bmFilter();
		// bmMapFolder();
	}

	private static void bmMapFolder() {
		new Benchmark() {
			public void benchmark() {
				new MapFolder<Integer,Boolean,Integer>() {
					public Integer fold(Boolean b, Integer e) {
						return b ? e + 1 : e;
					}

					public Boolean map(Integer a) {
						return 0 == a % 2;
					}
				}.apply(data, 0);
			}
		}.execute("MapFold (even) (count)");

		new Benchmark() {
			public void benchmark() {
				Integer evens = 0;

				for (Integer i : data)
					if (0 == i % 2)
						evens++;
			}
		}.execute("Iterating count even");

		new Benchmark() {
			public void benchmark() {
				new MapFolder<Integer,Boolean,Integer>() {
					public Integer fold(Boolean b, Integer e) {
						return b ? e + 1 : e;
					}

					public Boolean map(Integer a) {
						return isPrime(a);
					}
				}.apply(data, 0);
			}
		}.execute("MapFold (isPrime) (count)");

		new Benchmark() {
			public void benchmark() {
				Integer k = 0;

				for (Integer i : data)
					if (isPrime(i))
						k++;
			}
		}.execute("Iterating count primes");
	}

	private static void bmFilter() {
		new Benchmark() {
			public void benchmark() {
				new Filter<Integer>() {
					public boolean filter(Integer a) {
						return 0 == a % 2;
					}
				}.apply(data);

			}
		}.execute("Filter even");

		new Benchmark() {
			public void benchmark() {
				Collection<Integer> c = new ArrayList<Integer>();

				for (Integer i : data)
					if (0 == i % 2)
						c.add(i);
			}

		}.execute("Iterate filter even");
	}

	private static void bmFolder() {
		new Benchmark() {
			public void benchmark() {
				new Folder<Integer,Integer>() {
					public Integer fold(Integer a, Integer e) {
						return a * e;
					}
				}.apply(data, 1);
			}
		}.execute("Folding (*)");

		new Benchmark() {
			public void benchmark() {
				Integer r = 1;

				for (Integer i : data)
					r *= i;

			}
		}.execute("Iterating (*)");
	}

	private static void bmMapper() {
		/*
		 * new Benchmark() { public void benchmark() { new Mapper<Integer, Integer>() { public
		 * Integer map(Integer a) { return a + 1; } }.apply(data); } }.execute("Mapping (+1)");
		 * 
		 * new Benchmark() { public void benchmark() { new ParallelMapper<Integer, Integer>(10) {
		 * public Integer map(Integer a) { return a + 1; } }.apply(data); }
		 * }.execute("(P-10)-Mapping (+1)");
		 * 
		 * new Benchmark() { public void benchmark() { List<Integer> c = new ArrayList<Integer>(SIZE);
		 * 
		 * for (Integer i : data) c.add(i + 1); } }.execute("Iterating (+1)");
		 */

		new Benchmark() {
			public void benchmark() {
				new Mapper<Integer,Boolean>() {
					public Boolean map(Integer a) {
						return isPrime(a);
					}
				}.apply(data);
			}
		}.execute("Mapping (isPrime)");

		new Benchmark() {
			public void benchmark() {
				new ParallelMapper<Integer,Integer>(5) {
					public Integer map(Integer a) {
						return isPrime(a) ? a : 0;
					}
				}.apply(data);
			}
		}.execute("[5] Mapping (isPrime)");

		new Benchmark() {
			public void benchmark() {
				List<Integer> primes = new ArrayList<Integer>();

				for (Integer i : data)
					primes.add(isPrime(i) ? i : 0);
			}
		}.execute("Iterating (isPrime)");
	}

	private static List<Integer> getData(Integer size) {
		List<Integer> c = new ArrayList<Integer>(size);
		Random r = new Random();

		for (int i = 0; i < size; i++)
			c.add(r.nextInt());
		return c;
	}

	private static Boolean isPrime(Integer a) {
		for (int i = 2; i < Math.sqrt(a); i++)
			if (a % i == 0)
				return false;
		return true;
	}
}
