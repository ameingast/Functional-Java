package functional;

import java.util.Collection;

public abstract class Folder<A, B> implements Functor<Collection<A>, B, B> {
	public abstract B fold(final A a, final B e);

	public B apply(final Collection<A> as, B... bs) {
		B b = bs[0];

		for (A a : as)
			b = fold(a, b);
		return b;
	}
}