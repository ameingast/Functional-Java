package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Exists;
import at.yomi.functor.f.ExistsFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;

public class ParallelExists<A> extends Exists<A> {
	protected final Integer workerCount;

	protected final Integer commitInterval;

	protected final MapFunctor<A,Boolean> mapFunctor = new MapFunctor<A,Boolean>() {
		@Override
		public Boolean apply(A a) {
			return functor.apply(a);
		}
	};

	protected final FoldFunctor<Boolean,Boolean> foldFunctor = new FoldFunctor<Boolean,Boolean>() {
		@Override
		public Boolean apply(Boolean a, Boolean b) {
			return a || b;
		}
	};

	public ParallelExists(final ExistsFunctor<A> functor, final Integer workerCount,
			final Integer commitInterval) {
		super(functor);
		this.workerCount = workerCount;
		this.commitInterval = commitInterval;
	}

	@Override
	public Boolean apply(final List<A> as) {
		return new ParallelMapFold<A,Boolean,Boolean>(mapFunctor, foldFunctor, workerCount,
				commitInterval).apply(as, true);
	}
}
