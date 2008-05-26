package at.yomi.functor.parallel;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.Filter;
import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelFilter<A> extends Filter<A> {
	private final Integer workerCount;

	private final Integer commitInterval;

	protected MapFunctor<A,A> mapFunctor = new MapFunctor<A,A>() {
		@Override
		public A apply(A a) {
			return functor.apply(a) ? a : null;
		}
	};

	protected FoldFunctor<A,List<A>> foldFunctor = new FoldFunctor<A,List<A>>() {
		@Override
		public List<A> apply(A a, List<A> b) {
			if (a != null)
				b.add(a);
			return b;
		}
	};

	public ParallelFilter(final FilterFunctor<A> functor) {
		this(functor, MapWorker.DEFAULT_WORKER_COUNT, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelFilter(final FilterFunctor<A> functor, final Integer workerCount) {
		this(functor, workerCount, MapWorker.DEFAULT_COMMIT_INTERVAL);
	}

	public ParallelFilter(final FilterFunctor<A> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public List<A> apply(final List<A> as) {
		return new StrictParallelMapFold<A,A,List<A>>(mapFunctor, foldFunctor, workerCount,
				commitInterval).apply(as, new ArrayList<A>());
	}
}
