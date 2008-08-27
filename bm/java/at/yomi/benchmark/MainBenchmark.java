package at.yomi.benchmark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.yomi.benchmark.annotations.BM;
import at.yomi.benchmark.annotations.BMIgnore;
import at.yomi.functor.FilterBenchmark;
import at.yomi.functor.FoldBenchmark;
import at.yomi.functor.MapBenchmark;
import at.yomi.functor.MapFoldBenchmark;
import at.yomi.functor.SortBenchmark;
import at.yomi.functor.parallel.PFService;

public class MainBenchmark {
    private static Class<?>[] classes = { FilterBenchmark.class,
        FoldBenchmark.class, MapFoldBenchmark.class, MapBenchmark.class,
        SortBenchmark.class };

    public static void main(final String[] args)
        throws IllegalArgumentException, IllegalAccessException,
        InvocationTargetException {
        PFService.startUp();
        for (final Class<?> klass : classes)
            for (final Method m : klass.getMethods())
                if (m.isAnnotationPresent(BM.class)
                    && !m.isAnnotationPresent(BMIgnore.class))
                    m.invoke(null);

        AbstractBenchmark.run();
        PFService.shutDown();
    }
}