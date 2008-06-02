package at.yomi.mp;

import java.util.List;

import at.yomi.functor.Utils;

public class BaseTest {
	public static final Integer WORKER_COUNT = 10;

	public static final Integer SIZE = 10000;

	public final List<Integer> data = Utils.getData(false, SIZE);

	public final List<Integer> smallData = Utils.getData(true, SIZE);
}
