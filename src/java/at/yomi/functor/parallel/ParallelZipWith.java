package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipWith;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.f.ZipWithFunctor;
import at.yomi.functor.parallel.aggregator.MapWorker;
import at.yomi.pair.Pair;

public class ParallelZipWith<A,B,C> extends ZipWith<A,B,C> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

	protected final MapFunctor<Pair<A,B>,C> zipFunctor = new MapFunctor<Pair<A,B>,C>() {
		@Override
		public C apply(Pair<A,B> a) {
			return functor.apply(a.first, a.second);
		}
	};

	public ParallelZipWith(final ZipWithFunctor<A,B,C> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZipWith(final ZipWithFunctor<A,B,C> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelZipWith(final ZipWithFunctor<A,B,C> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<C> apply(final List<A> as, final List<B> bs) {
		return new ParallelMap<Pair<A,B>,C>(zipFunctor, workerCount, commitInterval) {}
				.apply(new Zip<A,B>().apply(as, bs));
	}
}
