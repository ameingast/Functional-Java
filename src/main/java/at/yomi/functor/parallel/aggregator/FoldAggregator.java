package at.yomi.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.Pair;
import at.yomi.pair.functor.FoldSecond;

public class FoldAggregator<A, B> extends Aggregator<A, B> {
    protected B e;

    protected final FoldFunctor<A, B> foldFunctor;

    public FoldAggregator(final Integer itemCount,
        final FoldFunctor<A, B> foldFunctor, final B e) {
        super(itemCount);
        this.foldFunctor = foldFunctor;
        this.e = e;
    }

    @Override
    public synchronized void add(final List<Pair<Integer, A>> items) {
        e = new FoldSecond<Integer, A, B>(foldFunctor).apply(items, e);
        counter.returnTickets(items.size());
    }

    @Override
    public B getResult() throws InterruptedException {
        counter.waitForLimit();
        return e;
    }

    @Override
    public void add(final A a, final Integer amount) {}
}