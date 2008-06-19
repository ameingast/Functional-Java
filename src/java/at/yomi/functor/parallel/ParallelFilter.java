package at.yomi.functor.parallel;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.Filter;
import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;

public class ParallelFilter<A> extends Filter<A> {
	protected final Integer workerCount;

	protected final MapFunctor<A,A> mapFunctor = new MapFunctor<A,A>() {
		@Override
		public A apply(A a) {
			return functor.apply(a) ? a : null;
		}
	};

	protected final FoldFunctor<A,List<A>> foldFunctor = new FoldFunctor<A,List<A>>() {
		@Override
		public List<A> apply(A a, List<A> b) {
			if (a != null)
				b.add(a);
			return b;
		}
	};

	public ParallelFilter(final FilterFunctor<A> functor) {
		this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
	}

	public ParallelFilter(final FilterFunctor<A> functor, final Integer workerCount) {
		super(functor);
		this.workerCount = workerCount;
	}

	@Override
	public List<A> apply(final List<A> as) {
		return new StrictParallelMapFold<A,A,List<A>>(mapFunctor, foldFunctor, workerCount).apply(as, new ArrayList<A>(
				as.size()));
	}
}
