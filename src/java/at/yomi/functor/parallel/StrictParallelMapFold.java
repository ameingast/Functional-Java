package at.yomi.functor.parallel;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.functor.parallel.aggregator.Aggregator;
import at.yomi.functor.parallel.aggregator.StrictFoldAggregator;

public class StrictParallelMapFold<A,B,C> extends ParallelMapFold<A,B,C> {
	public StrictParallelMapFold(final MapFunctor<A,B> mapFunctor,
			final FoldFunctor<B,C> foldFunctor) {
		this(mapFunctor, foldFunctor, AbstractWorker.DEFAULT_WORKER_COUNT);
	}

	public StrictParallelMapFold(final MapFunctor<A,B> mapFunctor,
			final FoldFunctor<B,C> foldFunctor, final Integer workerCount) {
		super(mapFunctor, foldFunctor, workerCount);
	}

	@Override
	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new StrictFoldAggregator<B,C>(itemCount, foldFunctor, e);
	}
}