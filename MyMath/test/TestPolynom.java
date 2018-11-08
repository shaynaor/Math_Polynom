
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Polynom;

class TestPolynom {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_polynom_default_constructor() {
		Polynom p1 = new Polynom();
		assertEquals("", p1.toString());
	}
	
	@Test
	void test_polynom_string_constructor() {
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5");
		Polynom p4 = new Polynom("5.2x^1-2.7x^4-4x^3+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom p6 = new Polynom("-3.7+2x^2+4.1x^1");
		
		System.out.println(p6);
		assertEquals("0", p1.toString());
		assertEquals("0", p2.toString());
		assertEquals("-2.0x^3+7.0x^5", p3.toString());
		assertEquals("7.0+5.2x-4.0x^3-2.7x^4", p4.toString());
		assertEquals("7.0-1.0x^1+2.0x^2", p5.toString());
		assertEquals("-3.7+4.1x+2.0x^2", p6.toString());
		
	}
	

}
