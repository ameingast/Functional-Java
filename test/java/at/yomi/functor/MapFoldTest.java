package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MapFoldTest extends BaseTest {
	protected MapFold<Integer,Integer,String> getMulTenConcatAsStringFolder() {
		return new MapFold<Integer,Integer,String>(Utils.mulTenFunctor,
				Utils.toIntegerConcatFunctor);
	}

	protected MapFold<Integer,Integer,Integer> getAddOneSumFolder() {
		return new MapFold<Integer,Integer,Integer>(Utils.addOneFunctor, Utils.sumFunctor);
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