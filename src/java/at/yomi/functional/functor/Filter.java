package at.yomi.functional.functor;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter<A> implements Functor<List<A>,List<A>,A> {
	public abstract boolean filter(A a);

	@SuppressWarnings("unchecked")
	public List<A> apply(final List<A> as, final A... ass) {
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