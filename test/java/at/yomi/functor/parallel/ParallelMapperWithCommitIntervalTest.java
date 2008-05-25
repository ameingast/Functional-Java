package at.yomi.functor.parallel;

import at.yomi.functor.Mapper;
import at.yomi.functor.MapperTest;
import at.yomi.functor.parallel.ParallelMapper;

public class ParallelMapperWithCommitIntervalTest extends MapperTest {
	@Override
	protected Mapper<Integer,Integer> getAddOneMapper() {
		return new ParallelMapper<Integer,Integer>(WORKER_COUNT, COMMIT_INTERVAL) {
			@Override
			public Integer map(final Integer a) {
				return a + 1;
			}
		};
	}

	@Override
	protected Mapper<Integer,Integer> getMulTenMapper() {
		return new ParallelMapper<Integer,Integer>(WORKER_COUNT, COMMIT_INTERVAL) {
			@Override
			public Integer map(final Integer a) {
				return 10 * a;
			}
		};
	}
}
