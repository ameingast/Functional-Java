package at.yomi.mp;

import java.util.List;

import at.yomi.benchmark.Utils;

public class BaseTest {
	protected static final Integer WORKER_COUNT = 10;

	protected static final Integer ITEMS = 10000;

	protected List<Integer> data = Utils.getData(ITEMS);
}
