package at.yomi.util.pair;

import static org.junit.Assert.*;

import org.junit.Test;

import at.yomi.util.pair.Pair;

public class PairTest {
	private Pair<String,Integer> pair = new Pair<String,Integer>("foo", 123);

	@Test
	public void testPair() {
		assertEquals("foo", pair.first);
		assertEquals(123, pair.second);
	}

}
