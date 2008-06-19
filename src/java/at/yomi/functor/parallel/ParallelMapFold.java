package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.MapFold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.functor.parallel.aggregator.Aggregator;
import at.yomi.functor.parallel.aggregator.FoldAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelMapFold<A,B,C> extends MapFold<A,B,C> {
	protected final Integer workerCount;

	public ParallelMapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor) {
		this(mapFunctor, foldFunctor, AbstractWorker.DEFAULT_WORKER_COUNT);
	}

	public ParallelMapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor,
			final Integer workerCount) {
		super(mapFunctor, foldFunctor);
		this.workerCount = workerCount;
	}

	@Override
	public C apply(final List<A> as, final C c) {
		try {
			return MapWorker.start(workerCount, getFolderAggregator(as.size(), c), as, mapFunctor);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected Aggregator<B,C> getFolderAggregator(final Integer itemCount, final C e) {
		return new FoldAggregator<B,C>(itemCount, foldFunctor, e);
	}
}
