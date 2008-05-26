package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.Functor;
import at.yomi.pair.Pair;

public class UnZip<A,B> implements Functor<List<Pair<A,B>>,Pair<List<A>,List<B>>> {
	public Pair<List<A>,List<B>> apply(final List<Pair<A,B>> pairs) {
		final List<A> as = new ArrayList<A>(pairs.size());
		final List<B> bs = new ArrayList<B>(pairs.size());

		for (final Pair<A,B> pair : pairs) {
			as.add(pair.first);
			bs.add(pair.second);
		}
		return new Pair<List<A>,List<B>>(as, bs);
	}
}
