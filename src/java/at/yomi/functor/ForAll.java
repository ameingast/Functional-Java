package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.ForAllFunctor;
import at.yomi.functor.f.Functor;

public class ForAll<A> implements Functor<List<A>,Boolean> {
	protected final ForAllFunctor<A> functor;

	public ForAll(final ForAllFunctor<A> functor) {
		this.functor = functor;
	}

	@Override
	public Boolean apply(final List<A> as) {
		for (final A a : as)
			if (!functor.apply(a))
				return false;
		return true;
	}
}
