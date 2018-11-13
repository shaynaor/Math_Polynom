
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (asummed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, subtract, value at x, derivative,
 * add and multiply.
 * 
 * @author Shay naor , Alex vaisman.
 *
 */
public class Monom implements function {

	/**
	 * Monom constructor- If the power is negative print error message. If the
	 * coefficient equals to zero sets the monom to: 0x^0. Else srts the monom to:
	 * ax^b.
	 * 
	 * @param a the coefficient.
	 * @param b the power.
	 */
	public Monom(double a, int b) {
		if (b < 0) // if the power is negative.
			throw new RuntimeException("Can not insert negative power!!");
		else if (a == 0) { // if the monom is'nt the zero monom.
			this.set_coefficient(0);
			this.set_power(0);
		} else {
			this.set_coefficient(a);
			this.set_power(b);
		}
	}

	/** Monom copy constructor. */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/** This function creates a deep copy of a monom. */
	public Monom copy() {
		Monom p = new Monom(this._coefficient, this._power);
		return p;
	}

	/**
	 * This function receives a string str of a monom (ax^b) when a is real, and b
	 * is a positive integer (neutral) number , and constructs a new monom.
	 * 
	 * @param str The string the function receives.
	 */
	public Monom(String str) {
		Monom m1 = new Monom();
		m1.init_from_string(str);
		if (m1.get_coefficient() == 0) {
			this.set_coefficient(0);
			this.set_power(0);
			return;
		}
		this.set_coefficient(m1.get_coefficient());
		this.set_power(m1.get_power());
	}

	/** Default constructor sets the monom to zero monom. */
	public Monom() {
		this.set_coefficient(0);
		this.set_power(0);
	}

	/** This function returns the values of y for a certain x. */
	public double f(double x) {
		return (this._coefficient * Math.pow(x, this._power));
	}

	/**
	 * Returns the value of 'a' from ax^b.
	 * 
	 * @return the coefficient.
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * Returns the value of 'b' from ax^b.
	 * 
	 * @return the power.
	 */
	public int get_power() {
		return this._power;
	}

	/**
	 * @param x for this point x the function calculaits the derivative.
	 * @return the deivative in the point x.
	 */
	public double derivative(int x) {
		if ((this._coefficient == 0) || (this._power == 0))
			return 0;
		else if (this._power == 1)
			return this._coefficient;
		else {
			Monom tag = new Monom((this._coefficient * this._power), (this._power - 1));
			return tag.f(x);
		}
	}

	/**
	 * This function return the derivative monom.
	 * 
	 * @return a new monom that derivative to this monom.
	 */
	public Monom derivative() {
		Monom deri = new Monom();
		if ((this._coefficient == 0) || (this._power == 0)) // if f(x)= 0x^b or f(x)= ax^0 ----> f'(x)= 0.
			return deri;
		else if (this._power == 1) { // if f(x)= ax ----> f'(x)= a.
			deri.set_coefficient(this._coefficient);
			return deri;
		} else { // if f(x)= ax^b ----> f'(x)= a*bx^b-1.
			deri.set_coefficient(this._coefficient * this._power);
			deri.set_power(this._power - 1);
			return deri;
		}
	}

	/**
	 * The function adds two monoms if the power is the same. Else the function
	 * prints an error messege.
	 * 
	 * @param m1 the monom that we want to add.
	 */
	public void add(Monom m1) {
		if (m1._coefficient == 0) // if try to add the zero monom.
			return;
		else if (this._coefficient == 0) {// if my monom is the zero monom.
			this.set_coefficient(m1._coefficient);
			this.set_power(m1._power);
		} else if (this._power != m1._power) // if the powers are diffrent.
			throw new RuntimeException("Cannot add monnoms with diffrent powers!" + "---> " + this + "+" + m1);
		else
			this.set_coefficient(this._coefficient + m1._coefficient);
	}

	/**
	 * This function multiplys two monoms.
	 * 
	 * @param m1 the monom that we multiplay with.
	 */
	public void multiply(Monom m1) {
		Monom temp = new Monom(this._coefficient * m1._coefficient, this._power + m1._power);
		this.set_coefficient(temp._coefficient);
		this.set_power(temp._power);
	}

	/**
	 * The function subtracts two monoms if the power is the same. Else the function
	 * prints an error messege.
	 * 
	 * @param m1 the monom that we want to subtract.
	 */
	public void subMonom(Monom m1) {
		if (m1._coefficient == 0)// if the monom that sent is the zero monom.
			return;
		else if (this._coefficient == 0) {// if my monom is the zero monom.
			this.set_coefficient((-1) * m1._coefficient);
			this.set_power(m1._power);
		} else if (this._power != m1._power) // if the powers are diffrent.
			throw new RuntimeException("Cannot substract monnoms whith diffrent powers! ---> " + this + "-" + m1);
		else
			this.set_coefficient(this._coefficient - m1._coefficient);
	}

	/** Prints the monom as "ax^b". */
	public String toString() {
		String s = "";
		if (this._coefficient == 0) { // if monom equals to 0 return 0
			return "0";
		}
		if (this.get_power() == 0) { // if power equals to 0 return a
			return s += +this.get_coefficient();
		}
		if (this.get_power() == 1) {
			return s += +this.get_coefficient() + "x";
		} else { // else return string ax^b
			return s += +this.get_coefficient() + "x^" + this.get_power();
		}
	}

	// ****************** Private Methods and Data *****************
	private double _coefficient;
	private int _power;

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		this._power = p;
	}

	/**
	 * This function receives a string s of a monom (ax^b) when a is real, and b is
	 * a positive integer (neutral) number , and constructs a new monom depending on
	 * the case. this function return the new monom to the public function.
	 * 
	 * @param s The string the function receives.
	 * @return The new monom from the string.
	 */
	private Monom init_from_string(String s) {
		Monom m1 = new Monom();
		String dub = "";
		String into = "";
		int length = s.length();
		double a=0;
		int b=0;
		int i = 0;
        s.toLowerCase();
        String errorMessege="This format is not accepted, Correct format is: ax^b where a is double and b is a positive int\n"
        + "You can only use char 'x' as a variable\n"+"You can only use char '^' as a power\n"
        +"No Spaces Allowed!\nString cannot be empty!\n"+"EXAMPLES FOR CORRECT FORMAT:\n"+"Monom: 2x^12, -2x^12, +2x^12\n"+
        "Polynom: 2x^12+31x^2-x^3+x+2\n\n\n";
        
        
        if(s.length()==0) {
        	System.err.println("The string cannot be empty, input a value please.\n");
			throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
        }
        
        
		int index = s.indexOf('x');
		if (index < 0) { // if its just a number example: (12)
			try {
			a = Double.parseDouble(s);
			}
			catch(Exception e) {
				System.err.println(errorMessege);
				throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
			}
			b = 0;
			this.set_coefficient(a);
			this.set_power(b);
			return m1;
		} else {
			if (length == 1&&s.contains("x")) { // if its just "x"
				b = 1;
				a = 1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
			if (length == 2 && s.charAt(0) =='-'&&s.charAt(1)=='x') { // if its "-x"
				b = 1;
				a = -1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
			if (length == 2 && s.charAt(0) == '+'&&s.charAt(1)=='x') { // if its "+x"
				b = 1;
				a = 1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
			if (s.charAt(0) == 'x' && s.length() > 1) { // if its "x^b"
				i = 2;
				while (i < s.length()) {
					into += s.charAt(i);
					i++;
				}
				try {
					b = Integer.parseInt(into);
				}
				catch(Exception e) {
					System.err.println(errorMessege);
					throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
				}
				if(b<0) {
					throw new RuntimeException("THE POWER CAN'T BE A NEGATIVE NUMBER!");
				}
				a = 1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
			if (s.charAt(0) == '-' &&s.charAt(1) == 'x'&&s.length() > 1 && s.charAt(1) == 'x') { // if its "-x^b"
				i = 3;
				while (i < s.length()) {
					into += s.charAt(i);
					i++;
				}
				try {
					b = Integer.parseInt(into);
				}
				catch(Exception e) {
					System.err.println(errorMessege);
					throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
				}
				if(b<0) {
					throw new RuntimeException("THE POWER CAN'T BE A NEGATIVE NUMBER!");
				}
				a = -1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
			
			if (s.charAt(0) == '+' &&s.charAt(1) == 'x' && s.length() > 1 && s.charAt(1) == 'x') { // if its "+x^b"
				i = 3;
				while (i < s.length()) {
					into += s.charAt(i);
					i++;
				}
				try {
					b = Integer.parseInt(into);
				}
				catch(Exception e) {
					System.err.println(errorMessege);
					throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
				}
				if(b<0) {
					throw new RuntimeException("THE POWER CAN'T BE A NEGATIVE NUMBER!");
				}
				a = 1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}

			while (s.charAt(i) != 'x') {
				dub += s.charAt(i);
				i++;
			}
			
			try {
				a = Double.parseDouble(dub);
			}
			catch(Exception e) {
				System.err.println(errorMessege);
				throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
			}
			
			
			index = s.indexOf('^');
			if (index < 0) { // if its a number with ax example:(12x)
				b = 1;
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			} else { // if its a number with ax^b example:(12x^2)
				i = index + 1;
				while (i < length) {
					into += s.charAt(i);
					i++;
				}
				try {
					b = Integer.parseInt(into);
				}
				catch(Exception e) {
					System.err.println(errorMessege);
					throw new RuntimeException("READ THE README FILE FOR MORE INFORMATION ON INPUT");
				}
				if(b<0) {
					throw new RuntimeException("THE POWER CAN'T BE A NEGATIVE NUMBER!");
				}
				
				this.set_coefficient(a);
				this.set_power(b);
				return m1;
			}
		}
	}
}
