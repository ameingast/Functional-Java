package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;

import at.yomi.functor.parallel.PFService;

public class BaseTest {
	private static final Integer SIZE = 10000;

	protected static final Integer WORKER_COUNT = 10;

	protected static final Integer COMMIT_INTERVAL = 100;

	protected List<Integer> data = getData(false);

	protected List<Integer> smallData = getData(true);

	@Before
	public void setUp() {
		PFService.startUp();
	}

	@After
	public void shutDown() {
		PFService.shutDown();
	}

	private List<Integer> getData(final boolean truncate) {
		final List<Integer> is = new ArrayList<Integer>(SIZE);
		final Random r = new Random();

		for (Integer i = 0; i < SIZE; i++)
			is.add(truncate ? r.nextInt() % 1000 : r.nextInt());
		return is;
	}
}