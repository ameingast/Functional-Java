package at.yomi.functional.functor;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import at.yomi.functional.BaseTest;
import at.yomi.functional.functor.FlatMapper;

public class FlatMapperTest extends BaseTest {
	@Test
	public void testAddTwoAndFlatten() {
		List<Integer> es = new FlatMapper<Integer,Integer>() {
			public Integer map(Integer a) {
				return a * 2;
			}
		}.apply(aas);
		List<Integer> results = new ArrayList<Integer>();

		for (Collection<Integer> as : aas)
			for (Integer i : as)
				results.add(i * 2);
		assertArrayEquals(results.toArray(), es.toArray());
	}
}
