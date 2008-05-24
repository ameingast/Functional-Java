package at.yomi.functional.functor.parallel;

import at.yomi.functional.functor.Mapper;
import at.yomi.functional.functor.MapperTest;

public class ParallelMapperWithCommitIntervalTest extends MapperTest {
	@Override
	protected Mapper<Integer,Integer> getAddOneMapper() {
		return new ParallelMapper<Integer,Integer>(WORK_COUNT, COMMIT_INTERVAL) {
			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Override
	protected Mapper<Integer,Integer> getMulTenMapper() {
		return new ParallelMapper<Integer,Integer>(WORK_COUNT, COMMIT_INTERVAL) {
			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}
}