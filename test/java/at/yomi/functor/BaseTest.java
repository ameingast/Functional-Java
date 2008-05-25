package at.yomi.functor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;

import at.yomi.functor.parallel.PFService;

public class BaseTest {
	private static final Integer SIZE = 10000;

	protected static final Integer WORK_COUNT = 10;

	protected static final Integer COMMIT_INTERVAL = 100;

	protected List<Integer> as = initTestData();

	@Before
	public void setUp() {
		PFService.startUp();
	}

	@After
	public void shutDown() {
		PFService.shutDown();
	}

	@SuppressWarnings("unchecked")
	protected List<List<Integer>> aas = new ArrayList<List<Integer>>(Arrays.asList(as, as, as));

	private List<Integer> initTestData() {
		final List<Integer> is = new ArrayList<Integer>(SIZE);
		final Random r = new Random();
		for (Integer i = 0; i < SIZE; i++)
			is.add(r.nextInt());
		return is;
	}
}