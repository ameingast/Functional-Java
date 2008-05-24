package at.yomi.benchmark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.benchmark.annotations.BMIgnore;
import at.yomi.functional.functor.FilterBenchmark;
import at.yomi.functional.functor.FolderBenchmark;
import at.yomi.functional.functor.MapFolderBenchmark;
import at.yomi.functional.functor.MapperBenchmark;
import at.yomi.mp.MPBenchmark;

public class MainBenchmark {
	private static Class<?>[] classes = { FilterBenchmark.class, FolderBenchmark.class,
			MapFolderBenchmark.class, MapperBenchmark.class, MPBenchmark.class };

	public static void main(final String[] args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		for (final Class<?> klass : classes)
			for (final Method m : klass.getMethods())
				if (m.isAnnotationPresent(BM.class) && !m.isAnnotationPresent(BMIgnore.class))
					m.invoke(null);

		AbstractBenchmark.run();
	}
}
