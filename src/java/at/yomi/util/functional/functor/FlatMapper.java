package at.yomi.util.functional.functor;

import java.util.ArrayList;
import java.util.List;

public abstract class FlatMapper<A,B> implements Functor<List<List<A>>,List<B>,Object> {
	public abstract B map(final A a);

	@SuppressWarnings("unchecked")
	public List<B> apply(List<List<A>> as, Object... cs) {
		return new Folder<List<A>,List<B>>() {
			public List<B> fold(List<A> aas, List<B> e) {
				for (A a : aas)
					e.add(map(a));
				return e;
			}

		}.apply(as, new ArrayList<B>());
	}
}
