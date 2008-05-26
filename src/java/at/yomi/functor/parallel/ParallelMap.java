package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Map;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.ListAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelMap<A,B> extends Map<A,B> {
	private final Integer workerCount;

	private final Integer commitInterval;

	public ParallelMap(final MapFunctor<A,B> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMap(final MapFunctor<A,B> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMap(final MapFunctor<A,B> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<B> apply(final List<A> as) {
		final ListAggregator<B> aggregator = new ListAggregator<B>(as.size());
		try {
			return MapWorker.start(workerCount, commitInterval, aggregator, as, functor);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}