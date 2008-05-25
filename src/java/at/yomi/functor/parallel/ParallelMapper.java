package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Functor;
import at.yomi.functor.Mapper;
import at.yomi.functor.parallel.aggregator.ListAggregator;
import at.yomi.functor.parallel.aggregator.Worker;

public abstract class ParallelMapper<A,B> extends Mapper<A,B> {

	private final Integer workerCount;

	private final Integer commitInterval;

	public ParallelMapper() {
		this(Worker.DEFAULT_WORKER_COUNT, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMapper(final Integer workerCount) {
		this(workerCount, Worker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMapper(final Integer workerCount, final Integer commitInterval) {
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<B> apply(final List<A> as, final Object... os) {
		final ListAggregator<B> aggregator = new ListAggregator<B>(as.size());

		Worker.createWorkers(workerCount, commitInterval, aggregator, as, true,
				new Functor<A,B,B>() {
					public B apply(final A a, final B... bs) {
						return map(a);
					}
				});

		try {
			return aggregator.getResult();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}