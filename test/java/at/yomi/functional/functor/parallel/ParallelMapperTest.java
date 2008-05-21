package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.Mapper;
import at.yomi.functional.functor.MapperTest;
import at.yomi.functional.functor.parallel.ParallelMapper;

public class ParallelMapperTest extends MapperTest {
	protected Mapper<Integer,Integer> getMapper() {
		return new ParallelMapper<Integer,Integer>(WORK_COUNT) {
			public Integer map(Integer a) {
				return a + 1;
			}
		};
	}
}
