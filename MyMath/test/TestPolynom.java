
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.Messaging.SyncScopeHelper;

import myMath.Monom;
import myMath.Polynom;

class TestPolynom {

	static Polynom p1 = new Polynom("0");
	static Polynom p2 = new Polynom("-2x^3+7x^5");
	static Polynom p3 = new Polynom("5.2x^1-2.7x^4-4x^3+7");
	static Polynom p4 = new Polynom("2x^2-x+7");
	
	static Monom m1 = new Monom("0x^1");
	static Monom m2 = new Monom("-x");
	static Monom m3 = new Monom("2x^3");
	static Monom m4 = new Monom(4, 2);

	@AfterEach
	void tearDown() throws Exception {
		p1 = new Polynom("0");
		p2 = new Polynom("-2x^3+7x^5");
		p3 = new Polynom("5.2x^1-2.7x^4-4x^3+7");
		p4 = new Polynom("2x^2-x+7");

		m1 = new Monom("0x^1");
		m2 = new Monom("-x");
		m3 = new Monom("2x^3");
		m4 = new Monom(4, 2);
	}

	@Test
	void test_polynom_default_constructor() {
		Polynom p0 = new Polynom();
		assertEquals("", p0.toString());
	}

	@Test
	void test_polynom_string_constructor() {
		assertEquals("0", p1.toString());
		assertEquals("-2.0x^3+7.0x^5", p2.toString());
		assertEquals("7.0+5.2x-4.0x^3-2.7x^4", p3.toString());
		assertEquals("7.0-1.0x+2.0x^2", p4.toString());
	}

	@Test
	void test_polynom_add_monom() {
		p1.add(m1);
		assertEquals("0", p1.toString());
		p2.add(m2);
		assertEquals("-1.0x-2.0x^3+7.0x^5", p2.toString());
		p3.add(m3);
		assertEquals("7.0+5.2x-2.0x^3-2.7x^4", p3.toString());
		p4.add(m4);
		assertEquals("7.0-1.0x+6.0x^2", p4.toString());
	}

}
