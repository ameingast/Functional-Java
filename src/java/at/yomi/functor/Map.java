package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.Functor;
import at.yomi.functor.f.MapFunctor;

public class Map<A,B> implements Functor<List<A>,List<B>> {
	protected final MapFunctor<A,B> functor;

	public Map(final MapFunctor<A,B> functor) {
		this.functor = functor;
	}

	@Override
	public List<B> apply(final List<A> as) {
		final List<B> bs = new ArrayList<B>(as.size());

		for (final A a : as)
			bs.add(functor.apply(a));
		return bs;
	}
}