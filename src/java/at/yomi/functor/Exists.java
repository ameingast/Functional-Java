package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.ExistsFunctor;
import at.yomi.functor.f.Functor;

public class Exists<A> implements Functor<List<A>,Boolean> {
	protected final ExistsFunctor<A> functor;

	public Exists(final ExistsFunctor<A> functor) {
		this.functor = functor;
	}

	@Override
	public Boolean apply(final List<A> as) {
		for (final A a : as)
			if (functor.apply(a))
				return true;
		return false;
	}
}
