package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.SideEffect;

public class Foreach<A> implements SideEffect<List<A>> {
	private final SideEffect<A> sideEffect;

	public Foreach(final SideEffect<A> sideEffect) {
		this.sideEffect = sideEffect;
	}

	@Override
	public void apply(final List<A> as) {
		for (final A a : as)
			sideEffect.apply(a);
	}
}