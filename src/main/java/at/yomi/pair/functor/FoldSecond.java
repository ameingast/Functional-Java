package at.yomi.pair.functor;

import at.yomi.functor.MapFold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.Pair;

public class FoldSecond<A, B, C> extends MapFold<Pair<A, B>, B, C> {
    public FoldSecond(final FoldFunctor<B, C> foldFunctor) {
        super(new ExtractSecondFunctor<A, B>(), foldFunctor);
    }
}
