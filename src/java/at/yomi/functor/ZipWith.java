package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.f.Functor2;
import at.yomi.functor.f.ZipWithFunctor;

public class ZipWith<A, B, C> implements Functor2<List<A>, List<B>, List<C>> {
    protected final ZipWithFunctor<A, B, C> functor;

    public ZipWith(final ZipWithFunctor<A, B, C> functor) {
        this.functor = functor;
    }

    @Override
    public List<C> apply(final List<A> as, final List<B> bs) {
        final int s = max(as.size(), bs.size());
        final List<C> cs = new ArrayList<C>(s);

        for (int i = 0; i < s; i++)
            if (i < as.size() && i < bs.size())
                cs.add(functor.apply(as.get(i), bs.get(i)));
            else if (i < as.size())
                cs.add(functor.apply(as.get(i), null));
            else
                cs.add(functor.apply(null, bs.get(i)));

        return cs;
    }

    private final int max(final int size, final int size2) {
        return size > size2 ? size : size2;
    }
}
