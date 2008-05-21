package benchmark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import functional.Filter;
import functional.Folder;
import functional.MapFolder;
import functional.Mapper;

public class Perf {
	private static final Integer SIZE = 100000;

	private static List<Integer> data = getData(SIZE);

	public static void main(String[] args) {
		//bmMapper();
		//bmFolder();
		//bmFilter();
		bmMapFolder();
	}

	private static void bmMapFolder() {
		new Benchmark() {
			public void benchmark() {
				new MapFolder<Integer, Boolean, Integer>() {
					public Integer ffold(Boolean b, Integer e) {
						return b ? e + 1 : e;
					}

					public Boolean fmap(Integer a) {
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
					public Integer ffold(Boolean b, Integer e) {
						return b ? e + 1 : e;
					}

					public Boolean fmap(Integer a) {
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
				new Folder<Integer, Integer>() {
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
		final List<Integer> r1 = new ArrayList<Integer>(SIZE);
		final List<Integer> r2 = new ArrayList<Integer>(SIZE);
		
		new Benchmark() {
			public void benchmark() {
				r1.addAll(new Mapper<Integer, Integer>() {
					public Integer map(Integer a) {
						return a + 1;
					}
				}.apply(data));
			}
		}.execute("Mapping (+1)");

		new Benchmark() {
			public void benchmark() {
				List<Integer> c = new ArrayList<Integer>(SIZE);
				
				for (Integer i : data)
					c.add(i + 1);
				r2.addAll(c);
			}
		}.execute("Iterating (+1)");
		
		for (int i = 0; i < SIZE; i++)
			if (!r1.get(i).equals(r2.get(i)))
				System.err.println("err: " + r2.get(i) + " != " + r1.get(i));
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
