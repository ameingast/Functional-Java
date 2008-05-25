package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.F;

public abstract class Filter<A> implements F<List<A>,List<A>> {
	public abstract boolean filter(A a);

	@Override
	public List<A> apply(final List<A> as) {
		return new Folder<A,List<A>>() {
			@Override
			public List<A> fold(final A a, final List<A> e) {
				if (filter(a))
					e.add(a);
				return e;
			}
		}.apply(as, new ArrayList<A>());
	}
}