package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.yomi.functor.f.SideEffect;

public class ForeachTest extends BaseTest {
	@Test
	public void testSideEffects() {
		final List<Integer> bs = new ArrayList<Integer>();

		new Foreach<Integer>(new SideEffect<Integer>() {
			@Override
			public void apply(final Integer s) {
				bs.add(s);
			}
		}).apply(data);

		assertArrayEquals(data.toArray(), bs.toArray());
	}
}