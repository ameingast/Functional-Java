package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor2;

public class Fold<A, B> implements Functor2<List<A>, B, B> {
    public final FoldFunctor<A, B> functor;

    public Fold(final FoldFunctor<A, B> functor) {
        this.functor = functor;
    }

    @Override
    public B apply(final List<A> as, final B b) {
        B e = b;

        for (final A a : as)
            e = functor.apply(a, e);
        return e;
    }
}
