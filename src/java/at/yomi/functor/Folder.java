package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.F2;

public abstract class Folder<A,B> implements F2<List<A>,B,B> {

	public abstract B fold(final A a, final B e);

	@Override
	public B apply(final List<A> as, final B e) {
		B b = e;

		for (final A a : as)
			b = fold(a, b);
		return b;
	}
}