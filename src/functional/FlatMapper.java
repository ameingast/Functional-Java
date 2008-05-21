package functional;

import java.util.ArrayList;
import java.util.Collection;

public abstract class FlatMapper<A,B> implements Functor<Collection<Collection<A>>, Collection<B>, Object> {
	public abstract B map(final A a);
	
	@SuppressWarnings("unchecked")
	public Collection<B> apply(Collection<Collection<A>> as, Object... cs) {
		return new Folder<Collection<A>,Collection<B>>() {
			public Collection<B> fold(Collection<A> aas, Collection<B> e) {
				for (A a : aas)
					e.add(map(a));
				return e;
			}
			
		}.apply(as, new ArrayList<B>());
	}
}
