package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor;
import at.yomi.pair.Pair;

public class UnZip<A, B> implements
        Functor<List<Pair<A, B>>, Pair<List<A>, List<B>>> {
    public Pair<List<A>, List<B>> apply(final List<Pair<A, B>> pairs) {
        return new Fold<Pair<A, B>, Pair<List<A>, List<B>>>(
            new FoldFunctor<Pair<A, B>, Pair<List<A>, List<B>>>() {
                @Override
                public Pair<List<A>, List<B>> apply(final Pair<A, B> a,
                    final Pair<List<A>, List<B>> b) {
                    b.first.add(a.first);
                    b.second.add(a.second);
                    return b;
                }
            }).apply(pairs, new Pair<List<A>, List<B>>(new ArrayList<A>(pairs
            .size()), new ArrayList<B>(pairs.size())));
    }
}
