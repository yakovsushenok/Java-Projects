/*
 * PROJECT II: Newton.java
 *
 * This file contains a template for the class Newton. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Newton-Raphson root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Newton {
    /**
     * The maximum number of iterations that should be used when applying
     * Newton-Raphson. Ensure this is small (e.g. at most 50) otherwise your
     * program may appear to freeze!
     */
    public static final int MAXITER = 20;

    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and not explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Newton.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Newton-Raphson method to.
     */
    private Polynomial f;
    
    /**
     * The derivative of the the polynomial f.
     */
    private Polynomial fp;

    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An integer that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   =  0: Nothing went wrong.
     *   = -1: Derivative went to zero during the algorithm.
     *   = -2: Reached MAXITER iterations.
     */
    private int err;
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor. You should calculate and set fp in this method.
     *
     * @param p  The polynomial used for Newton-Raphson.
     */
    public Newton(Polynomial p) {
        // You need to fill in this method.
        this.f=p;
        this.fp=p.derivative();
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public int getError() {
        // You need to fill in this method.
         return this.err;
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
        // You need to fill in this method.
        return this.numIterations;
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        // You need to fill in this method.
        return this.root;
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
        // You need to fill in this method.
        return this.f;
    }

    /**
     * Returns the derivative of the polynomial associated with this object.
     */
    public Polynomial getFp() {
        // You need to fill in this method.
        return this.fp;
    }

    // ========================================================
    // Newton-Rapshon method
    // ========================================================
    
    /**
     * Given a complex number z0, apply Newton-Raphson to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to 0.
     *   - At some point the derivative of f becomes zero. In this case, set err 
     *     to -1 and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to -2 and return.
     *      for (int j = 0; j < rootsArray.length - 1; j+=1)
     * @param z0  The initial starting point for the algorithm.
     */
    public void iterate(Complex z0) {
        // You need to fill in this method. 
        // Creating an array of length MAXITER because we don't need a bigger array since we're not interested in iterating in more than MAXITER times
        Complex[] rootsArray = new Complex[MAXITER]; 
        rootsArray[0] = z0; // Assigning z0 as the first element in this array 
        // Here we're going to apply the Newton Raphson algorithm  
            int j=0; 
            while(j < rootsArray.length-1) {  // Here we are going to iterate until MAXITER-1 
                  Complex b = fp.evaluate(rootsArray[j]); // This is the derivative of the polynomial evaluated at complex number j i.e f'(z_j)
                  Complex h = (f.evaluate(rootsArray[j]).divide(b)).minus(); // This is the polynomial evalueated at complex number j,
                  // multiplied by minus (Since later we're going to subtract), and then divided by its derivative i.e  -f(z_j)/f'(z_j)
                  rootsArray[j+1] = rootsArray[j].add(h);
                  j += 1;	// This is the equality which represents the newton raphsom algorithm i.e z_(j+1)=z_j-f(z_j)/f'(z_j)
            }
        
        
       
        /**  In the following lines I am going to give conditions for the attainment of the error parameter value.
         * 1) (Condition 1) -- If we do not reach MAXITER iterations, this means that the while loop isnt't true anymore, and hence we have 2 consecutive roots that
     *     are equal to each other. Hence we found a root withing the range of allowed iterations and we then proceed to set the root to the last 
     *     number we attained in the sequence, and we set the number of iterations to i+1 since we iterated i+1 times. And clearly the parameter 
     *     error is set to 0 since we found a converging root
         * 2) (Condition 2) is implemented because if we iterate until z_(j+2) is equal to MAXITER, then we have reached the maximum numbers
     *     of our allowed iteration, and hence we stop iterating and assign parameter err to -2. We set the numIterations to MAXITER 
     *     3) (Condition 3) is because while two consecutive roots are not in close enough distance (ie equal), then the newton raphson
     *     algorithm continues because we have not yet came to a converging point. If the difference between 2 consecutive points is bigger than
     *     Newton.Tol then we have not yet came to a converging root and hence we have to continue the algorithm until done so. 
     *     4) (4th condition) If we get that the derivative of our polynomial is equal to 0 then we'd be dealing with division by zero. Hence we 
     *     set the err parameter to -1 and numIteration parameter to j+1 since the derivative went to 0 at the j+1'th iteration
     */
         
       int i=0;
        do { 
               if (!(2+i == MAXITER)){   // Condition 1
                    numIterations = 1+i;
                    err = 0; 
                    root = rootsArray[1+i];	   
                } else {                    // Condition 2
                    numIterations = MAXITER;
                    err = -2; 
                    break; 		
                }
        i += 1;  // While the condition in the loop is true, the counter increases.
           }  while (Math.abs(-rootsArray[i].abs() + rootsArray[1+i].abs()) >= Newton.TOL);	  // Condition 3	   
        if (fp.evaluate(rootsArray[j]).abs() == 0) { // Condition 4
            err = -1;
            numIterations = 1+j;}
        }	
     	
    // ========================================================
    // Tester function.
    // ========================================================
    
    public static void main(String[] args) {
		// Basic tester: find a root of f(z) = z^3-1 from the starting point
        // z_0 = 1+i.
        Complex[] coeff = new Complex[] { new Complex(-1.0,0.0), new Complex(), 
                                          new Complex(), new Complex(1.0,0.0) };
Polynomial p    = new Polynomial(coeff);
Newton     n    = new Newton(p);

                n.iterate(new Complex(1.0, 1.0));
                System.out.println(n.root);

                Complex[] coeff1 = new Complex[] { new Complex(-1.0,0.0), new Complex(), 
                                                   new Complex(), new Complex(1.0,0.0) };
Polynomial p1    = new Polynomial(coeff);
Newton     n1    = new Newton(p1);
Complex    c1    = new Complex(1.0, 0.0);

n1.iterate(c1);
System.out.println("Polynomial:  " +n1.getF());     // Testing getter function to get the polynomial
System.out.println();
System.out.println("Derivative: "+ n1.getFp());      // Testing getter function to get the derivative of the polynomial
System.out.println();
System.out.println("Root: " +n1.getRoot());          // Testing getter function to get the root of the polynomial
System.out.println();
System.out.println("Error: "+ n1.getError());       // Testing getter function to get the err parameter value
}
}