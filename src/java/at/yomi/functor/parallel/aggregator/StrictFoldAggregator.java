package at.yomi.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.Pair;
import at.yomi.pair.PairFirstElementComparator;
import at.yomi.pair.functor.FoldSecond;

public class StrictFoldAggregator<A, B> extends FoldAggregator<A, B> {
    protected List<Pair<Integer, A>> items = new ArrayList<Pair<Integer, A>>();

    public StrictFoldAggregator(final Integer itemCount,
        final FoldFunctor<A, B> foldFunctor, final B e) {
        super(itemCount, foldFunctor, e);
    }

    @Override
    public synchronized void add(final List<Pair<Integer, A>> items) {
        this.items.addAll(items);
        counter.returnTickets(items.size());
    }

    @Override
    public B getResult() throws InterruptedException {
        counter.waitForLimit();
        Collections.sort(items, new PairFirstElementComparator<Integer, A>());
        return new FoldSecond<Integer, A, B>(foldFunctor).apply(items, e);
    }
}