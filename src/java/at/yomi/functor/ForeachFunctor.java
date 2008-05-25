package at.yomi.functor;

import java.util.List;

public abstract class ForeachFunctor<A> implements Functor<List<A>,A,A> {
	public abstract void handle(A a);

	public A apply(final List<A> as, final A... aas) {
		for (final A a : as)
			handle(a);
		return null;
	}
}