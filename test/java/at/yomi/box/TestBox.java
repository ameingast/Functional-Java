package at.yomi.box;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.box.Box;
import at.yomi.box.EmptyBox;
import at.yomi.box.FullBox;

public class TestBox {
	@Test
	public void testOpenBox() {
		Box<String> fullBox = new FullBox<String>("foo");
		Box<String> emptyBox = new EmptyBox<String>();

		assertEquals("foo", fullBox.openOr("bar"));
		assertEquals("bar", emptyBox.openOr("bar"));

	}
}
