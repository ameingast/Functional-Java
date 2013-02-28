package at.yomi.pair.functor;

import at.yomi.functor.MapFold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.Pair;

public class FoldFirst<A, B, C> extends MapFold<Pair<A, B>, A, C> {
    public FoldFirst(final FoldFunctor<A, C> foldFunctor) {
        super(new ExtractFirstFunctor<A, B>(), foldFunctor);
    }
}
