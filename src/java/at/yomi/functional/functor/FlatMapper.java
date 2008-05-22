package at.yomi.functional.functor;

import java.util.ArrayList;
import java.util.List;

public abstract class FlatMapper<A,B> implements Functor<List<List<A>>,List<B>,Object> {
	public abstract B map(final A a);

	@SuppressWarnings("unchecked")
	public List<B> apply(final List<List<A>> as, final Object... cs) {
		return new Folder<List<A>,List<B>>() {
			@Override
			public List<B> fold(final List<A> aas, final List<B> e) {
				for (final A a : aas)
					e.add(map(a));
				return e;
			}

		}.apply(as, new ArrayList<B>());
	}
}