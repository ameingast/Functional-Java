package at.yomi.pair.functor;

import at.yomi.functor.f.MapFunctor;
import at.yomi.pair.Pair;

public final class ExtractFirstFunctor<A,B> implements MapFunctor<Pair<A,B>,A> {
	@Override
	public A apply(final Pair<A,B> a) {
		return a.first;
	}
}
