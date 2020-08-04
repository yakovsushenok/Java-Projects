/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     *  /**
     
     
     
     
     
     //* In this class we have to set up a polynomial so that it does not store coefficients after the last non-trivial entry
     * So my method of doing this is to go from the last element of the array and then count all of the elements which are trivial
     * After counting the trivial elements I subtract this number (it's an integer) from the array's length (the array with which i'm con-
     * structing the polynomial). So now for example if I have j trivial (leading) coefficients then I will subtract j from the length of the array which 
     * stores the polynomial coefficients 
     */
     
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
		int counter = 0;
		for (int i = coeff.length - 1; i >= 0; i--) {
			if (coeff[i].getReal() == 0 && coeff[i].getImag() == 0) {
				counter++;
			}
			else
				break;
		}
		if (coeff.length == counter) {
			this.coeff = new Complex[] {new Complex(0,0)};

		}
		else {
			this.coeff = new Complex[coeff.length - counter];
			for (int i = coeff.length - counter - 1; i >= 0; i--) {
				this.coeff[i] = coeff[i];
			}
		}
    }
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
		this.coeff = new Complex [] {new Complex(0.0,0.0)};  // Setting the coeff array to be an array of size 1 with the trivial complex number
	}

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     * 
     */
    public String toString() {
        // You need to fill in this function.
		String a = "(" +coeff[0].getReal() + "+" + coeff[0].getImag() + "i)+";
        String b =" ";
        int d = coeff.length-1;  // d will be the biggest power the polynomial gets to
        String c = "(" + coeff[coeff.length-1].getReal() + "+" + coeff[coeff.length-1].getImag() + "i)X^" + d;
            int i = 1;
            while (i < coeff.length-1) {
                 b+= "(" +coeff[i].getReal() + "+" + coeff[i].getImag() + "i)X^" + i + "+";   // Iterates through the coeff coefficients and gives according indexes as powers (X^i)
                 i += 1;
            }
        return a+b+c;  // Returns our polynomial in string form
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
       return this.coeff.length-1;}
        

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     * 
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
        Complex result = new Complex(0.0,0.0); // Initializing to trivial complex number
        int j = coeff.length-1;
        while(j >= 0) {
            result = coeff[j].add(z.multiply(result));  // Applying the "Hormer's" method of evaluating a polynomial
            j -= 1;
        }
        return result;  // Returns the evaluated complex number
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */ 
     /**
     *  In this method, we first check in the first IF statement whether the polynomial is not constant. ie it is not of length 1. 
     *  So if this condition is true then we iterate through all of the elements of the array through a  loop. And then we set every member
     *  accordingly. 
     *  The second IF statement checks whether the polynomial is constant. If it is, then we set return the trivial polynomial
     */ 
    public Polynomial derivative() {
        // You need to fill in this function.
		if (!(coeff.length == 1)) { 
            Complex[] d = new Complex[coeff.length - 1]; 
                int i=0;
                while(i < coeff.length-1){
                    d[i] = coeff[1+i].multiply(1+i); 
                    i+=1; 
                }
            return new Polynomial(d);                  
		} else {
            return new Polynomial();  
        }
	}
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You need to fill in this function.
		// Creating complex numbers to use later
		Complex a1 = new Complex(-3, 1);
		Complex a2 = new Complex(2, 0.3);
        Complex a3 = new Complex(); 
        Complex b = new Complex(); 
		Complex[] com = new Complex[] {a1,b, a2, a3,b,b,b,b,b}; // Array of complex numbers to test wether the constructor works properly
		Polynomial p = new Polynomial(com); // Creates polynomial from com array
		Polynomial q = new Polynomial();    // Tests default constructor
		// Printing out results
		System.out.println("Zero Polynomial: " +q.toString());
        System.out.println("Polynomial P: " +p.toString());
        System.out.println();
		System.out.println();
        System.out.println("Degree of P: "+ p.degree());
		System.out.println("Degree of zero polynomial: "+ q.degree());
		System.out.println("Degree of derivative of P: "+ p.derivative().degree());
		System.out.println();
        System.out.println();
        Complex a4=new Complex(0,2);
		Complex a5=new Complex();
		Complex a6=new Complex(3);
		Complex a7=new Complex(4);  // Testing the 
        System.out.println("P(0): "+ p.evaluate(a5));
		System.out.println("P(3): "+ p.evaluate(a6));
		System.out.println("P(4): "+ p.evaluate(a7));
        System.out.println("P(2i): "+ p.evaluate(a4));
        System.out.println();
		System.out.println();
		System.out.println("Derivative of P: " +p.derivative());
		System.out.println("Derivative of zero polynomial: " +q.derivative());
    }
}