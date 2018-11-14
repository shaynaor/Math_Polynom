package myMath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.text.Format;
import java.util.Formatter;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PlotArea;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.legends.Legend;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class PlotPolynom extends JFrame {

	public PlotPolynom(Polynom m1,double x1,double x2) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);                         // Size of the display window
		setSize(800, 600);

		double minx = find_Min(m1,x1,x2);                             
		double maxx = find_Max(m1,x1,x2);
		DataTable data = new DataTable(Double.class, Double.class);
		DataTable minpoint = new DataTable(Double.class, Double.class);  
		DataTable maxpoint = new DataTable(Double.class, Double.class);  

		if(minx!=Double.NEGATIVE_INFINITY) {
			
			minpoint.add(minx,m1.f(minx));
			
		}
		if(maxx!=Double.NEGATIVE_INFINITY) {
			maxpoint.add(maxx,m1.f(maxx));
		}



		for (double x = x1; x <= x2; x+=0.25) {                      // creating data set from polynom m1
			double y = m1.f(x);
			data.add(x, y); 

		}

		XYPlot plot = new XYPlot(data,minpoint,maxpoint);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

		PointRenderer pointRenderer = plot.getPointRenderers(data).get(0);        // point rendering
		pointRenderer.setShape(new Ellipse2D.Double(0.1, -0.1, 0.1, 0.1));

		Color colorline = new Color(0, 0, 1.0f, 1.0f);                               //line color -- blue
		Color colormin = new Color(0, 0, 0, 1.0f);                                  // point min color --  black
		Color colormax = new Color(0, 1.0f, 0, 1.0f);                                //point max color -- green

		plot.getLineRenderers(data).get(0).setColor(colorline);
		plot.getTitle().setText("Polynom: "+m1.toString());                        //title
		plot.getPlotArea().setBorderColor(new Color(0.0f, 0.2f, 0.8f));
		plot.getPlotArea().setBorderStroke(new BasicStroke(2f));

		
		
		                                             



		PointRenderer pointmin = plot.getPointRenderers(minpoint).get(0);         //min point setting
		pointmin.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));             // point style
		pointmin.setColor(colormin);
		
		

		
		PointRenderer pointmax = plot.getPointRenderers(maxpoint).get(0);         //max point setting
		pointmax.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));            // point style
		pointmax.setColor(colormax);
	
		
		
	}




	/**
	 * this function receives a polynomial and a limitation from x1 to x2 and finds minimal point .
	 * x1 must be smaller then x2. x1<x2.
	 * @param m1 the polynomial 
	 * @param x1 from this point
	 * @param x2 to this point
	 * @return
	 */
	public double find_Min(Polynom m1,double x1,double x2) {
		Polynom zero= new Polynom("0");
		if(m1.derivative().derivative().equals(zero)) {
			return Double.NEGATIVE_INFINITY;
		}

		Polynom m = new Polynom();
		m.add(m1.derivative());
		Double checkmin;

		

		if(m.f(x1)*m.f(x2)>0) {
			return Double.NEGATIVE_INFINITY;
		}

		checkmin=m.root(x1, x2,0.01 );
		if(m1.f(checkmin)<m1.f(checkmin+0.01)&&m1.f(checkmin)<m1.f(checkmin-0.01)) {
			return checkmin;
		} 


		return Double.NEGATIVE_INFINITY;
	}


	/**
	 * this function receives a polynomial and a limitation from x1 to x2 and finds minimal point .
	 * x1 must be smaller then x2. x1<x2.
	 * @param m1 the polynomial 
	 * @param x1 from this point
	 * @param x2 to this point
	 * @return
	 */
	public double find_Max(Polynom m1,double x1,double x2) {
		Polynom zero= new Polynom("0");
		if(m1.derivative().derivative().equals(zero)) {
			return Double.NEGATIVE_INFINITY;
		}
		Polynom m = new Polynom();
		m.add(m1.derivative());
		Double checkmax;

		if(m.f(x1)*m.f(x2)>0) {
			return Double.NEGATIVE_INFINITY;
		}

		checkmax=m.root(x1, x2,0.01 );
		if(m1.f(checkmax)>m1.f(checkmax+0.01)&&m1.f(checkmax)>m1.f(checkmax-0.01)) {
			return checkmax;
		} 


		return Double.NEGATIVE_INFINITY;
	}




}