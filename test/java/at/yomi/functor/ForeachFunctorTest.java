package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.functor.ForeachFunctor;

public class ForeachFunctorTest extends BaseTest {
	@Test
	public void testSideEffects() {
		final List<Integer> bs = new ArrayList<Integer>();

		new ForeachFunctor<Integer>() {
			@Override
			public void handle(final Integer a) {
				bs.add(a);
			}
		}.apply(data);

		assertArrayEquals(data.toArray(), bs.toArray());
	}
}