package at.yomi.functor;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import at.yomi.functor.parallel.PFService;

public class BaseTest {
	private static final Integer SIZE = 10000;

	protected static final Integer WORKER_COUNT = 10;

	protected static final Integer COMMIT_INTERVAL = 100;

	protected final List<Integer> data = Utils.getData(false, SIZE);

	protected final List<Integer> smallData = Utils.getData(true, SIZE);

	protected final List<String> stringData = Utils.getStringData(1000);

	@Before
	public void setUp() {
		PFService.startUp();
	}

	@After
	public void shutDown() {
		PFService.shutDown();
	}
}