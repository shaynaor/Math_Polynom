package myMath;


public class Test_new {

	public static void main(String[] args) {
		Polynom m1 = new Polynom("x^2-2"); //bug 2x^2+6-3x^3
	
	
	  
		
		
		PlotPolynom frame = new PlotPolynom(m1,-10,10);
	
		frame.setVisible(true);
	
	}

}