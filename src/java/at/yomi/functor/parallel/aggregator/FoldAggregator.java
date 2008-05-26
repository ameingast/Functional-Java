package at.yomi.functor.parallel.aggregator;

import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.pair.Pair;

public class FoldAggregator<A,B> extends Aggregator<A,B> {
	protected B e;

	protected final FoldFunctor<A,B> foldFunctor;

	protected final Fold<Pair<Integer,A>,B> pairFold = new Fold<Pair<Integer,A>,B>(
			new FoldFunctor<Pair<Integer,A>,B>() {
				@Override
				public B apply(Pair<Integer,A> a, B b) {
					return foldFunctor.apply(a.second, b);
				}
			});

	public FoldAggregator(final Integer itemCount, final FoldFunctor<A,B> foldFunctor, final B e) {
		super(itemCount);
		this.foldFunctor = foldFunctor;
		this.e = e;
	}

	@Override
	public synchronized void add(final List<Pair<Integer,A>> items) {
		e = pairFold.apply(items, e);
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