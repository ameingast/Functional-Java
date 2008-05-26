package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.parallel.aggregator.FoldWorker;
import at.yomi.functor.parallel.aggregator.ListAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelFold<A> extends Fold<A,A> {
	private final Integer commitInterval;

	private final Integer workerCount;

	public ParallelFold(final FoldFunctor<A,A> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelFold(final FoldFunctor<A,A> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelFold(final FoldFunctor<A,A> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public A apply(final List<A> as, final A e) {
		final ListAggregator<A> aggregator = new ListAggregator<A>(as.size());

		try {
			return FoldWorker.start(workerCount, commitInterval, aggregator, as, functor, e);
		} catch (final InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
