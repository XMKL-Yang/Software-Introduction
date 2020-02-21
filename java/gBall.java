package assignment4;

import java.awt.Color;
import acm.graphics.GOval;

/**
* This class provides a single instance of a ball falling under the influence
* of gravity. Because it is an extension of the Thread class, each instance
* will run concurrently, with animations on the screen as a side effect. We take * advantage here of the fact that the run method associated with the Graphics
* Program class runs in a separate thread.
*
* @author ferrie
*
*/

public class gBall extends Thread {
	
/**
* The constructor specifies the parameters for simulation. They are *
* @param Xi
* @param Yi
* @param bSize
* @param bColor
* @param bLoss
* @param bVel
 * @return 
*/
	
	public gBall (double Xi, double Yi, double bSize, Color bColor, double bLoss, double bVel) {
		this.Xi = Xi;
		this.Yi = Yi;
		this.bSize = bSize;
		this.bColor = bColor;
		this.bLoss = bLoss;
		this.bVel = bVel;
		
		myBall = new GOval(gUtil.XtoScreen(Xi-bSize),gUtil.YtoScreen(Yi+bSize),
				gUtil.LtoScreen(2*bSize),gUtil.LtoScreen(2*bSize));
		myBall.setFilled(true);
		myBall.setFillColor(bColor);
		}
/**
* The run method implements the simulation from Assignment 1. Once the start * method is called on the gBall instance, the code in the run method is
* executed concurrently with the main program.
* @param void
* @return void
* 
*/	public double getsize() {
	return this.bSize;
}
	public void bmove(double x) {
        myBall.setLocation( gUtil.XtoScreen(x),600-2*bSize*6);
	}
	
	
	public void run() {
    // Run the same simulation code as for Assignment 1
		double time = 0;
		double total_time = 0;
		double vt = Math.sqrt(2*G*Yi);
		double height = Yi;
		int dir = 0;
		double last_top = Yi;
		double el = Math.sqrt(1.0-bLoss);
		
// Simulation clock
// Tracks time from beginning
// Terminal velocity
// Initial height of drop
// 0 down, 1 up
// Height of last drop
// Energy loss scale factor for velocity
// This while loop computes height:
// dir=0:  falling under gravity -->  height =  h0 - 0.5*g*t^2
// dir=1:  vertical projectile motion --> height = vt*t - 0.5*g*t^2
		
while (true) {
	
	if (dir == 0) {
        height = last_top - 0.5*G*time*time;
        if (height <= bSize) {

        dir=1;
        last_top = bSize;
        time=0;
        vt = vt*el;
        } 
        }
else {
    height = bSize + vt*time -0.5*G*time*time;
    if (height < last_top) {
        if (height <= bSize) break;
            dir=0;
time=0; }
    last_top = height;
}

    // Determine the current position of the ball in simulation coordinates
    // and update the position on the screen
    
        double Yt = Math.max(bSize,height);             // Stay above ground!
        double Xt = Xi + bVel*total_time;
        myBall.setLocation(gUtil.XtoScreen(Xt-bSize),gUtil.YtoScreen(Yt+bSize));
        
    // Delay and update clocks
        
        try {
            Thread.sleep((long) (TICK*500));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        time += TICK;
        total_time += TICK;
	} 
    

	}
// Offset to bbox

/**
     * Instance Variables & Class Parameters
     */
    public GOval myBall;
    private double Xi;
    private double Yi;
    double bSize;
    private Color bColor;
    private double bLoss;
    private double bVel;
    public static final double G=9.8;
    private static final double TICK = 0.1; // Clock tick duration
}