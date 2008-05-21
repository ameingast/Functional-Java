package at.yomi.functional.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functional.BaseTest;
import at.yomi.functional.functor.Functor;

public class FunctorTest extends BaseTest {

	@Test
	public void testAddConst() {
		final Integer c = 2;
		Integer k = new Functor<Integer,Integer,Integer>() {
			public Integer apply(final Integer a, final Integer... is) {
				return a + c;
			}
		}.apply(2);
		assertEquals(k, 4);
	}

	@Test
	public void testConsString() {
		final String prefix = "PRE";
		String result = new Functor<String,String,String>() {
			public String apply(String a, String... cs) {
				String r = prefix + a;
				for (String s : cs)
					r += s;
				return r;
			}
		}.apply("-", "FIX", "-", "POST", "-", "FIX");
		assertEquals("PRE-FIX-POST-FIX", result);
	}
}
