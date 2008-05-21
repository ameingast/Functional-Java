package at.yomi.util.functional.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.util.functional.BaseTest;

public class MapFolderTest extends BaseTest {
	@Test
	public void testAddOneAndSum() {
		Integer fs = new MapFolder<Integer,Integer,Integer>() {
			public Integer fold(Integer b, Integer e) {
				return e + b;
			}

			public Integer map(Integer a) {
				return a + 1;
			}
		}.apply(as, 0);

		Integer result = 0;
		for (Integer i : as)
			result += i + 1;
		assertEquals(fs, result);
	}
}
