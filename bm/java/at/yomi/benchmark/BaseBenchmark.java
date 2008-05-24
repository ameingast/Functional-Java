package at.yomi.benchmark;

import java.util.List;

public abstract class BaseBenchmark {
	protected static final Integer SIZE = 100000;

	protected static final Integer WORKER_COUNT = 10;

	protected static List<Integer> data = Utils.getData(SIZE);
}
