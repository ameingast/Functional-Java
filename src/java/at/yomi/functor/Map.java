package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor;
import at.yomi.functor.f.MapFunctor;

public class Map<A, B> implements Functor<List<A>, List<B>> {
    public final MapFunctor<A, B> functor;

    public final FoldFunctor<A, List<B>> foldFunctor = new FoldFunctor<A, List<B>>() {
        @Override
        public List<B> apply(A a, List<B> b) {
            b.add(functor.apply(a));
            return b;
        }
    };

    public Map(final MapFunctor<A, B> functor) {
        this.functor = functor;
    }

    @Override
    public List<B> apply(final List<A> as) {
        return new Fold<A, List<B>>(foldFunctor).apply(as, new ArrayList<B>());
    }
}
