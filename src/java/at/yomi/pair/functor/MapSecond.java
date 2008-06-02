package at.yomi.pair.functor;

import at.yomi.functor.Map;
import at.yomi.pair.Pair;

public final class MapSecond<A,B> extends Map<Pair<A,B>,B> {
	public MapSecond() {
		super(new ExtractSecondFunctor<A,B>());
	}
}
