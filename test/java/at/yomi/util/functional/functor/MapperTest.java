package at.yomi.util.functional.functor;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

import at.yomi.util.functional.BaseTest;

public class MapperTest extends BaseTest {

	protected Mapper<Integer,Integer> addOneMapper;

	public MapperTest() {
		addOneMapper = getMapper();
	}

	protected Mapper<Integer,Integer> getMapper() {
		return new Mapper<Integer,Integer>() {
			public Integer map(Integer a) {
				return a + 1;
			}
		};
	}

	@Test
	public void testAddOne() {
		List<Integer> bs = addOneMapper.apply(as);

		List<Integer> cs = new ArrayList<Integer>();
		for (Integer i : as)
			cs.add(i + 1);
		assertArrayEquals(cs.toArray(), bs.toArray());
	}
}
