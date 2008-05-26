package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FoldTest extends BaseTest {
	protected Fold<Integer,Integer> getSumFold() {
		return new Fold<Integer,Integer>(Utils.sumFunctor);
	}

	protected Fold<String,String> getStringReverseFold() {
		return new Fold<String,String>(Utils.reverseStringFunctor);
	}

	@Test
	public void testAddNumbers() {
		final Integer k = getSumFold().apply(data, 0);
		Integer r = 0;

		for (final Integer i : data)
			r += i;
		assertEquals(r, k);
	}

	@Test
	public void testStringReverse() {
		String buf = "";

		for (final String s : stringData)
			buf += s;
		assertEquals(Utils.reverse(buf), getStringReverseFold().apply(stringData, ""));
	}
}