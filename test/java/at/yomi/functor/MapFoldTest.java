package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.pair.Pair;

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

	@Test
	public void testPalindromes() {
		final List<String> result = new ArrayList<String>();

		for (final String s : stringData)
			if (Utils.reverse(s).equals(s))
				result.add(s);

		final List<String> mrx = new MapFold<String,Pair<String,String>,List<String>>(
				Utils.reverseStringToPairFunctor, Utils.collectPalindromes).apply(stringData,
				new ArrayList<String>());
		assertArrayEquals(result.toArray(), mrx.toArray());
	}
}