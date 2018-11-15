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

/**
 * This class given a polynom and a certain area from two point on the x axis,
 * will render a graph of the function find the min and max values of the
 * function in that area if they exist. calculate the area confined by the
 * function and the x axis. And present it in a graphical user interface.
 * 
 * 
 * We used gral for the presentation of the function in graphical user interface
 * , link: https://github.com/eseifert/gral
 * 
 * @author Shay naor , Alex vaisman.
 *
 */

public class PlotPolynom extends JFrame {

	/**
	 * This function given a polynom and a certain area from x1 to x2 will draw the
	 * polynom find * min and max points if they exist and the area confined between
	 * the function and the x axis.
	 * 
	 * @param m1 The polynom the function receive
	 * @param x1 from this point to
	 * @param x2 this point
	 */

	public PlotPolynom(Polynom p1, double x1, double x2) {
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Size of the display window
		setSize(800, 600);

		double root = 0;
		double minx = Double.NEGATIVE_INFINITY;
		double maxx = Double.NEGATIVE_INFINITY;
		double miny = Double.POSITIVE_INFINITY;
		double maxy = Double.NEGATIVE_INFINITY;
		Polynom p1Tag = new Polynom();
		Polynom p1TagTag = new Polynom();
		p1Tag.add(p1.derivative());
		p1TagTag.add(p1Tag.derivative());
		DataTable data = new DataTable(Double.class, Double.class); // data of normal points
		DataTable minpoint = new DataTable(Double.class, Double.class); // data of min point
		DataTable maxpoint = new DataTable(Double.class, Double.class); // data of max point

		for (double x = x1; x <= x2; x += 0.15) { // finding min and max point on graph
			double y = p1.f(x);
			if (p1.f(x) < miny) {
				miny = p1.f(x);
			}
			if (p1.f(x) > maxy) {
				maxy = p1.f(x);
			}
		}
		if (miny < 0.001 && miny > -0.001) { // if min or max almost 0 make them 0
			miny = 0;
		}
		if (minx < 0.001 && minx > -0.001) {
			minx = 0;
		}

		for (double x = x1; x <= x2; x += 0.15) { // creating data set from polynom m1
			double y = p1.f(x);
			data.add(x, y);

			if (p1Tag.f(x) < 0 && p1Tag.f(x + 0.2) > 0 || p1Tag.f(x) > 0 && p1Tag.f(x + 0.2) < 0) {
				root = p1Tag.root(x, x + 0.2, 0.0001);

				if (p1TagTag.f(root) > 0 && p1.f(root) - 0.01 <= miny) { // min

					minpoint.add(root, p1.f(root));
					minx = root;
				}

				if (p1TagTag.f(root) < 0 && p1.f(root) + 0.01 >= maxy) { // max
					maxpoint.add(root, p1.f(root));
					maxx = root;
				}
			}
		}

		if (minx < 0.001 && minx > -0.001) { // if min or max almost 0 make them 0
			minx = 0;
		}
		if (maxx < 0.001 && maxx > -0.001) {
			maxx = 0;
		}

		XYPlot plot = new XYPlot(data, minpoint, maxpoint); // rendering points and lines from data
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);

		PointRenderer pointRenderer = plot.getPointRenderers(data).get(0); // point rendering
		pointRenderer.setShape(new Ellipse2D.Double(0.1, -0.1, 0.1, 0.1));

		Color colorline = new Color(0, 0, 1.0f, 1.0f); // line color -- blue
		Color colormin = new Color(0, 0, 0, 1.0f); // point min color -- black
		Color colormax = new Color(0, 0, 255); // point max color -- blue

		plot.getLineRenderers(data).get(0).setColor(colorline);
		// title
		if (minx == Double.NEGATIVE_INFINITY && maxx == Double.NEGATIVE_INFINITY) { // no min max points
			plot.getTitle().setText("Polynom: " + p1.toString());
		}

		if (minx != Double.NEGATIVE_INFINITY && maxx == Double.NEGATIVE_INFINITY) { // min point
			plot.getTitle().setText("Polynom: " + p1.toString() + "\nMin: (" + minx + ", " + p1.f(minx) + ")\n"
					+ "Area between The graph and x axis from " + x1 + " to " + x2 + ": " + p1.area(x1, x2, 0.01));
		}
		if (minx == Double.NEGATIVE_INFINITY && maxx != Double.NEGATIVE_INFINITY) { // max point
			plot.getTitle().setText("Polynom: " + p1.toString() + "\nMax: (" + maxx + ", " + p1.f(maxx) + ")\n"
					+ "Area between The graph and x axis from " + x1 + " to " + x2 + ": " + p1.area(x1, x2, 0.01));
		}
		if (minx != Double.NEGATIVE_INFINITY && maxx != Double.NEGATIVE_INFINITY) { // max and min point
			plot.getTitle()
					.setText("Polynom: " + p1.toString() + "\nMax: (" + maxx + ", " + p1.f(maxx) + ")" + "\nMin: ("
							+ minx + ", " + p1.f(minx) + ")\n" + "Area between The graph and x axis from " + x1 + " to "
							+ x2 + ": " + p1.area(x1, x2, 0.01));
		}

		plot.getPlotArea().setBorderColor(new Color(0.0f, 0.2f, 0.8f)); // border style/color
		plot.getPlotArea().setBorderStroke(new BasicStroke(2f));

		PointRenderer pointmin = plot.getPointRenderers(minpoint).get(0); // min point setting
		pointmin.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0)); // point style
		pointmin.setColor(colormin);

		PointRenderer pointmax = plot.getPointRenderers(maxpoint).get(0); // max point setting
		pointmax.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0)); // point style
		pointmax.setColor(colormax);

	}
}