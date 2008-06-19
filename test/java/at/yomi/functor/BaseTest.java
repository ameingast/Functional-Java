package at.yomi.functor;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import at.yomi.functor.parallel.PFService;
import at.yomi.functor.parallel.aggregator.AbstractWorker;

public class BaseTest {
	public static final Integer SIZE = 1000;

	public static final Integer WORKER_COUNT = AbstractWorker.DEFAULT_WORKER_COUNT;

	public static final List<Integer> data = Utils.getData(false, SIZE);

	public static final List<Integer> smallData = Utils.getData(true, SIZE);

	public static final List<String> stringData = Utils.getStringData(1000);

	@Before
	public void setUp() {
		PFService.startUp();
	}

	@After
	public void shutDown() {
		PFService.shutDown();
	}
}