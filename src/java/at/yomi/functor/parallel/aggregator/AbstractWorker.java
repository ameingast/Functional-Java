package at.yomi.functor.parallel.aggregator;

import java.util.ArrayList;
import java.util.List;

import at.yomi.collection.Helper;
import at.yomi.functor.Zip;
import at.yomi.pair.Pair;
import at.yomi.utils.ThreadPoolExecutorSingleton;

public abstract class AbstractWorker<A, B> implements Runnable {
    public static final Integer DEFAULT_WORKER_COUNT = 4;

    protected final Aggregator<B, ?> aggregator;

    protected List<Pair<Integer, A>> items;

    protected static <A, B> void setWork(
        final List<AbstractWorker<A, B>> workers, final List<A> as) {
        int i = 0;

        for (final List<A> splitAs : Helper.split(as, workers.size())) {
            workers.get(i).addAll(splitAs);
            startWorker(workers.get(i++));
        }
    }

    private static <A, B> void startWorker(final AbstractWorker<A, B> worker) {
        ThreadPoolExecutorSingleton.getInstance().execute(worker);
    }

    public AbstractWorker(final Aggregator<B, ?> aggregator) {
        this.aggregator = aggregator;
    }

    protected synchronized void addAll(final List<A> as) {
        final List<Pair<Integer, A>> tmp = new ArrayList<Pair<Integer, A>>(as
            .size());
        tmp.addAll(new Zip<Integer, A>().apply(
            aggregator.getTickets(as.size()), as));
        items = tmp;
    }
}
