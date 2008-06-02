package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.Functor;

public class TakeWhile<A> implements Functor<List<A>,List<A>> {
	public final Functor<A,Boolean> functor;

	public TakeWhile(final Functor<A,Boolean> functor) {
		this.functor = functor;
	}

	@Override
	public List<A> apply(final List<A> as) {
		final List<A> aas = new ArrayList<A>();

		for (final A a : as)
			if (functor.apply(a))
				aas.add(a);
			else
				return aas;
		return aas;
	}
}
