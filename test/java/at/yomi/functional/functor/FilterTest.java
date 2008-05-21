package at.yomi.functional.functor;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import at.yomi.functional.BaseTest;
import at.yomi.functional.functor.Filter;

public class FilterTest extends BaseTest {
	@Test
	public void testFilterEven() {
		Collection<Integer> cs = new Filter<Integer>() {
			public boolean filter(Integer a) {
				return 0 == a % 2;
			}
		}.apply(as);
		for (Integer i : cs)
			assertTrue(0 == i % 2);
	}
}
