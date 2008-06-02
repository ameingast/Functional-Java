package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.ExistsFunctor;
import at.yomi.functor.f.Functor;

public class Exists<A> implements Functor<List<A>,Boolean> {
	public final ExistsFunctor<A> functor;

	public Exists(final ExistsFunctor<A> functor) {
		this.functor = functor;
	}

	@Override
	public Boolean apply(final List<A> as) {
		return as.size() != 0
				&& (functor.apply(as.get(0)) || new Exists<A>(functor).apply(as.subList(1, as
						.size())));
	}
}
