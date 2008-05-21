package at.yomi.util.functional.functor.parallel;

import at.yomi.util.functional.functor.Mapper;
import at.yomi.util.functional.functor.MapperTest;

public class ParallelMapperTest extends MapperTest {
	protected Mapper<Integer,Integer> getMapper() {
		return new ParallelMapper<Integer,Integer>(WORK_COUNT) {
			public Integer map(Integer a) {
				return a + 1;
			}
		};
	}
}
