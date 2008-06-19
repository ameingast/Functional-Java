package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor;

public class Filter<A> implements Functor<List<A>,List<A>> {
	public final FilterFunctor<A> functor;

	public final FoldFunctor<A,List<A>> filterFunctor = new FoldFunctor<A,List<A>>() {
		@Override
		public List<A> apply(final A a, final List<A> b) {
			if (functor.apply(a))
				b.add(a);
			return b;
		}
	};

	public Filter(final FilterFunctor<A> functor) {
		this.functor = functor;
	}

	@Override
	public List<A> apply(final List<A> as) {
		return new Fold<A,List<A>>(filterFunctor).apply(as, new ArrayList<A>(as.size()));
	}
}