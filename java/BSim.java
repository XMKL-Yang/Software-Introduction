package assignment4;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.gui.TableLayout;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;


public class bSim extends GraphicsProgram implements ChangeListener, ItemListener{




	// Parameters used in this program
	private static final int WIDTH = 1200; // n.b. screen coordinates
	private static final int HEIGHT = 600;
	private static final int OFFSET = 200;
	private static int NUMBALLS = 15; // # balls to simulate
	private static final double MINSIZE = 1; // Minumum ball size
	private static final double MAXSIZE = 8; // Maximum ball size
	private static int XMIN = 10; // Min X starting location
	private static final double XMAX = 50; // Max X starting location
	private static final double YMIN = 50; // Min Y starting location
	private static final double YMAX = 100; // Max Y starting location
	private static final double EMIN = 0.4; // Minimum loss coefficient
	private static final double EMAX = 0.9; // Maximum loss coefficient
	private static final double VMIN = 0.5; // Minimum X velocity
	private static final double VMAX = 3.0; // Maximum Y velocity



	private bTree myTree;
	
	
	public static void main(String[] args) { // Standalone Applet
		new bSim().start(args);
	}
	// int PS_NumBalls;
	//int minSizeVal;

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (source==numballsSlider.mySlider) {
			NUMBALLS=numballsSlider.getISlider();
			numballsSlider.setISlider(NUMBALLS);
		}
		else if( source== minSizeSlider.mySlider)
		{
			XMIN = minSizeSlider.getISlider();
			minSizeSlider.setISlider(XMIN);
		}
	}
	sliderBox numballsSlider;
	sliderBox minSizeSlider;
	JComboBox<String> bSimC;
	void setChoosers() {
		bSimC = new JComboBox<String>();
		bSimC.addItem("bSim");
		bSimC.addItem("Run");
		bSimC.addItem("Clear");
		bSimC.addItem("Stop");
		bSimC.addItem("Quit");
		add(bSimC,NORTH);
		addJComboListeners();

	}
	void addJComboListeners() {
		bSimC.addItemListener((ItemListener)this); 
	}
	public void run() {


		JPanel eastPannel = new JPanel();

		JLabel gen_sim = new JLabel("General Simulation Parameters");
		eastPannel.add(gen_sim);

		// change 10 -> 17 
		eastPannel.setLayout(new GridLayout(10,1));
		numballsSlider = new sliderBox("NUMBALLS", 1, 15, 25);
		eastPannel.add(numballsSlider.myPanel,"EAST");
		numballsSlider.mySlider.addChangeListener((ChangeListener) this);

		minSizeSlider = new sliderBox("MIN SIZE", 1.0, 15.0, 25.0);
		eastPannel.add(minSizeSlider.myPanel,"EAST");
		minSizeSlider.mySlider.addChangeListener((ChangeListener) this);
		add(eastPannel);


		setChoosers();
		this.resize(WIDTH,HEIGHT+OFFSET); // optional, initialize window size
		// Create the ground plane
		GRect gPlane = new GRect(0,HEIGHT,WIDTH,3);
		gPlane.setColor(Color.BLACK);
		gPlane.setFilled(true);
		add(gPlane);
		// Set up random number generator & B-Tree
		RandomGenerator rgen = RandomGenerator.getInstance();
		myTree = new bTree();
		// Generate a series of random gballs and let the simulation run till completion
		for (int i=0; i<NUMBALLS; i++) {
			double Xi = rgen.nextDouble(XMIN,XMAX); // Current Xi
			double Yi = rgen.nextDouble(YMIN,YMAX); // Current Yi
			double iSize = rgen.nextDouble(MINSIZE,MAXSIZE); // Current size
			Color iColor = rgen.nextColor(); // Current color
			double iLoss = rgen.nextDouble(EMIN,EMAX); // Current loss coefficient
			double iVel = rgen.nextDouble(VMIN,VMAX); // Current X velocity
			gBall iBall = new gBall(Xi,Yi,iSize,iColor,iLoss,iVel); // Generate instance
			add(iBall.myBall); // Add to display list
			myTree.addNode(iBall); // Save instance
			iBall.start(); // Start this instance
		}
		// Wait until simulation stops
		while (myTree.isRunning()); // Block until simulation terminates
		//String CR = readLine("CR to continue");
		//For standalone application with no console, use graphics display
		GLabel myLabel = new GLabel("Click mouse to continue");
		myLabel.setLocation(WIDTH-myLabel.getWidth()-50,HEIGHT-myLabel.getHeight());
		myLabel.setColor(Color.RED);
		add(myLabel);
		waitForClick();
		myLabel.setLabel("All Sorted!");
		//myTree.clearBalls(this);
		//myTree.inorder(); // Echo sizes on console
		myTree.moveSort(); // Lay out balls from left to right in size order
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//System.out.println("itemStateChanged is called");
		// TODO Auto-generated method stub
		JComboBox source = (JComboBox)e.getSource();
		if (source==bSimC) {
			if (bSimC.getSelectedIndex()==1) {
				System.out.println("Starting simulation");
				//this.myTree.setRunning(true);
			}
			else
			{
				//this.myTree.setRunning(false);
			}
		}
	}
}
