package at.yomi.util.functional.functor;

import java.util.List;

public abstract class ForeachFunctor<A> implements Functor<List<A>,A,A> {
	public abstract void handle(A a);

	public A apply(final List<A> as, A... aas) {
		for (A a : as)
			handle(a);
		return null;
	}
}
