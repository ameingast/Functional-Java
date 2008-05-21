package at.yomi.benchmark;

public abstract class Benchmark {

	public static Integer ROUNDS = 3;

	public abstract void benchmark();

	public void execute(String title) {
		System.out.print("Starting: " + title + " ");
		long start = System.currentTimeMillis();
		for (int i = 0; i < ROUNDS; i++) {
			benchmark();
			System.out.print(".");
		}
		long end = System.currentTimeMillis();
		System.out.println(".\t| Execution took:\t" + timeTaken(start, end) + " ms");
	}

	private long timeTaken(long start, long end) {
		return ((end - start) / ROUNDS);
	}
}