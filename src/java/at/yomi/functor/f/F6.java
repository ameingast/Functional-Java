package at.yomi.functor.f;

/**
 * A transformation function of arity-6 from <code>A</code>, <code>B</code>, <code>C</code>,
 * <code>D</code>, <code>E</code> and <code>F$</code> to <code>G</code>. This type can be
 * represented using the Java 7 closure syntax.
 * 
 * @version 2.6<br>
 *          <ul>
 *          <li>$LastChangedRevision: 9595 $</li>
 *          <li>$LastChangedDate: 2008-04-16 14:47:05 +1000 (Wed, 16 Apr 2008) $</li>
 *          </ul>
 */
public interface F6<A,B,C,D,E,F$,G> {
	/**
	 * Transform <code>A</code>, <code>B</code>, <code>C</code>, <code>D</code>,
	 * <code>E</code> and <code>F$</code> to <code>G</code>.
	 * 
	 * @param a
	 *            The <code>A</code> to transform.
	 * @param b
	 *            The <code>B</code> to transform.
	 * @param c
	 *            The <code>C</code> to transform.
	 * @param d
	 *            The <code>D</code> to transform.
	 * @param e
	 *            The <code>E</code> to transform.
	 * @param f
	 *            The <code>F$</code> to transform.
	 * @return The result of the transformation.
	 */
	G f(A a, B b, C c, D d, E e, F$ f);
}
