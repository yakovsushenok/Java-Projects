/*
 * PROJECT II: Complex.java
 *
 * This file contains a template for the class Complex. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class implements a new type for complex numbers and corresponding
 * arithmetic operations. You should already have done something very similar
 * in week 14 (see CmplxNum in the online lab notes), so you should be able to
 * simply copy and paste from that file into this one.
 *
 * At the bottom of this file, I have included a simple main function which
 * tests the basic functionality. This is the same code that was released in
 * the week 14 solutions.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Complex {
    /**
     * Real part x of the complex number x+iy.
     */
    private double x;
    
    /**
     * Imaginary part y of the complex number x+iy.
     */
    private double y;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Constructor: Initializes x, y.
     *
     * @param x  The initial value of the real component.
     * @param y  The initial value of the imaginary component.
     */
    public Complex(double x, double y) {
        // You need to fill in this method.
        this.x=x;
        this.y=y;
    }

    /**
     * Real constructor - initialises with a real number.
     *
     * @param x  The initial real number to initialise to.
     */
    public Complex(double x) {
        // You need to fill in this method.
        this(x, 0.0);
    }

    /**
     * Default constructor; initialiase x and y to zero.
     */
    public Complex() {
        // You need to fill in this method.
        this(0.0, 0.0);
    }
    
    // ========================================================
    // Accessor and mutator methods.
    // ========================================================
    
    /**
     * Accessor Method: get real part of the complex number.
     * 
     * @return The real part of the complex number.
     */
    public double getReal() {
        // You need to fill in this method.
        return x;
    }

    /**
     * Accessor Method: get imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number
     */
    public double getImag() {
        // You need to fill in this method.
        return y;
    }

    /**
     * Mutator method: set the real part of the complex number.
     *
     * @param x  The replacement real part of z.
     */
    public void setReal(double x) {
        // You need to fill in this method.
        this.x=x;
    }
    
    /**
     * Mutator method: set the imaginary part of the complex number.
     *
     * @param y  The replacement imaginary part of z.
     */
    public void setImag(double y) {
        // You need to fill in this method.
        this.y=y;
    }
    
    // ========================================================
    // Operations and functions with complex numbers.
    // ========================================================

    /**
     * Converts the complex number to a string. This is an important method as
     * it allows us to print complex numbers using System.out.println.
     *
     * @return A string describing the complex number.
     */
    public String toString() {
        // This function is complete.
        if (y < 0.0)
            return x + "-" + Math.abs(y) + "i";
        else
            return x + "+" + Math.abs(y) + "i";
    }

    /**
     * Computes square of the absolute value (magnitude) of the complex number
     * (i.e. |z|^2).
     *
     * @return The square of the absolute value of this complex number.
     */
    public double abs2() {
        // You need to fill in this method.
        return Math.pow(x, 2)+Math.pow(y, 2);
    }

    /**
     * Computes absolute value (magnitude) of the complex number.
     *
     * @return The absolute value of the complex number.
     */
    public double abs() {
        // You need to fill in this method.
        
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }
    
    /**
     * Calculates the conjugate of this complex number.
     *
     * @return A Complex contaning the conjugate.
     */
    public Complex conjugate() {
        // You need to fill in this method.
        return new Complex(this.x,-this.y);
    }

    /**
     * Adds a complex number to this one.
     *
     * @param b  The complex number to add to this one.
     * @return   The sum of this complex number with b.
     */
    public Complex add(Complex b) {
        // You need to fill in this method.
        return new Complex(this.x + b.x, this.y + b.y);
    }
    
    /**
     * Calculates -z.
     *
     * @return The complex number -z = -x-iy
     */
    public Complex minus() {
        // You need to fill in this method.
       return  new Complex(-this.x, -this.y);
    }

    /**
     * Multiplies this complex number by a constant.
     *
     * @param alpha   The constant to multiply by.
     * @return        The product of alpha with z.
     */
    public Complex multiply(double alpha) {
        // You need to fill in this method.
        return new Complex(alpha*this.x, alpha*this.y);
    }
    
    /**
     * Multiplies this complex number by another complex number.
     *
     * @param b   The complex number to multiply by.
     * @return    The product of b with z.
     */
    public Complex multiply(Complex b) {
        // You need to fill in this method.
        return new Complex(this.x*b.x - this.y*b.y, this.x*b.y + this.y*b.x);
    }

    /**
     * Divide this complex number by another.
     *
     * @param b  The complex number to divide by.
     * @return   The division z/a.
     */
    public Complex divide(Complex b) {
        // You need to fill in this method.
        Complex ret;
        double   alpha;
        
        alpha = b.abs2();
        ret   = this.multiply(b.conjugate());
        ret   = ret.multiply(1.0/alpha);
        return ret;
    }
        
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // Test all of the constructor functions. You can add anything you
        // want to this if you think it's necessary!
        Complex A = new Complex(1.0, 1.0);
        Complex B = new Complex(1.0);
        Complex C = new Complex();
        
        // Test the converters by printing out A, B and C.
        System.out.println("Constructor test:");
        System.out.println("A = "+A.toString());
        System.out.println("B = "+B.toString());
        System.out.println("C = "+C.toString());
        
        // Test accessor/mutators.
        System.out.println("\nSetting imag(C) = real(C) + 1:");
        C.setImag(C.getReal() + 1.0);
        System.out.println("C = "+C.toString());
        
        // Now test operations for both complex and real numbers, just to make
        // sure something isn't wrong.
        System.out.println("\nTesting operators:");
        System.out.println("abs(A)   = "+A.abs());
        System.out.println("abs2(A)  = "+A.abs2());
        System.out.println("abs(B)   = "+B.abs());
        System.out.println("abs2(B)  = "+B.abs2());
        System.out.println("conj(A)  = "+A.conjugate());
        System.out.println("conj(B)  = "+B.conjugate());
        System.out.println("neg(A)   = "+A.minus());
        System.out.println("neg(B)   = "+B.minus());
        System.out.println("A+B      = "+A.add(B));
        System.out.println("A+C      = "+A.add(C));
        System.out.println("2*A      = "+A.multiply(2.0));
        System.out.println("A*C      = "+A.multiply(C));
        System.out.println("B*C      = "+B.multiply(C));
        System.out.println("A*A      = "+A.multiply(A));
        System.out.println("A/B      = "+A.divide(B));
        System.out.println("A/C      = "+A.divide(C));
        System.out.println("A/A      = "+A.divide(A));

        // And to finish off, a couple examples of how you can chain the
        // different operations together.
        System.out.println("\nChained operators:");
        System.out.println("B*(A+C)    = "+B.multiply(A.add(C)));
        System.out.println("-A+B*C     = "+A.minus().multiply(B.add(C)));
    }
}