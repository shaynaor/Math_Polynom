package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * This class represents a Polynom with add, subtract, multiply functionality,
 * it also supports the following: 1. Riemann's
 * Integral:https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a
 * numerical value between two values (currently support root only f(x)=0). 3.
 * Derivative
 * 
 * @author Shay naor , Alex vaisman.
 *
 */
public class Polynom implements Polynom_able {

	/** A default constructor that initialize an empty array list. */
	public Polynom() {
		poly = new ArrayList<>();
	}

	/**
	 * This function receives a string str of a polynom and constructs a new
	 * polynom.
	 * 
	 * @param str The string the function receives.
	 */
	public Polynom(String str) {
		this.poly = new ArrayList<>();
		Polynom ans = new Polynom();
		ans.init_from_string(str);
		this.add(ans);
	}

	/**
	 * This function adds a monom to the polynom. If the monom equals to zero- and
	 * the polynom is empty the function will add the zero monom else it does
	 * nothing. . If the power of the monom equles to an existing monom , the
	 * function will add them together. else the function adds the monom to the
	 * polynom array. In the end the function sort the polynom by powers.
	 * 
	 * @param m1 the monom that we add.
	 */
	public void add(Monom m1) {
		Iterator<Monom> it = this.poly.iterator();
		Monom hold = new Monom();
		Monom temp = new Monom();
		if (m1.get_coefficient() == 0) {// if we want to add the zero mono.
			if (this.poly.size() == 0)// add the zero monom only if the polynom is empty.
				this.poly.add(m1);
			return;
		}
		if (this.poly.size() == 1) {// if the the polynom contains just the zero polynom clear and add.
			if (this.poly.get(0).get_coefficient() == 0)
				this.poly.clear();
		}
		while (it.hasNext()) {
			hold = it.next();
			temp = hold.copy();
			if (temp.get_power() == m1.get_power()) {
				temp.add(m1);
				it.remove();
				this.poly.add(temp);
				this.poly.sort(monomcmp);
				this.removeZero();
				return;
			}
		}
		this.poly.add(m1);
		this.poly.sort(monomcmp);
		this.removeZero();
	}

	/**
	 * This function gets adds two polynoms togther.
	 * 
	 * @param p1 the polynom that we want to add.
	 */
	public void add(Polynom_able p1) {
		Polynom_able p = new Polynom();
		p = p1;
		Iterator<Monom> it = p.iteretor();
		while (it.hasNext()) {
			Monom temp = it.next();
			this.add(temp);
		}
		this.removeZero();
	}

