package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.F;

public abstract class Map<A,B> implements F<List<A>,List<B>> {
	public abstract B map(final A a);

	@Override
	public List<B> apply(final List<A> as) {
		final List<B> bs = new ArrayList<B>(as.size());
		for (final A a : as)
			bs.add(map(a));
		return bs;
	}
}