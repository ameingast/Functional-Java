package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.Functor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.f.UnZipWithFunctor;
import at.yomi.pair.Pair;

public class UnZipWith<A, B, C> implements Functor<List<Pair<A, B>>, List<C>> {
    protected final UnZipWithFunctor<A, B, C> functor;

    protected final MapFunctor<Pair<A, B>, C> unpairFunctor = new MapFunctor<Pair<A, B>, C>() {
        @Override
        public C apply(Pair<A, B> a) {
            return functor.apply(a.first, a.second);
        }
    };

    public UnZipWith(final UnZipWithFunctor<A, B, C> functor) {
        this.functor = functor;
    }

    public List<C> apply(final List<Pair<A, B>> as) {
        return getMap().apply(as);
    }

    protected Map<Pair<A, B>, C> getMap() {
        return new Map<Pair<A, B>, C>(unpairFunctor);
    }
}
