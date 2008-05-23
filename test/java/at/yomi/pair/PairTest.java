package at.yomi.pair;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class PairTest {
	private final Pair<String,Integer> pair = new Pair<String,Integer>("foo", 123);

	@Test
	public void testPair() {
		assertEquals("foo", pair.first);
		assertEquals(123, pair.second);
	}

	@Test
	public void testComparator() {
		final Pair<Integer,String> p1 = new Pair<Integer,String>(0, "b");
		final Pair<Integer,String> p2 = new Pair<Integer,String>(1, "a");
		final List<Pair<Integer,String>> pairs = new ArrayList<Pair<Integer,String>>();

		if (pairs.add(p2) && pairs.add(p1)) {
			Collections.sort(pairs, new PairFirstElementComparator<Integer,String>());
			assertEquals(p1, pairs.get(0));
			assertEquals(p2, pairs.get(1));

			Collections.sort(pairs, new PairSecondElementComparator<Integer,String>());
			assertEquals(p2, pairs.get(0));
			assertEquals(p1, pairs.get(1));
		}
	}
}
