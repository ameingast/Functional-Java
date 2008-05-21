package at.yomi.util.functional.functor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import at.yomi.util.functional.BaseTest;

public class FolderTest extends BaseTest {
	@Test
	public void testAddNumbers() {
		Integer k = new Folder<Integer,Integer>() {
			public Integer fold(Integer a, Integer e) {
				return a + e;
			}
		}.apply(as, 0);
		Integer r = 0;
		for (Integer i : as)
			r += i;
		assertEquals(r, k);
	}

	@Test
	public void testStringConcat() {
		String sf = new Folder<String,String>() {
			public String fold(String a, String e) {
				return e + a;
			}
		}.apply(Arrays.asList("PRE", "-", "FIX", "-", "POST", "-", "FIX"), "");
		assertEquals("PRE-FIX-POST-FIX", sf);
	}
}
