package at.yomi.benchmark;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBenchmark {

	private static List<AbstractBenchmark> benchmarks = new ArrayList<AbstractBenchmark>();

	private final String title;

	public static Integer ROUNDS = 3;

	public abstract void benchmark();

	public AbstractBenchmark(final String title) {
		this.title = title;
		benchmarks.add(this);
	}

	public static void run() {
		for (final AbstractBenchmark benchmark : benchmarks)
			benchmark.execute();
	}

	public void execute() {
		System.out.print("Starting: " + title + " ");
		final long start = System.currentTimeMillis();
		for (int i = 0; i < ROUNDS; i++) {
			benchmark();
			System.out.print(".");
		}
		final long end = System.currentTimeMillis();
		System.out.println(".\t| Execution took:\t" + timeTaken(start, end) + " ms");
	}

	private long timeTaken(final long start, final long end) {
		return ((end - start) / ROUNDS);
	}
}