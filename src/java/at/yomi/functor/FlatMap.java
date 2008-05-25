package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.F;

public abstract class FlatMap<A,B> implements F<List<List<A>>,List<B>> {
	public abstract B map(final A a);

	@Override
	public List<B> apply(final List<List<A>> as) {
		return new Fold<List<A>,List<B>>() {
			@Override
			public List<B> fold(final List<A> aas, final List<B> e) {
				for (final A a : aas)
					e.add(map(a));
				return e;
			}

		}.apply(as, new ArrayList<B>());
	}
}