import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {

	static Monom m1 = new Monom("0x^1");
	static Monom m2 = new Monom("-x");
	static Monom m3 = new Monom("2x^3");
	static Monom m4 = new Monom(4.5, 2);

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
		m1 = new Monom("0x^1");
		m2 = new Monom("-x");
		m3 = new Monom("2x^3");
		m4 = new Monom(4.5, 2);
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
		
	}

}
