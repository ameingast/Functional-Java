package at.yomi.functional.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functional.BaseTest;

public class MapFolderTest extends BaseTest {
	protected MapFolder<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new MapFolder<Integer,Integer,String>() {
			public String fold(Integer b, String e) {
				return e + b.toString();
			}

			public Integer map(Integer a) {
				return 10 * a;
			}
		};
	}

	protected MapFolder<Integer,Integer,Integer> getAddOneSumFolder() {
		return new MapFolder<Integer,Integer,Integer>() {
			public Integer fold(Integer b, Integer e) {
				return e + b;
			}

			public Integer map(Integer a) {
				return a + 1;
			}
		};
	}

	@Test
	public void testAddOneAndSum() {
		Integer fs = getAddOneSumFolder().apply(as, 0);

		Integer result = 0;
		for (Integer i : as)
			result += i + 1;
		assertEquals(result, fs);
	}

	@Test
	public void testMulTenConcatAsString() {
		String fs = getMulTenConcatAsStringFolder().apply(as, "");

		String result = "";
		for (Integer i : as)
			result += new Integer(10 * i).toString();
		assertEquals(result, fs);
	}
}
