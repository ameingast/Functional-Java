package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functor.f.F;

public class FunctorTest extends BaseTest {

	@Test
	public void testAddConst() {
		final Integer c = 2;
		final Integer k = new F<Integer,Integer>() {
			@Override
			public Integer apply(final Integer a) {
				return a + c;
			}
		}.apply(2);
		assertEquals(k, 4);
	}

	@Test
	public void testConsString() {
		final String prefix = "PRE";
		final String result = new F<String,String>() {
			@Override
			public String apply(String a) {
				return prefix + a;
			}
		}.apply("POST");
		assertEquals("PREPOST", result);
	}
}