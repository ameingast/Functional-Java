package at.yomi.functor.parallel;

import at.yomi.functor.Map;
import at.yomi.functor.UnZipWith;
import at.yomi.functor.f.UnZipWithFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.pair.Pair;

public class ParallelUnZipWith<A,B,C> extends UnZipWith<A,B,C> {
	protected final Integer workerCount;

	public ParallelUnZipWith(final UnZipWithFunctor<A,B,C> functor) {
		this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
	}

	public ParallelUnZipWith(final UnZipWithFunctor<A,B,C> functor, final Integer workerCount) {
		super(functor);
		this.workerCount = workerCount;
	}

	@Override
	protected Map<Pair<A,B>,C> getMap() {
		return new ParallelMap<Pair<A,B>,C>(unpairFunctor, workerCount);
	}
}
