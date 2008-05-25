package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.F2;

public abstract class ZipWith<A,B,C> implements F2<List<A>,List<B>,List<C>> {
	public abstract C handle(final A a, final B b);

	@Override
	public List<C> apply(final List<A> as, final List<B> bs) {
		final int s = max(as.size(), bs.size());
		final List<C> cs = new ArrayList<C>(s);

		for (int i = 0; i < s; i++)
			if (i < as.size() && i < bs.size())
				cs.add(handle(as.get(i), bs.get(i)));
			else if (i < as.size())
				cs.add(handle(as.get(i), null));
			else
				cs.add(handle(null, bs.get(i)));

		return cs;
	}

	private final int max(final int size, final int size2) {
		return size > size2 ? size : size2;
	}
}
