
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
	static Monom m4 = new Monom(-4.5, 3);

	@AfterEach
	void tearDown() throws Exception {
		p1 = new Polynom("0");
		p2 = new Polynom("-2x^3+7x^5");
		p3 = new Polynom("5.2x^1-2.7x^4-4x^3+7");
		p4 = new Polynom("2x^2-x+7");

		m1 = new Monom("0x^1");
		m2 = new Monom("-x");
		m3 = new Monom("2x^3");
		m4 = new Monom(-4.5, 3);
	}

	@Test
	void test_polynom_default_constructor() {
		Polynom p0 = new Polynom();
		assertEquals("", p0.toString());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
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
		Polynom result_1 = new Polynom("0");
		assertEquals(result_1.toString(), p1.toString());
		
		p2.add(m2);
		Polynom result_2 = new Polynom("-1.0x-2.0x^3+7.0x^5");
		assertEquals(result_2.toString(), p2.toString());
		p3.add(m3);
		Polynom result_3 = new Polynom("7.0+5.2x-2.0x^3-2.7x^4");
		assertEquals(result_3.toString(), p3.toString());
		p4.add(m4);
		Polynom result_4 = new Polynom("7.0-1.0x+2.0x^2-4.5x^3");
		assertEquals(result_4.toString(), p4.toString());
	}
	
	@Test
	void test_polynom_add_polynom() {
		p1.add(p2);
		Polynom result_1 = new Polynom("-2.0x^3+7.0x^5");
		assertEquals(result_1.toString(), p1.toString());
		
		p3.add(p4);
		Polynom result_2 = new Polynom("14.0+4.2x+2.0x^2-4.0x^3-2.7x^4");
		assertEquals(result_2.toString(), p3.toString());
		
	}
	
	@Test
	void test_polynom_area() {
		assertEquals(9,Math.round(p3.area(0, 1.223, 0.0001)));
		assertEquals(6, Math.round(p3.area(-1.428, 0, 0.0001)));
		assertEquals(15, Math.round(p4.area(-1, 1, 0.0001)));
	}
	
	@Test
	void test_polynom_copy() {
		Polynom p5 = new Polynom();
		p5 = (Polynom)p1.copy();
		assertNotEquals(p5, p1);
		assertEquals(p5.toString(), p1.toString());
		assertEquals(p5.f(30), p1.f(30));
		
		Polynom p6 = new Polynom();
		p6 = (Polynom)p2.copy();
		assertNotEquals(p6, p2);
		assertEquals(p6.toString(), p2.toString());
		assertEquals(p6.f(30), p2.f(30));
		
		Polynom p7 = new Polynom();
		p7 = (Polynom)p3.copy();
		assertNotEquals(p7, p3);
		assertEquals(p7.toString(), p3.toString());
		assertEquals(p7.f(30), p3.f(30));
	}
	
	@Test
	void test_polynom_derivative() {
		Polynom result_1 = new Polynom("0");
		assertEquals(result_1.toString(), p1.derivative().toString());
		
		Polynom result_2 = new Polynom("-6.0x^2+35x^4");
		assertEquals(result_2.toString(), p2.derivative().toString());
		
		Polynom result_3 = new Polynom("5.2-12.0x^2-10.8x^3");
		assertEquals(result_3.toString(), p3.derivative().toString());
		
		Polynom result_4 = new Polynom("-1.0+4x");
		assertEquals(result_4.toString(), p4.derivative().toString());
	}
	
	@Test
	void test_polynom_equals() {
		Polynom p5 = new Polynom();
		p5 = (Polynom)p1.copy();
		assertTrue(p1.equals(p5));
		
		Polynom p6 = new Polynom();
		p6 = (Polynom)p2.copy();
		assertTrue(p2.equals(p6));
		
		Polynom p7 = new Polynom();
		p7 = (Polynom)p3.copy();
		assertTrue(p3.equals(p7));
		
		Polynom p8 = new Polynom("7.0-1.0x+2.0x^2");
		assertTrue(p4.equals(p8));
	}
	
	@Test
	void test_polynom_f() {
		assertEquals(0, p1.f(10));
		assertEquals(208, p2.f(2));
		assertEquals(-1647, p2.f(-3));
		assertEquals(7, p3.f(0));
		assertEquals(28, p4.f(-3));
		assertEquals(7, p4.f(0));
	}
	
	@Test
	void test_polynom_isZero() {
		assertTrue(p1.isZero());
		assertTrue(!p2.isZero());
		assertTrue(!p3.isZero());
		assertTrue(!p4.isZero());
		p1.add(m4);
		assertTrue(!p1.isZero());
		
	}
	
	

}
