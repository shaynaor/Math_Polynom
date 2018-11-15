package myMath;

public class Test_new {

	public static void main(String[] args) {
		Polynom m1 = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		PlotPolynom frame = new PlotPolynom(m1, -2, 6);

		frame.setVisible(true);

	}

}
