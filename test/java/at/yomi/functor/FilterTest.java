package at.yomi.functor;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import at.yomi.functor.Filter;

public class FilterTest extends BaseTest {
	@Test
	public void testFilterEven() {
		final Collection<Integer> cs = new Filter<Integer>() {
			@Override
			public boolean filter(Integer a) {
				return 0 == a % 2;
			}
		}.apply(as);
		for (final Integer i : cs)
			assertTrue(0 == i % 2);
	}
}