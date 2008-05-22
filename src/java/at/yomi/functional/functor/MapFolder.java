package at.yomi.functional.functor;

import java.util.List;

public abstract class MapFolder<A,B,C> implements Functor<List<A>,C,C> {

	private final MapFolder<A,B,C> self = this;

	public abstract B map(A a);

	public abstract C fold(B b, C e);

	public C apply(final List<A> as, final C... cs) {
		final List<B> bs = new Mapper<A,B>() {
			@Override
			public B map(A a) {
				return self.map(a);
			}
		}.apply(as);

		return new Folder<B,C>() {
			@Override
			public C fold(final B b, final C e) {
				return self.fold(b, e);
			}
		}.apply(bs, cs);
	}
}