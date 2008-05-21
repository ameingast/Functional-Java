package functional;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Filter<A> implements Functor<Collection<A>, Collection<A>, A> {
	public abstract boolean filter(A a);

	@SuppressWarnings("unchecked")
	public Collection<A> apply(final Collection<A> as, A... ass) {
		return new Folder<A, Collection<A>>() {
			public Collection<A> fold(A a, Collection<A> e) {
				if (filter(a))
					e.add(a);
				return e;
			}
		}.apply(as, new ArrayList<A>());
	}
}