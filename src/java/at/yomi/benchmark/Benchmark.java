package at.yomi.benchmark;

public abstract class Benchmark {

	public static Integer ROUNDS = 3;

	public abstract void benchmark();

	public void execute(final String title) {
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