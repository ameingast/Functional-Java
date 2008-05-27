package at.yomi.functor.parallel;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.Filter;
import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;

public class ParallelFilter<A> extends Filter<A> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

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
