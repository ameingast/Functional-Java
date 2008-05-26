package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.MapFold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.Aggregator;
import at.yomi.functor.parallel.aggregator.FoldAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelMapFold<A,B,C> extends MapFold<A,B,C> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelMapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor) {
		this(mapFunctor, foldFunctor, MapWorker.DEFAULT_WORKER_COUNT);
	}

	public ParallelMapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor,
			final Integer workerCount) {
		this(mapFunctor, foldFunctor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelMapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor,
			final Integer workerCount, final Integer commitInterval) {
		super(mapFunctor, foldFunctor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new FoldAggregator<B,C>(itemCount, foldFunctor, e);
	}

	@Override
	public C apply(final List<A> as, final C c) {
		final Aggregator<B,C> aggregator = getFolderAggregator(as.size(), c);

		try {
			return MapWorker.start(workerCount, commitInterval, aggregator, as, mapFunctor);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
