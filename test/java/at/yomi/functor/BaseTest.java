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

	protected final List<Integer> data = getData(false);

	protected final List<Integer> smallData = getData(true);

	protected final List<String> stringData = getStringData();

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

	private List<String> getStringData() {
		final List<String> data = new ArrayList<String>(1000);
		final Random r = new Random();

		for (Integer i = 0; i < 1000; i++)
			data.add(new Integer(r.nextInt()).toString());

		return data;
	}
}