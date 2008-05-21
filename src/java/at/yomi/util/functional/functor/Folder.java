package at.yomi.util.functional.functor;

import java.util.List;

public abstract class Folder<A,B> implements Functor<List<A>,B,B> {
	public abstract B fold(final A a, final B e);

	public B apply(final List<A> as, B... bs) {
		B b = bs[0];

		for (A a : as)
			b = fold(a, b);
		return b;
	}
}