package at.yomi.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BaseTest {
	private static final Integer SIZE = 1000;

	protected static final Integer WORK_COUNT = 10;

	protected List<Integer> as = initTestData();

	@SuppressWarnings("unchecked")
	protected List<List<Integer>> aas = new ArrayList<List<Integer>>(Arrays.asList(as, as, as));

	private List<Integer> initTestData() {
		List<Integer> is = new ArrayList<Integer>(SIZE);
		Random r = new Random();
		for (Integer i = 0; i < SIZE; i++)
			is.add(r.nextInt());
		return is;
	}
}