	/** This function returns the values of y for a certain x. */
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> it = poly.iterator();
		while (it.hasNext()) {
			Monom m = it.next();
			ans = ans + m.f(x);
		}
		return ans;
	}

	/**
	 * This function subtract between two polynoms.
	 * 
	 * @param p1 the polynom that we want to subtract whith.
	 */
	public void substract(Polynom_able p1) {
		Polynom_able p = new Polynom();
		p = p1;
		Iterator<Monom> it = p.iteretor();
		while (it.hasNext()) {
			Monom temp = it.next();
			this.sub(temp);
		}
		this.removeZero();
	}

	/**
	 * This function receives a polynomial p1 and multiplies it with (this). If one
	 * of the polynomial is empty the function will add the second polynomial to the
	 * empty one. If one of the polynomial equals to zero, the function will return
	 * zero. Else the function will multiply the monomial of each polynomial in
	 * mathematical order , put the answer in a new polynomial and deep copy it into
	 * (this).
	 */
	public void multiply(Polynom_able p1) {
		Polynom ans = new Polynom();
		Iterator<Monom> p1t = p1.iteretor();
		Iterator<Monom> thist = this.iteretor();
		Monom temp = new Monom();
		Monom p1temp = new Monom();
		Monom zero = new Monom(0, 0);
		if (this.poly.isEmpty()) { // If (this) polynom is empty then add the p1 to it.
			this.add(p1);
			return;
		}
		if (!(this.poly.isEmpty())) { // If (this) polynom is not empty but p1 is empty do nothing.
			if (!p1t.hasNext()) {
				return;
			}
		}
		if (p1.isZero() || this.isZero()) { // if one of the polynoms is zero return polynom zero.
			this.poly.clear();
			this.add(zero);
			return;
		}
		if (thist.hasNext() && p1t.hasNext()) {
			while (thist.hasNext()) {
				Monom x = thist.next();
				temp = x.copy();
				Iterator<Monom> p1t2 = p1.iteretor();
				while (p1t2.hasNext()) {
					temp = x.copy();
					Monom y = p1t2.next();
					p1temp = y.copy();
					temp.multiply(p1temp);
					ans.add(temp);
					p1temp = zero;
					temp = zero;
				}
			}
		}
		this.poly.clear();
		ans.poly.sort(monomcmp);
		Iterator<Monom> anst = ans.iteretor();
		while (anst.hasNext()) {
			this.add(anst.next());
		}
	}

	/**
	 * This function test if this Polynom is logically equals to p1.
	 * 
	 * @param p1 the polynom that we compere with.
	 * @return true if this polynom represents the same function ans p1.
	 */
	public boolean equals(Polynom_able p1) {
		if (size(p1) != this.poly.size())
			return false;
		Iterator<Monom> p1It = p1.iteretor();
		Iterator<Monom> thisIt = this.iteretor();
		while (p1It.hasNext() && thisIt.hasNext()) {
			Monom m1, m2 = new Monom();
			m1 = thisIt.next();
			m2 = p1It.next();
			if ((m1.get_coefficient() != m2.get_coefficient()) || (m1.get_power() != m2.get_power()))
				return false;// if m1 and m2 are diffrent return false.
		}
		return true;
	}

	/** This function returns true if this polynom contains only the zero monom. */
	public boolean isZero() {
		if (poly.size() == 1) {
			if (poly.get(0).get_coefficient() == 0)
				return true;
		}
		return false;
	}

	/**
	 * The function root finds a solution to the function f(x)=0 between 2 points.
	 * The algorithim taken from:
	 * https://www.geeksforgeeks.org/program-for-bisection-method/
	 * 
	 * @param x0  starting point.
	 * @param x1  end point.
	 * @param eps the epsilon defines the accuracy amount of the function
	 * @return the x solution to f(x)=0.
	 */
	public double root(double x0, double x1, double eps) {
		if (f(x0) * f(x1) > 0 || this.isZero())
			throw new RuntimeException("Error: f(x0) and f(x1) must be from the oposite side of x-line.");
		if (Math.abs(f(x0)) < eps)
			return x0;
		if (Math.abs(f(x1)) < eps)
			return x1;
		double temp = 0;
		if (x0 > x1) {
			temp = x0;
			x0 = x1;
			x1 = temp;
		}
		double mid = 0;
		while ((x1 - x0) >= eps) {
			mid = (x0 + x1) / 2; // find the middle point.
			if (this.f(mid) == 0) // check if the middle point is the root.
				return mid;
			else if ((this.f(mid) * this.f(x0)) < 0)
				x1 = mid;
			else
				x0 = mid;
		}
		return mid;
	}

	/** This function creates a deep copy of the object. */
	public Polynom_able copy() {
		Polynom_able p = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			p.add(it.next());
		}
		return p;
	}

	/**
	 * This function return the derivative polynom.
	 * 
	 * @return A new polynom that derivative to this polynom.
	 */
	public Polynom_able derivative() {
		Polynom_able deri = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) {
			deri.add(it.next().derivative());
		}
		return deri;
	}

	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using
	 * eps size steps.
	 * 
	 * @return the approximated area above the x-axis below this Polynom and between
	 *         the [x0,x1] range.
	 */
	public double area(double x0, double x1, double eps) {
		if (x1 <= x0)
			return 0;
		double area = 0;// area will sum the area of the rectangels.
		double temp = 0; // and temp check if the area above the x-axis.
		double pos = x0 + eps;// represent the current possition on the x-axis.
		while (pos < x1) {
			temp = eps * this.f(pos);
			if (temp >= 0)
				area = area + eps * this.f(pos);
			pos = pos + eps;
		}
		return area;
	}

	/** Iterator function. */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = poly.iterator();
		return it;
	}

	/** Prints the polynom as: a0+a1x+a2x^2+...... +anx^n . */
	public String toString() {
		String ans = "";
		Iterator<Monom> it = poly.iterator();
		while (it.hasNext()) {
			Monom s = it.next();
			if (ans == "") { // if its the first monom add it
				if (s.get_coefficient() == 0) {
					ans += "0";
					continue;
				}
				ans += s.toString();
				continue;
			}

			if (s.get_coefficient() >= 0) { // if its bigger then or equals to 0 and " + ".
				ans += "+" + s.toString();
			} else // if its smaller then 0 add " - "
			{
				if (s.get_power() == 1)
					ans += "-" + Math.abs(s.get_coefficient()) + "x";
				else
					ans += "-" + Math.abs(s.get_coefficient()) + "x^" + s.get_power();

			}
		}
		return ans;
	}

	// ******* Private Methods and Data *************
	private ArrayList<Monom> poly;
	private final Monom_Comperator monomcmp = new Monom_Comperator();

	/**
	 * This function substract a monom from polynom. If the monom equals to zero-
	 * and the polynom is empty the function will add the zero monom else it does
	 * nothing. . If the power of the monom equles to an existing monom , the
	 * function will substract between them. Else the function substract the monom
	 * to the polynom array. In the end the function sort the polynom by powers.
	 * 
	 * @param m1 the monom that we add.
	 */
	private void sub(Monom m1) {
		Monom m = new Monom();
		if (m1.get_coefficient() == 0) {
			if (poly.size() == 0) {
				m.subMonom(m1);
				poly.add(m);
			}
			return;
		}
		Iterator<Monom> it = poly.iterator();
		while (it.hasNext()) {
			Monom temp = it.next();
			if (temp.get_power() == m1.get_power()) {
				temp.subMonom(m1);
				int indexh = poly.indexOf(temp);
				poly.set(indexh, temp);
				return;
			}

		}
		m.subMonom(m1);
		poly.add(m);
		poly.sort(monomcmp);
	}

	/**
	 * This function moving over the polynom and check: a. If this is the
	 * zeropolynom - do nothing. b. If the polynom contains more then one monom and
	 * the zero monom once of them remove him.
	 */
	private void removeZero() {
		Monom zero = new Monom(0, 0);
		Iterator<Monom> t = this.iteretor();
		if (this.poly.size() > 1) {
			while (t.hasNext()) {
				Monom check = t.next();
				if (check.get_coefficient() == 0)
					t.remove();
			}
			if (poly.size() == 0)
				this.add(zero);
		}
	}

	/**
	 * This function gets polynom_able and calculate how much monoms in the polynom.
	 * 
	 * @param p1 the polynom_able that the function gets.
	 * @return how much monoms in the polynom
	 */
	private int size(Polynom_able p1) {
		int counter = 0;
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			it.next();
			counter++;
		}
		return counter;
	}

	/**
	 * This function receives a string s of a polynom and return a new polynom. This
	 * function will count how many monoms the string s has, and depending on the
	 * case if the first polynom is positive or negative it will add each monom to a
	 * new polynom p. when the function finishes the string, it will add the polynom
	 * p to the the new polynom (this).
	 *
	 * @param s The string of a polynom we receive.
	 */
	private void init_from_string(String s) {
		Polynom p = new Polynom();
		int monoms = 0;
		int index = 0;
		String sign = "";
		String ans = "";
		String hold = "";
		s.toLowerCase();
		if(s.length()==0) {
			System.err.println("The string cannot be empty, input a value please.\n");
			throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
		}

		if (s.charAt(0) == '-') { // if the polynom starts with -
			while (index < s.length()) {
				if (s.charAt(index) == '+' || s.charAt(index) == '-') { // counting the number of monoms
					monoms++;

				}
				index++;
			}
			index = 0;

			for (int i = 0; i < monoms; i++) { // the for loop runs till it reaches the number of monoms
				while (index < s.length()) { // and when the monom is full then function sends it to monom(string)

					if (index > 1 && s.charAt(index - 1) == '-' && ans == "") {
						sign = "-";
					}
					if (index > 1 && s.charAt(index - 1) == '+' && ans == "") {
						sign = "+";
					}
					ans += s.charAt(index);
					if ((s.charAt(index) == '+' || s.charAt(index) == '-') && (ans.length() > 1)) {
						hold = sign + ans.substring(0, ans.length() - 1);
						Monom m = new Monom(hold);
						p.add(m);
						ans = "";
						hold = "";
					}
					index++;
				}
			}
			sign += ans;
			Monom m = new Monom(sign);
			p.add(m);
			sign = "";
			ans = "";
			this.add(p);
		} else { // the function starts with + logicly equals to ""
			monoms = 1;
			while (index < s.length()) {
				if (s.charAt(index) == '+' || s.charAt(index) == '-') { // counting the number of monoms
					monoms++;
				}
				index++;
			}
			index = 0;

			for (int i = 0; i < monoms; i++) { // the for loop runs till it reaches the number of monoms
				while (index < s.length()) { // and when the monom is full then function sends it to monom(string)

					if (index > 1 && s.charAt(index - 1) == '-' && ans == "") {
						sign = "-";
					}
					if (index > 1 && s.charAt(index - 1) == '+' && ans == "") {
						sign = "+";
					}
					ans += s.charAt(index);
					if ((s.charAt(index) == '+' || s.charAt(index) == '-') && (ans.length() > 1)) {
						hold = sign + ans.substring(0, ans.length() - 1);
						Monom m = new Monom(hold);
						p.add(m);
						ans = "";
						hold = "";
					}
					index++;
				}
			}
			sign += ans;
			Monom m = new Monom(sign);
			p.add(m);
			sign = "";
			ans = "";
			this.add(p);
		}
	}
}