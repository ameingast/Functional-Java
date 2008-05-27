package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.SideEffect1;

public class Foreach<A> implements SideEffect1<List<A>> {
	protected final SideEffect1<A> sideEffect;

	public Foreach(final SideEffect1<A> sideEffect) {
		this.sideEffect = sideEffect;
	}

	@Override
	public void apply(final List<A> as) {
		for (final A a : as)
			sideEffect.apply(a);
	}
}