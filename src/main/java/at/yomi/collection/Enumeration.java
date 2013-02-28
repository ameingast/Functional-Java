package at.yomi.collection;

import java.util.ArrayList;

public class Enumeration extends ArrayList<Integer> {
    private static final long serialVersionUID = 4011448197533545398L;

    public final Integer start;
    public final Integer end;

    public Enumeration(final Integer start, final Integer end) {
        super(end.intValue() - start.intValue());
        this.start = start;
        this.end = end;

        for (int i = start; i < end; i++)
            add(i);
    }
}
