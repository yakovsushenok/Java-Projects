/*
 * PROJECT I: Project1.java
 *
 * This file contains a template for the class Point. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */
public class Point {
    /**
     * x and y co-ordinates of the point on the plane. 
     */
    private double X, Y;
    public static final double GEOMTOL = 1.0e-6;
    
    /**
     * GEOMTOL is described in the formulation. It describes the tolerance
     * that we are going to use throughout this project. Remember that you do
     * NOT need to redefine this in other classes.
     */
     

    // =========================
    // Constructors
    // =========================
    
    /**
     * Default constructor - this initializes X and Y to the point (0,0).
     */
    public Point() {
	// This method is complete.
	setPoint(0.0,0.0);
    }

   /**
    * Two-parameter version of the constructor. Initialiases (X,Y) to the
    * a point (a,b) which is supplied to the function.
    *
    * @param X - x-coordinate of the point
    * @param Y - y-coordinate of the point
    */
    public Point (double X, double Y) {
	// This method is complete.
	setPoint(X,Y);
    }

    // =========================
    // Setters and Getters
    // =========================
    
    /**
     * Setter for instance variables - sets the coordinates of the point.
     *
     * @param X - new x-coordinate for this point.
     * @param Y - new y-coordinate for this point
     */
    public void setPoint(double X, double Y) {
	// This method is complete.
	this.X = X;
	this.Y = Y;
    }

    /**
     * Getter for x co-ordinate. 
     *
     * @param  none
     * @return The x co-ordinate of this point.
     */   
    public double getX() {
    // You need to fill in this method.
    return this.X;
    }

    /**
     * Getter for y co-ordinate. 
     *
     * @param  none
     * @return The y co-ordinate of this point.
     */   
    public double getY() {
    // You need to fill in this method.
    return this.Y;
    }
    
    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Point.
     *
     * @return A String of the form [X,Y]
     */
    public String toString() {
	// This method is complete.
        return "[" + X + "," + Y + "]";
    }

    // ==========================
    // Implementors
    // ==========================
    
    /**
     * Compute the distance of this Point to the supplied Point x.
     *
     * @param x  Point from which the distance should be measured.
     * @return   The distance between x and this instance
     */
    public double distance(Point x) {
    // You need to fill in this method.
    return Math.hypot(getX() - x.getX(), getY() - x.getY());
    
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    // -----------------------------------------------------------------------
    // Do not change the two methods below! They are essential for marking the
    // project, and you may lose marks if they are changed.
    //
    // We shall talk about these functions later (week 17).
    // -----------------------------------------------------------------------

    /**
     * Compare this with some Object. Two points are equal if their are in a
     * box given by the constant GEOMTOL.
     *
     * @param obj The object to compare with.
     * @return If obj is a Quaternion with the same coefficients.
     */
    public boolean equals(Object obj) {
	// This method is complete.
	if (obj instanceof Point) {
            Point q = (Point)obj;
            return (Math.abs(X - q.X) <= GEOMTOL && 
                    Math.abs(Y - q.Y) <= GEOMTOL);
	} else {
            return false;
        }
    }

    /**
     * Compare two points. Two points are equal if their are in a box given by
     * the constant GEOMTOL.
     *
     * @param q  A Point to be compared to this instance
     * @return   true if Point q is equal to this instance.
     */
    public boolean equals(Point q) {
	return (Math.abs(X - q.X) <= GEOMTOL && 
		Math.abs(Y - q.Y) <= GEOMTOL);
    }

    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main (String args[]) {
    // You should fill in this method.

        // Constructing 3 points with default point that was initially provided
        Point a = new Point(); 
		Point b = new Point(-4, -3);
		Point c = new Point(3, 2);   
		double d1 = a.distance(b); // Testing distance method
		double d2 = a.distance(c);
		double d3 = b.distance(c);
		String s1 = a.toString(); // Testing String method
		String s2 = b.toString();
		String s3 = c.toString();
		
        // The lines below will output the tests in the terminal
        System.out.println("X co-ordinate of Point 1: "+a.getX()); //  get method
        System.out.println("Y co-ordinate of Point 1: "+a.getY());
        System.out.println();
		System.out.println("X co-ordinate of Point 2: "+b.getX());
        System.out.println("Y co-ordinate of Point 2: "+b.getY());
        System.out.println();
		System.out.println("X co-ordinate of Point 3: "+c.getX());
        System.out.println("Y co-ordinate of Point 3: "+c.getY());
        System.out.println();
		System.out.println("Point 1: "+a);                             //  toString method
        System.out.println("toString method used on Point 1: "+s1);
        System.out.println();
		System.out.println("Point 2: "+b);
        System.out.println("toString method used on Point 2: "+s2);
        System.out.println();
		System.out.println("Point 3: "+c);
        System.out.println("toString method used on Point 3: "+s3);
        System.out.println();
		System.out.println("Distance between 1 & 2: "+d1);           //  distance method
		System.out.println("Distance between 1 & 3: "+d2);
        System.out.println("Distance between 2 & 3: "+d3);
        System.out.println();
		
    } 
}
