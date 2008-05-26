package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor2;
import at.yomi.functor.f.MapFunctor;

public class MapFold<A,B,C> implements Functor2<List<A>,C,C> {
	protected final FoldFunctor<B,C> foldFunctor;

	protected final MapFunctor<A,B> mapFunctor;

	public MapFold(final MapFunctor<A,B> mapFunctor, final FoldFunctor<B,C> foldFunctor) {
		this.mapFunctor = mapFunctor;
		this.foldFunctor = foldFunctor;
	}

	@Override
	public C apply(final List<A> as, final C c) {
		return new Fold<B,C>(foldFunctor) {}.apply(new Map<A,B>(mapFunctor) {}.apply(as), c);
	}
}