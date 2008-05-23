package at.yomi.functional.functor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.annotations.BM;
import at.yomi.benchmark.annotations.BMIgnore;

public class MainBenchmark {
	private static Class<?>[] classes = { FilterBenchmark.class, FolderBenchmark.class,
			MapFolderBenchmark.class, MapperBenchmark.class, };

	public static void main(final String[] args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		for (final Class<?> klass : classes)
			for (final Method m : klass.getMethods())
				if (m.isAnnotationPresent(BM.class) && !m.isAnnotationPresent(BMIgnore.class))
					m.invoke(null);

		AbstractBenchmark.run();
	}
}
