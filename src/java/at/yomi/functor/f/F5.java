package at.yomi.functor.f;

/**
 * A transformation function of arity-5 from <code>A</code>, <code>B</code>, <code>C</code>,
 * <code>D</code> and <code>E</code> to <code>F$</code>. This type can be represented using
 * the Java 7 closure syntax.
 * 
 * @version 2.6<br>
 *          <ul>
 *          <li>$LastChangedRevision: 9595 $</li>
 *          <li>$LastChangedDate: 2008-04-16 14:47:05 +1000 (Wed, 16 Apr 2008) $</li>
 *          </ul>
 */
public interface F5<A,B,C,D,E,F$> {
	/**
	 * Transform <code>A</code>, <code>B</code>, <code>C</code>, <code>D</code> and
	 * <code>E</code> to <code>F$</code>.
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
	 * @return The result of the transformation.
	 */
	F$ f(A a, B b, C c, D d, E e);
}
