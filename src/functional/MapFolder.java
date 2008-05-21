package functional;

import java.util.Collection;
import java.util.List;

public abstract class MapFolder<A, B, C> implements Functor<List<A>, C, C> {
	public abstract B fmap(A a);

	public abstract C ffold(B b, C e);

	public C apply(List<A> as, C... cs) {
		Collection<B> bs = new Mapper<A, B>() {
			public B map(A a) {
				return fmap(a);
			}
		}.apply(as);

		return new Folder<B, C>() {
			public C fold(B b, C e) {
				return ffold(b, e);
			}
		}.apply(bs, cs);
	}
}
