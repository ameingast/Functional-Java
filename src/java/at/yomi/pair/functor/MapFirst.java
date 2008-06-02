package at.yomi.pair.functor;

import at.yomi.functor.Map;
import at.yomi.pair.Pair;

public final class MapFirst<A,B> extends Map<Pair<A,B>,A> {
	public MapFirst() {
		super(new ExtractFirstFunctor<A,B>());
	}
}
