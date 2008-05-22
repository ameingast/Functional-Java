package at.yomi.functional.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functional.BaseTest;

public class MapFolderTest extends BaseTest {
	protected MapFolder<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new MapFolder<Integer,Integer,String>() {
			@Override
			public String fold(final Integer b, final String e) {
				return e + b.toString();
			}

			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}

	protected MapFolder<Integer,Integer,Integer> getAddOneSumFolder() {
		return new MapFolder<Integer,Integer,Integer>() {
			@Override
			public Integer fold(final Integer b, final Integer e) {
				return e + b;
			}

			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Test
	public void testAddOneAndSum() {
		final Integer fs = getAddOneSumFolder().apply(as, 0);

		Integer result = 0;
		for (final Integer i : as)
			result += i + 1;
		assertEquals(result, fs);
	}

	@Test
	public void testMulTenConcatAsString() {
		final String fs = getMulTenConcatAsStringFolder().apply(as, "");

		String result = "";
		for (final Integer i : as)
			result += new Integer(10 * i).toString();
		assertEquals(result, fs);
	}
}