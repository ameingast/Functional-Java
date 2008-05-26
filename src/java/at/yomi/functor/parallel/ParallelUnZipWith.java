package at.yomi.functor.parallel;

import at.yomi.functor.Map;
import at.yomi.functor.UnZipWith;
import at.yomi.functor.f.UnZipWithFunctor;
import at.yomi.functor.parallel.aggregator.MapWorker;
import at.yomi.pair.Pair;

public class ParallelUnZipWith<A,B,C> extends UnZipWith<A,B,C> {

	protected final Integer workerCount;

	protected final Integer commitInterval;

	public ParallelUnZipWith(final UnZipWithFunctor<A,B,C> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelUnZipWith(final UnZipWithFunctor<A,B,C> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelUnZipWith(final UnZipWithFunctor<A,B,C> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	protected Map<Pair<A,B>,C> getMap() {
		return new ParallelMap<Pair<A,B>,C>(unpairFunctor, workerCount, commitInterval);
	}
}
