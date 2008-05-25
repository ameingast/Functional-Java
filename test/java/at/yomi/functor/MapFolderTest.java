package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functor.MapFolder;

public class MapFolderTest extends BaseTest {

	protected String concat(final Integer b, final String e) {
		return e + b.toString();
	}

	protected Integer mulTen(final Integer a) {
		return 10 * a;
	}

	protected Integer addOne(final Integer a) {
		return a + 1;
	}

	protected Integer sum(final Integer a, final Integer b) {
		return a + b;
	}

	protected MapFolder<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new MapFolder<Integer,Integer,String>() {
			@Override
			public String fold(final Integer b, final String e) {
				return concat(b, e);
			}

			@Override
			public Integer map(final Integer a) {
				return mulTen(a);
			}
		};
	}

	protected MapFolder<Integer,Integer,Integer> getAddOneSumFolder() {
		return new MapFolder<Integer,Integer,Integer>() {
			@Override
			public Integer fold(final Integer b, final Integer e) {
				return sum(e, b);
			}

			@Override
			public Integer map(final Integer a) {
				return addOne(a);
			}
		};
	}

	@Test
	public void testAddOneAndSum() {
		final Integer fs = getAddOneSumFolder().apply(data, 0);

		Integer result = 0;
		for (final Integer i : data)
			result += i + 1;
		assertEquals(result, fs);
	}

	@Test
	public void testMulTenConcatAsString() {
		final String fs = getMulTenConcatAsStringFolder().apply(data, "");

		String result = "";
		for (final Integer i : data)
			result += new Integer(10 * i).toString();
		assertEquals(result, fs);
	}
}