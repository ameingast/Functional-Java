package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.F2;
import at.yomi.pair.Pair;

public class Zip<A,B> implements F2<List<A>,List<B>,List<Pair<A,B>>> {
	@Override
	public List<Pair<A,B>> apply(final List<A> as, final List<B> bs) {
		return getZipper().apply(as, bs);
	}

	protected ZipWith<A,B,Pair<A,B>> getZipper() {
		return new ZipWith<A,B,Pair<A,B>>() {
			@Override
			public Pair<A,B> handle(final A a, final B b) {
				return new Pair<A,B>(a, b);
			}
		};
	}
}
