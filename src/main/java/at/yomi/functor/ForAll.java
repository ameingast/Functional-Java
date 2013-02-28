package at.yomi.functor;

import java.util.List;

import at.yomi.functor.f.ForAllFunctor;
import at.yomi.functor.f.Functor;

public class ForAll<A> implements Functor<List<A>, Boolean> {
    public final ForAllFunctor<A> functor;

    public ForAll(final ForAllFunctor<A> functor) {
        this.functor = functor;
    }

    @Override
    public Boolean apply(final List<A> as) {
        return as.size() == 0
            || (functor.apply(as.get(0)) && new ForAll<A>(functor).apply(as
                .subList(1, as.size())));
    }
}
