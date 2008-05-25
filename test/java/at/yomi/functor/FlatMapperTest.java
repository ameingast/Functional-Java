package at.yomi.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import at.yomi.functor.FlatMapper;

public class FlatMapperTest extends BaseTest {
	@Test
	public void testAddTwoAndFlatten() {
		final List<Integer> es = new FlatMapper<Integer,Integer>() {
			@Override
			public Integer map(Integer a) {
				return a * 2;
			}
		}.apply(aas);
		final List<Integer> results = new ArrayList<Integer>();

		for (final Collection<Integer> as : aas)
			for (final Integer i : as)
				results.add(i * 2);
		assertArrayEquals(results.toArray(), es.toArray());
	}
}
