package at.yomi.pair.functor;

import at.yomi.functor.f.MapFunctor;
import at.yomi.pair.Pair;

public final class ExtractSecondFunctor<A, B> implements
        MapFunctor<Pair<A, B>, B> {
    @Override
    public B apply(final Pair<A, B> a) {
        return a.second;
    }
}
