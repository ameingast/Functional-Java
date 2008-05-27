package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExistsTest extends BaseTest {
	protected Exists<Integer> getEvenExists() {
		return new Exists<Integer>(Utils.existsEvenFuncor);
	}

	@Test
	public void testExistsEven() {
		Boolean result = false;

		for (final Integer i : data)
			if (i % 2 == 0) {
				result = true;
				break;
			}
		assertEquals(result, getEvenExists().apply(data));
	}
}
