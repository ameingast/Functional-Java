package at.yomi.functor.parallel.aggregator;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import at.yomi.pair.Pair;
import at.yomi.pair.PairFirstElementComparator;
import at.yomi.pair.functor.MapSecond;

public class ListAggregator<A> extends Aggregator<A, List<A>> {
    private final List<Pair<Integer, A>> items = new Vector<Pair<Integer, A>>();

    public ListAggregator(final Integer itemCount) {
        super(itemCount);
    }

    @Override
    public void add(final List<Pair<Integer, A>> items) {
        this.items.addAll(items);
        counter.returnTickets(items.size());
    }

    @Override
    public List<A> getResult() throws InterruptedException {
        counter.waitForLimit();
        Collections.sort(items, new PairFirstElementComparator<Integer, A>());
        return new MapSecond<Integer, A>().apply(items);
    }

    @Override
    public void add(final A a, final Integer amount) {
        items.add(new Pair<Integer, A>(0, a));
        counter.returnTickets(amount);
    }
}