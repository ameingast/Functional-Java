package at.yomi.functor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.yomi.functor.f.ExistsFunctor;
import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.ForAllFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.f.UnZipWithFunctor;
import at.yomi.functor.f.ZipWithFunctor;
import at.yomi.pair.Pair;

public class Utils {
    public final static FilterFunctor<Integer> isEvenFunctor = new FilterFunctor<Integer>() {
        @Override
        public Boolean apply(Integer a) {
            return 0 == a % 2;
        }
    };

    public final static MapFunctor<Integer, Integer> mulTenFunctor = new MapFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a) {
            return a * 10;
        }
    };

    public final static MapFunctor<Integer, Integer> addOneFunctor = new MapFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a) {
            return a + 1;
        }
    };

    public final static FoldFunctor<Integer, Integer> sumFunctor = new FoldFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    };

    public final static Fold<Integer, Integer> sumFold = new Fold<Integer, Integer>(
        sumFunctor);

    public final static FoldFunctor<Float, Float> sumFloatFunctor = new FoldFunctor<Float, Float>() {
        @Override
        public Float apply(Float a, Float b) {
            return a + b;
        }
    };

    public final static FoldFunctor<String, String> concatFunctor = new FoldFunctor<String, String>() {
        @Override
        public String apply(String a, String b) {
            return b + a;
        }
    };

    public final static FoldFunctor<Integer, String> toIntegerConcatFunctor = new FoldFunctor<Integer, String>() {
        @Override
        public String apply(Integer a, String b) {
            return b + a;
        }
    };

    public final static ZipWithFunctor<Integer, Integer, Integer> safeSumFunctor = new ZipWithFunctor<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return safeSum(a, b);
        }
    };

    public final static FoldFunctor<Integer, Integer> mulFunctor = new FoldFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return a * b;
        }
    };

    public final static MapFunctor<Integer, Boolean> isPrimeFunctor = new MapFunctor<Integer, Boolean>() {
        @Override
        public Boolean apply(Integer a) {
            return isPrime(a);
        }
    };

    public final static FoldFunctor<Boolean, Integer> sumTruesFunctor = new FoldFunctor<Boolean, Integer>() {

        @Override
        public Integer apply(Boolean a, Integer b) {
            return a ? b + 1 : b;
        }

    };

    public final static MapFunctor<Integer, Float> tpolyFunctor = new MapFunctor<Integer, Float>() {
        @Override
        public Float apply(Integer a) {
            return tpoly(a);
        }
    };

    public static final UnZipWithFunctor<Integer, Integer, Integer> safePairAddFunctor = new UnZipWithFunctor<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return safeSum(a, b);
        }
    };

    public static final ForAllFunctor<Integer> allEvensFunctor = new ForAllFunctor<Integer>() {
        @Override
        public Boolean apply(Integer a) {
            return a % 2 == 0;
        }
    };

    public static final FoldFunctor<String, String> reverseStringFoldFunctor = new FoldFunctor<String, String>() {
        @Override
        public String apply(String a, String b) {
            return reverse(a) + b;
        }
    };

    public static final ExistsFunctor<Integer> existsEvenFuncor = new ExistsFunctor<Integer>() {
        @Override
        public Boolean apply(Integer a) {
            return a % 2 == 0;
        }
    };

    public static final MapFunctor<Integer, Integer> intPrimeFunctor = new MapFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a) {
            return Utils.isPrime(a) ? a : 0;
        }
    };

    public static final FoldFunctor<List<?>, List<?>> collectFunctor = new FoldFunctor<List<?>, List<?>>() {
        @SuppressWarnings("unchecked")
        @Override
        public List<?> apply(List<?> a, List<?> b) {
            ((List) b).addAll(a);
            return b;
        }
    };

    public static final Fold<List<?>, List<?>> collectFold = new Fold<List<?>, List<?>>(
        collectFunctor);

    public static final TakeWhile<Integer> takeWhileEven = new TakeWhile<Integer>(
        isEvenFunctor);

    public static final MapFunctor<Integer, Integer> evenFunctor = new MapFunctor<Integer, Integer>() {
        @Override
        public Integer apply(Integer a) {
            return a % 2 == 0 ? a : 0;
        }
    };

    public static final MapFunctor<String, Pair<String, String>> reverseStringToPairFunctor = new MapFunctor<String, Pair<String, String>>() {
        @Override
        public Pair<String, String> apply(String a) {
            return new Pair<String, String>(a, reverse(a));
        }
    };

    public static final FoldFunctor<Pair<String, String>, List<String>> collectPalindromes = new FoldFunctor<Pair<String, String>, List<String>>() {

        @Override
        public List<String> apply(Pair<String, String> a, List<String> b) {
            if (a.first != null && a.first.equals(a.second))
                b.add(a.first);
            return b;
        }
    };

    public static final Map<Integer, Integer> evenMap = new Map<Integer, Integer>(
        evenFunctor);

    public static String reverse(final String a) {
        String r = "";
        for (int i = a.length() - 1; i >= 0; i--)
            r += a.charAt(i);
        return r;
    }

    public static Boolean isPrime(final Integer a) {
        for (int i = 2; i < Math.sqrt(a); i++)
            if (a % i == 0)
                return new Boolean(false);
        return new Boolean(true);
    }

    public static Float tpoly(final Integer k) {
        float mu = (float) 10.0;
        float x, s;
        float pu = (float) 0.0;
        int i;
        int j;
        final float pol[] = new float[100];

        final int n = 50;
        x = (float) 0.2;
        for (i = 0; i < n; i++) {
            for (j = 0; j < 100; j++) {
                mu = (mu + (float) 2.0) / (float) 2.0;
                pol[j] = mu;
            }
            s = (float) 0.0;
            for (j = 0; j < 100; j++) {
                s = x * s + pol[j];
            }
            pu += s;
        }
        return new Float(pu);
    }

    protected static List<Integer> iterateZipSum(final List<Integer> as,
        final List<Integer> bs) {
        final List<Integer> zs = new ArrayList<Integer>();
        int i = 0;

        for (i = 0; i < as.size(); i++)
            zs.add(as.get(i) + (i < bs.size() ? bs.get(i) : 0));
        for (; i < bs.size(); i++)
            zs.add((i < as.size() ? as.get(i) : 0) + bs.get(i));
        return zs;
    }

    protected static List<Integer> filterEven(final List<Integer> l) {
        final List<Integer> result = new ArrayList<Integer>();

        for (final Integer i : l)
            if (i % 2 == 0)
                result.add(i);
        return result;
    }

    protected static Integer safeSum(final Integer a, final Integer b) {
        return (a == null ? 0 : a) + (b == null ? 0 : b);
    }

    public static List<Integer> getData(final boolean truncate,
        final Integer size) {
        final List<Integer> is = new ArrayList<Integer>(size);
        final Random r = new Random();

        for (Integer i = 0; i < size; i++)
            is.add(truncate ? r.nextInt() % 1000 : r.nextInt());
        return is;
    }

    public static List<String> getStringData(final Integer size) {
        final List<String> data = new ArrayList<String>(size);
        final Random r = new Random();

        for (Integer i = 0; i < size; i++)
            data.add(new Integer(r.nextInt()).toString());
        return data;
    }

    public static List<Integer> factorize(final Integer k) {
        final List<Integer> results = new ArrayList<Integer>();

        for (Integer i = 1; i < k; i++)
            if (k % i == 0)
                results.add(i);
        return results;
    }

    public static List<Integer> primeFactorize(final Integer k) {
        final List<Integer> results = new ArrayList<Integer>();

        for (final Integer i : factorize(k))
            if (isPrime(i))
                results.add(i);
        return results;
    }
}
