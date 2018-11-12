import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {

	static Monom m1 = new Monom("0x^1");
	static Monom m2 = new Monom("-x");
	static Monom m3 = new Monom("2x^3");
	static Monom m4 = new Monom(-4.5, 3);

	@AfterEach
	void tearDown() throws Exception {
		m1 = new Monom("0x^1");
		m2 = new Monom("-x");
		m3 = new Monom("2x^3");
		m4 = new Monom(-4.5, 3);
	}

	@Test
	void test_monom_default_constructor() {
		Monom actual = new Monom();
		Monom expected = new Monom(0, 0);
		assertEquals(expected.get_coefficient(), actual.get_coefficient());
		assertEquals(expected.get_power(), actual.get_power());
	}

	@Test
	void test_monom_constructor() {

		double coef = 0;
		int power = 0, counter = 50;
		while (counter > 0) {// check numbers with positive coefficient.
			Monom m = new Monom(coef, power);
			assertEquals(coef, m.get_coefficient());
			assertEquals(power, m.get_power());
			coef = Math.random() * 10;
			power = (int) Math.floor(Math.random() * 10);
			counter--;
		}
		coef = 0;
		power = 0;
		counter = 50;
		while (counter > 0) {// check numbers with negative coefficient.
			Monom m = new Monom(coef, power);
			assertEquals(coef, m.get_coefficient());
			assertEquals(power, m.get_power());
			coef = Math.random() * -10;
			power = (int) Math.floor(Math.random() * 10);
			counter--;
		}
	}

	@Test
	void test_monom_copy_constructor() {
		Monom copy1 = new Monom(m1);
		Monom copy2 = new Monom(m2);
		Monom copy3 = new Monom(m3);
		Monom copy4 = new Monom(m4);

		assertNotEquals(copy1, m1);
		assertNotEquals(copy2, m2);
		assertNotEquals(copy3, m3);
		assertNotEquals(copy4, m4);

		assertEquals(copy1.get_coefficient(), m1.get_coefficient());
		assertEquals(copy1.get_power(), m1.get_power());
		assertEquals(copy2.get_coefficient(), m2.get_coefficient());
		assertEquals(copy2.get_power(), m2.get_power());
		assertEquals(copy3.get_coefficient(), m3.get_coefficient());
		assertEquals(copy3.get_power(), m3.get_power());
		assertEquals(copy4.get_coefficient(), m4.get_coefficient());
		assertEquals(copy4.get_power(), m4.get_power());
	}

	@Test
	void test_monom_string_constructor() {
		assertEquals(0, m1.get_coefficient());
		assertEquals(0, m1.get_power());// if coefficient == 0 -----> power = 0.
		assertEquals(-1, m2.get_coefficient());
		assertEquals(1, m2.get_power());
		assertEquals(2, m3.get_coefficient());
		assertEquals(3, m3.get_power());
	}

	@Test
	void test_monom_add() {
		m1.add(m2);
		Monom result1 = new Monom(-1, 1);
		assertEquals(result1.get_coefficient(), m1.get_coefficient());
		assertEquals(result1.get_power(), m1.get_power());

		m3.add(m4);
		Monom result2 = new Monom(-2.5, 3);
		assertEquals(result2.get_coefficient(), m3.get_coefficient());
		assertEquals(result2.get_power(), m3.get_power());
	}

	@Test
	void test_monom_derivative() {
		Monom m1_deri = new Monom(0, 0);
		Monom m2_deri = new Monom(-1, 0);
		Monom m3_deri = new Monom("6x^2");
		Monom m4_deri = new Monom("-13.5x^2");

		assertEquals(m1_deri.get_coefficient(), m1.derivative().get_coefficient());
		assertEquals(m1_deri.get_power(), m1.derivative().get_power());
		assertEquals(m2_deri.get_coefficient(), m2.derivative().get_coefficient());
		assertEquals(m2_deri.get_power(), m2.derivative().get_power());
		assertEquals(m3_deri.get_coefficient(), m3.derivative().get_coefficient());
		assertEquals(m3_deri.get_power(), m3.derivative().get_power());
		assertEquals(m4_deri.get_coefficient(), m4.derivative().get_coefficient());
		assertEquals(m4_deri.get_power(), m4.derivative().get_power());
	}

	@Test
	void test_monom_f() {
		double result1 = 0;
		assertEquals(result1, m1.f(10));
		double result2 = -10;
		assertEquals(result2, m2.f(10));
		double result3 = 2000;
		assertEquals(result3, m3.f(10));
		double result4 = -4500;
		assertEquals(result4, m4.f(10));
	}

	@Test
	void test_monom_get_coefficient() {
		assertEquals(0, m1.get_coefficient());
		assertEquals(-1, m2.get_coefficient());
		assertEquals(2, m3.get_coefficient());
		assertEquals(-4.5, m4.get_coefficient());
	}

	@Test
	void test_monom_get_power() {
		assertEquals(0, m1.get_power());
		assertEquals(1, m2.get_power());
		assertEquals(3, m3.get_power());
		assertEquals(3, m4.get_power());
	}

	@Test
	void test_monom_multyply() {
		m1.multiply(m2);
		Monom result1 = new Monom(0, 0);
		assertEquals(result1.get_coefficient(), m1.get_coefficient());
		assertEquals(result1.get_power(), m1.get_power());

		m3.multiply(m4);
		Monom result2 = new Monom(-9, 6);
		assertEquals(result2.get_coefficient(), m3.get_coefficient());
		assertEquals(result2.get_power(), m3.get_power());
	}

	@Test
	void test_monom_subMonom() {
		m1.subMonom(m2);
		Monom result1 = new Monom(1, 1);
		assertEquals(result1.get_coefficient(), m1.get_coefficient());
		assertEquals(result1.get_power(), m1.get_power());

		m3.subMonom(m4);
		Monom result2 = new Monom(6.5, 3);
		assertEquals(result2.get_coefficient(), m3.get_coefficient());
		assertEquals(result2.get_power(), m3.get_power());
	}

}
