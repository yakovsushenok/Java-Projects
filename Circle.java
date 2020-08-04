/*
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */

    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    private Point A;
    private double r;
    public static final double GEOMTOL = 1.0e-6;
    public Circle() {
        // This method is complete.
    }
    
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        // You need to fill in this method.
        Point C1= new Point(xc,yc);
        r=rad;
        A=C1;
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param centre  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point centre, double rad) {
        // You need to fill in this method.
        A= centre ;
        r=rad;

    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCentre(double xc, double yc) {
        // You need to fill in this method.
       A=new Point(xc,yc);
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param C  A Point representing the new centre of the circle.
     */   
    public void setCentre(Point A) {
        // You need to fill in this method.
        this.A = A;
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        // You need to fill in this method.
        r = rad;
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCentre(){
        // You need to fill in this method.
        return A;
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        // You need to fill in this method.
        return r;
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        // You need to fill in this method.
        return "[" + A.getX() + "," + A.getY() + "], "+"r="+ r;
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     * 
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        // You need to fill in this method.
         return (Math.abs(A.getX()-c.getCentre().getX())<= GEOMTOL &&
            Math.abs(A.getY()-c.getCentre().getY()) <= GEOMTOL &&
            Math.abs(r-c.getRadius()) <=GEOMTOL);
         }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        // You need to fill in this method.

        return Math.PI*Math.pow(getRadius(),2);
    }

    /**
     * Tests whether this circle envelops another
     * Circle, as set out in the project formulation.
     *
     * @return An integer describing the overlap with C:
     *         1 - this envelops c; 0 - Neither envelops; -1 c envelops this circle ; -2 - identical.
     */
    public int envelops(Circle C) {
        // You need to fill in this method.
        
            if (Math.abs(r - C.r) <= Point.GEOMTOL && A.equals(C.getCentre())) { 
                return -2; // if the circles are identical; I use GEOMTOL parameter from class Point
            }	
            else if (C.r>=(A.distance(C.getCentre())+r)) { 
                return -1; //if this circle is enveloped by the circle provided
            }
            else if (A.distance(C.getCentre()) >= (r + C.r)) { 
                return 0; //disjoint circles(neither enveloping one another)
            }
            else if(r>=(A.distance(C.getCentre())+C.r)) {
                return 1; //if this circle envelops the circle provided
            }	
            return 0;	//neither enveloping one another
        }
    


    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        // You need to fill in this method.
        
    
    Point L = new Point(1,2);  // Point used for filling in a circle
    Circle u = new Circle(0,0,3); // Circles to be used for testing
    Circle u2 = new Circle(L, 3); 
    Circle u3 = new Circle(0,0,1.5); 
    Circle u4 = new Circle(0,0,4);
    Circle u5 = new Circle(0,0,3); 
    int envelops1 = u.envelops(u2); // envelops method tested
    int envelops2 = u.envelops(u3);
    int envelops3 = u.envelops(u4); 
    int envelops4 = u.envelops(u5); 
    Boolean c1 = u.equals(u2);
    Boolean c2 = u.equals(u5); // equals method tested
    double a1 = u.area(); // area method tested
    double a2 = u2.area();
    double a3 = u3.area();
    double a4 = u4.area();
    String s1 = u.toString(); // toString method tested
    String s2 = u2.toString();
    String s3 = u3.toString();
    String s4 = u4.toString();
    String s5 = u5.toString();
    //The lines below will output the tests in the terminal
    System.out.println();
    System.out.println("Circle Class Test");
    System.out.println();
    System.out.println("Circle 1: "+ u);
    System.out.println("Using toString method on Circle 1: "+ s1);
    System.out.println("Circle 2: "+ u2);
    System.out.println("Using toString method on Circle 2: "+ s2);
    System.out.println("Circle 3: "+ u3);
    System.out.println("Using toString method on Circle 3: "+ s3);
    System.out.println("Circle 4: "+ u4);
    System.out.println("Using toString method on Circle 4: "+ s4);
    System.out.println("Circle 4: "+ u5);
    System.out.println("Using toString method on Circle 4: "+ s5);
    System.out.println();
    System.out.println("Circle 1 area: "+ a1);
    System.out.println("Circle 2 area: "+ a2);
    System.out.println("Circle 3 area: "+ a3);
    System.out.println("Circle 4 area: "+ a4);
    System.out.println();
    System.out.println("Results of equals method. Whether circle 1 is equal to circle 2: "+c1);
    System.out.println();
    System.out.println("Results of equals method. Whether circle 1 is equal to circle 5: "+c2);
    System.out.println();
    // Here I test all 4 of the results from envelops method. 
    System.out.println("Result of envlops method, where circle 1 is the original circle and circle 2 is the circle provided: "+envelops1);
    System.out.println("Result of envlops method, where circle 1 is the original circle and circle 3 is the circle provided: "+envelops2);
    System.out.println("Result of envlops method, where circle 1 is the original circle and circle 4 is the circle provided: "+envelops3);
    System.out.println("Result of envlops method, where circle 1 is the original circle and circle 5 is the circle provided: "+envelops4);
    System.out.println();
    System.out.println("Centre of Circle 1: "+ u.getCentre());
    System.out.println("Radius of Circle 1: "+ u.getRadius());
    System.out.println();
    System.out.println("Centre of Circle 2: "+ u2.getCentre());
    System.out.println("Radius of Circle 2: "+ u2.getRadius());
    System.out.println("Centre of Circle 3: "+ u3.getCentre());
    System.out.println("Radius of Circle 3: "+ u3.getRadius());
    System.out.println();
    System.out.println("Centre of Circle 4: "+ u4.getCentre());
    System.out.println("Radius of Circle 4: "+ u4.getRadius());
    System.out.println();
    System.out.println("Centre of Circle 5: "+ u5.getCentre());
    System.out.println("Radius of Circle 5: "+ u5.getRadius());
    System.out.println();
    }
}
