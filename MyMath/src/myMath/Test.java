package myMath;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("hisdiffsdfsdfdfsdfsdfgdfg");
		System.out.println("***********------ Monom-Tests------**************");
		System.out.println();
		test_monom_default_constructor();
		test_monom_constructor();
		test_monom_copy_constructor();
		test_monom_string_constructor();
		test_monom_add();
		test_monom_derivative();
		test_monom_f();
		test_monom_get_coefficient();
		test_monom_get_power();
		test_monom_multyply();
		test_monom_subMonom();
		System.out.println("***********------ Polynom-Tests------**************");
		System.out.println();
		test_polynom_default_constructor();
		test_polynom_string_constructor();
		test_polynom_add_monom();
		test_polynom_add_polynom();
		test_polynom_area();
		test_polynom_copy();
		test_polynom_derivative();
		test_polynom_equals();
		test_polynom_f();
		test_polynom_isZero();
		test_polynom_multiply();
		test_polynom_root();
		test_polynom_substract();
	}

	public static void test_monom_default_constructor() {
		Monom m1 = new Monom();
		System.out.println("test_monom_default_constructor: Monom m1= " + m1);
		System.out.println();
	}

	public static void test_monom_constructor() {
		Monom m1 = new Monom(2.3, 3);
		Monom m2 = new Monom(-4, 0);
		Monom m3 = new Monom(0, 1);
		System.out.println("test_monom_constructor: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3);
		System.out.println();
	}

	public static void test_monom_copy_constructor() {
		Monom m1 = new Monom(2.3, 3);
		Monom m2 = new Monom(-4, 0);
		Monom copy_m1 = new Monom(m1);
		Monom copy_m2 = new Monom(m2);
		System.out.println("test_monom_copy_constructor: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom copy_m1= "
				+ copy_m1 + ", Monom copy_m2= " + copy_m2);
		System.out.println();
	}

	public static void test_monom_string_constructor() {
		Monom m1 = new Monom("0");
		Monom m2 = new Monom("0x^1");
		Monom m3 = new Monom("-x");
		Monom m4 = new Monom("2x^3");
		Monom m5 = new Monom("-2x^0");
		System.out.println("test_monom_string_constructor: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4 + ", Monom m5= " + m5);
		System.out.println();
	}

	public static void test_monom_add() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		System.out.println(
				"test_monom_add: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3 + ", Monom m4= " + m4);
		m1.add(m4);
		System.out.println("m1+m4= " + m1);
		m3.add(m2);
		System.out.println("m3+m2= " + m3);
		System.out.println();
	}

	public static void test_monom_derivative() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		System.out.println("test_monom_derivative: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4);
		System.out.println("Monom m1_derivative= " + m1.derivative() + ", Monom m2_derivative= " + m2.derivative()
				+ ", Monom m3_derivative= " + m3.derivative() + ", Monom m4_derivative= " + m4.derivative());
		System.out.println();
	}

	public static void test_monom_f() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		System.out.println(
				"test_monom_f: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3 + ", Monom m4= " + m4);
		System.out.println("Monom m1 f(3)=" + m1.f(3) + ", Monom m2 f(0)=" + m2.f(0) + ", Monom m3 f(3.2)=" + m3.f(3.2)
				+ ", Monom m4 f(4.5)=" + m4.f(4.5));
		System.out.println();
	}

	public static void test_monom_get_coefficient() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		System.out.println("test_monom_get_coefficient: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4);
		System.out.println("Monom m1 coefficient=" + m1.get_coefficient() + ", Monom m2 coefficient"
				+ m2.get_coefficient() + ", Monom coefficient=" + m3.get_coefficient() + ", Monom m4 coefficient="
				+ m4.get_coefficient());
		System.out.println();
	}

	public static void test_monom_get_power() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		System.out.println("test_monom_get_power: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4);
		System.out.println("Monom m1 power=" + m1.get_power() + ", Monom m2 power=" + m2.get_power()
				+ ", Monom m3 power=" + m3.get_power() + ", Monom m4 power=" + m4.get_power());
		System.out.println();
	}

	public static void test_monom_multyply() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^0");
		Monom m5 = new Monom(4, 2);
		Monom m6 = new Monom(-7, 5);
		System.out.println("test_monom_multyply: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4 + ", Monom m5= " + m5 + ", Monom m6= " + m6);
		m1.multiply(m2);
		m3.multiply(m4);
		m5.multiply(m6);
		System.out.println("m1*m2= " + m1 + ", m3*m4= " + m3 + ", m5*m6= " + m5);
		System.out.println();
	}

	public static void test_monom_subMonom() {
		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^3");
		Monom m5 = new Monom(4, 2);
		Monom m6 = new Monom(-7, 5);
		System.out.println("test_monom_subMonom: Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3
				+ ", Monom m4= " + m4 + ", Monom m5= " + m5 + ", Monom m6= " + m6);
		m1.subMonom(m2);
		m3.subMonom(m4);
		m5.subMonom(m6);
		System.out.println("m1-m2= " + m1 + ", m3-m4= " + m3 + ", m5-m6= " + m5);
		System.out.println();
	}

	public static void test_polynom_default_constructor() {
		Polynom p1 = new Polynom();
		System.out.println("test_polynom_default_constructor: Polynom p1= " + p1);
		System.out.println();
	}

	public static void test_polynom_string_constructor() {
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom p4 = new Polynom("5.2x^1-2.7x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom p6 = new Polynom("-3.7+2x^2+4.1x^6");

		System.out.println("test_polynom_string_constructor:");
		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println();
	}

	public static void test_polynom_add_monom() {
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom p4 = new Polynom("3.2x^1-2.7x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom p6 = new Polynom("-3.7+2x^2+4.1x^6");
		System.out.println("test_polynom_add_monom:");

		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println();

		Monom m1 = new Monom("0x^1");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("2x^3");
		Monom m4 = new Monom("-2x^3");
		Monom m5 = new Monom(4, 2);
		Monom m6 = new Monom(-7, 5);
		System.out.println("Monom m1= " + m1 + ", Monom m2= " + m2 + ", Monom m3= " + m3 + ", Monom m4= " + m4
				+ ", Monom m5= " + m5 + ", Monom m6= " + m6);
		System.out.println();
		p1.add(m1);
		System.out.println("Polynom p1 + Monom m1=" + p1);
		p2.add(m2);
		System.out.println("Polynom p2 + Monom m2=" + p2);
		p3.add(m3);
		System.out.println("Polynom p3 + Monom m3=" + p3);
		p4.add(m4);
		System.out.println("Polynom p4 + Monom m4=" + p4);
		p5.add(m5);
		System.out.println("Polynom p5 + Monom m5=" + p5);
		p6.add(m6);
		System.out.println("Polynom p6 + Monom m6=" + p6);
		System.out.println();
	}

	public static void test_polynom_add_polynom() {
		Polynom p1 = new Polynom("0");
		Polynom_able p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom_able p4 = new Polynom("3.2x^1-2.7x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom_able p6 = new Polynom("-3.7+2x^2+4.1x^6");
		System.out.println("test_polynom_add_monom:");

		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println();

		p1.add(p2);
		System.out.println("Polynom p1 + Polynom p2=" + p1);
		p3.add(p4);
		System.out.println("Polynom p3 + Polynom p4=" + p3);
		p5.add(p6);
		System.out.println("Polynom p5 + Polynom p6=" + p5);
		System.out.println();
	}

	public static void test_polynom_area() {
		System.out.println("test_polynom_area:");
		Polynom p1 = new Polynom();
		Monom m1 = new Monom(1, 4);
		Monom m2 = new Monom(-2, 2);
		Monom m3 = new Monom(2, 0);
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		System.out.println("Polynom p1=" + p1);
		System.out.println("the area between -2.5 to 2.5: " + p1.area(-2.5, 2.5, 0.001));
		System.out.println();
		Polynom p2 = new Polynom("2x^2+3x-5");
		System.out.println("Polynom p2=" + p2);
		System.out.println("the area between 1 to 2: " + p2.area(1, 2, 0.0001));
		System.out.println();
	}

	public static void test_polynom_copy() {
		System.out.println("test_polynom_copy:");
		Polynom p1 = new Polynom("2x^2+3x-5");
		System.out.println("Polynom p1=" + p1);
		Polynom_able p2 = p1.copy();
		System.out.println("Polynom (the copy of p1) p2=" + p1);
		p1.add(p2);
		System.out.println("p1 = p1 + p2 (if the deep copy works p1 need to chenge and p2 not)");
		System.out.println("after add operation- Polynom p1=" + p1);
		System.out.println("after add operation- Polynom p2=" + p2);
		System.out.println();
	}

	public static void test_polynom_derivative() {
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom p4 = new Polynom("3.2x^1-2.7x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom p6 = new Polynom("-3.7+2x^2+4.1x^6");
		System.out.println("test_polynom_derivative:");

		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p1_derivative=" + p1.derivative());
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p2_derivative=" + p2.derivative());
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p3_derivative=" + p3.derivative());
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p4_derivative=" + p4.derivative());
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p5_derivative=" + p5.derivative());
		System.out.println("Polynom p6=" + p6);
		System.out.println("Polynom p6_derivative=" + p6.derivative());
		System.out.println();
	}

	public static void test_polynom_equals() {
		Polynom p1 = new Polynom("0");
		Polynom_able p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom_able p4 = new Polynom("3.2x^1-2.7x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom_able p6 = new Polynom("2x^2-x+7");
		System.out.println("test_polynom_equals:");
		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("p1 equals to p2? " + p1.equals(p2));
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("p3 equals to p4? " + p3.equals(p4));
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println("p5 equals to p6? " + p5.equals(p6));
		System.out.println();
	}

	public static void test_polynom_f() {
		System.out.println("test_polynom_f:");
		Polynom p1 = new Polynom("2x^2+3x-5");
		System.out.println("f(x)= " + p1);
		System.out.println("f(0)= " + p1.f(0));
		System.out.println("f(1)= " + p1.f(1));
		System.out.println("f(2)= " + p1.f(2));
		System.out.println();
	}

	public static void test_polynom_isZero() {
		System.out.println("test_polynom_isZero:");
		Polynom p1 = new Polynom("0");
		Polynom p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");

		System.out.println("Polynom p1=" + p1);
		System.out.println("p1 is zero? " + p1.isZero());
		System.out.println("Polynom p2=" + p2);
		System.out.println("p2 is zero? " + p2.isZero());
		System.out.println("Polynom p3=" + p3);
		System.out.println("p3 is zero? " + p3.isZero());
		System.out.println();
	}

	public static void test_polynom_multiply() {
		Polynom p1 = new Polynom("0");
		Polynom_able p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom_able p4 = new Polynom("3x^1-2x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom_able p6 = new Polynom("-3+2x^2+4x^6");
		System.out.println("test_polynom_multiply:");

		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println();

		p1.multiply(p2);
		System.out.println("Polynom p1 * Polynom p2=" + p1);
		p3.multiply(p4);
		System.out.println("Polynom p3 * Polynom p4=" + p3);
		p5.multiply(p6);
		System.out.println("Polynom p5 * Polynom p6=" + p5);
		System.out.println();
	}

	public static void test_polynom_root() {
		System.out.println("test_polynom_root:");
		Polynom p1 = new Polynom("2x^2+3x-5");
		System.out.println("Polynom p1=" + p1);
		System.out.println("the root of the function is: " + p1.root(-4, -1, 0.01));
		Polynom p2 = new Polynom("2x^2-x-7");
		System.out.println("Polynom p2=" + p2);
		System.out.println("the root of the function is: " + p2.root(0, 5, 0.01));
	}

	public static void test_polynom_substract() {
		Polynom p1 = new Polynom("0");
		Polynom_able p2 = new Polynom("0x^3");
		Polynom p3 = new Polynom("-2x^3+7x^5+x-7");
		Polynom_able p4 = new Polynom("3x^1-2x^4-4x+7");
		Polynom p5 = new Polynom("2x^2-x+7");
		Polynom_able p6 = new Polynom("-3+2x^2+4x^6");
		System.out.println("test_polynom_substract:");

		System.out.println("Polynom p1=" + p1);
		System.out.println("Polynom p2=" + p2);
		System.out.println("Polynom p3=" + p3);
		System.out.println("Polynom p4=" + p4);
		System.out.println("Polynom p5=" + p5);
		System.out.println("Polynom p6=" + p6);
		System.out.println();

		p1.substract(p2);
		System.out.println("Polynom p1 - Polynom p2=" + p1);
		p3.substract(p4);
		System.out.println("Polynom p3 - Polynom p4=" + p3);
		p5.substract(p6);
		System.out.println("Polynom p5 - Polynom p6=" + p5);
		System.out.println();
	}
}
