package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.S;

public abstract class ForeachFunctor<A> implements S<List<A>> {
	public abstract void handle(A a);

	@Override
	public void apply(final List<A> as) {
		for (final A a : as)
			handle(a);
	}
}