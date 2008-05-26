package at.yomi.functor.f;

import at.yomi.pair.Pair;

public interface ZipFunctor<A,B> extends ZipWithFunctor<A,B,Pair<A,B>> {}
