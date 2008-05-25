package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import at.yomi.functor.Fold;

public class FoldTest extends BaseTest {
	@Test
	public void testAddNumbers() {
		final Integer k = new Fold<Integer,Integer>() {
			@Override
			public Integer fold(Integer a, Integer e) {
				return a + e;
			}
		}.apply(data, 0);
		Integer r = 0;
		for (final Integer i : data)
			r += i;
		assertEquals(r, k);
	}

	@Test
	public void testStringConcat() {
		final String sf = new Fold<String,String>() {
			@Override
			public String fold(String a, String e) {
				return e + a;
			}
		}.apply(Arrays.asList("PRE", "-", "FIX", "-", "POST", "-", "FIX"), "");
		assertEquals("PRE-FIX-POST-FIX", sf);
	}
}