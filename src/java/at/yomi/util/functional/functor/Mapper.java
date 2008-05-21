package at.yomi.util.functional.functor;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<A,B> implements Functor<List<A>,List<B>,Object> {
	public abstract B map(final A a);

	public List<B> apply(final List<A> as, final Object... os) {
		List<B> bs = new ArrayList<B>(as.size());
		for (A a : as)
			bs.add(map(a));
		return bs;
	}
}