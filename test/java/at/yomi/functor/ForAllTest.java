package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ForAllTest extends BaseTest {
	protected ForAll<Integer> getAllEvens() {
		return new ForAll<Integer>(Utils.allEvensFunctor);
	}

	@Test
	public void testForAllEven() {
		Boolean result = true;

		for (final Integer i : data)
			result &= i % 2 == 0;
		assertEquals(result, getAllEvens().apply(data));
	}
}
