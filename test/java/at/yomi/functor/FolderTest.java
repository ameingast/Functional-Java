package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import at.yomi.functor.Folder;

public class FolderTest extends BaseTest {
	@Test
	public void testAddNumbers() {
		final Integer k = new Folder<Integer,Integer>() {
			@Override
			public Integer fold(Integer a, Integer e) {
				return a + e;
			}
		}.apply(as, 0);
		Integer r = 0;
		for (final Integer i : as)
			r += i;
		assertEquals(r, k);
	}

	@Test
	public void testStringConcat() {
		final String sf = new Folder<String,String>() {
			@Override
			public String fold(String a, String e) {
				return e + a;
			}
		}.apply(Arrays.asList("PRE", "-", "FIX", "-", "POST", "-", "FIX"), "");
		assertEquals("PRE-FIX-POST-FIX", sf);
	}
}