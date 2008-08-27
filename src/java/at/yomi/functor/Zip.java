package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.Functor2;
import at.yomi.functor.f.ZipFunctor;
import at.yomi.pair.Pair;

public class Zip<A, B> implements Functor2<List<A>, List<B>, List<Pair<A, B>>> {
    protected final ZipFunctor<A, B> pairFunctor = new ZipFunctor<A, B>() {
        @Override
        public Pair<A, B> apply(A a, B b) {
            return new Pair<A, B>(a, b);
        }
    };

    @Override
    public List<Pair<A, B>> apply(final List<A> as, final List<B> bs) {
        return getZipper().apply(as, bs);
    }

    protected ZipWith<A, B, Pair<A, B>> getZipper() {
        return new ZipWith<A, B, Pair<A, B>>(pairFunctor);
    }
}
