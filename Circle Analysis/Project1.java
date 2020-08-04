import java.util.Scanner;
import java.io.*;
/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    envelopsFirstLast;  //The result of the first circle enveloping the last circle in the file. 
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=472148;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }

    /**
     * Results function. It should open the file called FileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        // You need to fill in this method.
     
        double x1,y1,rad1,x2,y2,rad2; // I created two copies of variables and initialized them here so I can use them in two scans
        // The first scan will be with the aim of calculating the number of circles, and the second one would be for creating an array of circles
     
      int numberOfCircles = 0; 
	    try {
        
        Scanner s = new Scanner(new BufferedReader(new FileReader(fileName))); /*Here, just as in the ReadData class(example you
        provided), we read the file with scanner. Note that on your computer the file path/location may be different from mine, so please make sure
        when you compile it has the correct path*/
	      
	      while(s.hasNext()) {
          
          x1 = s.nextDouble(); // This will ensure that the first value scanned from the file is an x-coordinate(and every third value after that,
          y1 = s.nextDouble();   //so 1st,4th,7th etc.) and the same way for y-coordinate, and radius)
	        rad1 = s.nextDouble();  
                
          if(rad1 > Point.GEOMTOL) {  //This ensures that the circles we "accept" are well defined. i.e radius>0. For this, we use Geomtol from -> 
        
          numberOfCircles++;      // class Point
			}
	      }
	  
	    } 
		catch(Exception e) {
	      System.err.println("An error has occured."); // This handles scanner error, if there are any
        
        e.printStackTrace();
     
      }
    
      int m = numberOfCircles; // parameter m is the value of the number of well defined circles in project1.data
		 //The fact that we know the number of circles lets us create a new array of circles, which would be of length m
		
    
            Circle[] circles = new Circle[m]; // This is our new array of circles with length m
	
		int j = 0;
    
    try {
			Scanner s = new Scanner(new BufferedReader(new FileReader(fileName))); //Opening file again
	 		while(s.hasNext()) {
				  x2 = s.nextDouble(); // Now we assign these values to a new circle in while loop.
	    		y2 = s.nextDouble();
	    		rad2 = s.nextDouble();  // These assign values to a new circle in while loop
				
				if(rad2 > Point.GEOMTOL) { // This ensure that we'll be dealing with only well-defined circle (radius>0)
					circles[j] = new Circle(x2, y2, rad2);
					j++; 
				}		 //this creates m well defined circles in array
	  		}
		} catch(Exception e) {
	  	  System.err.println("An error has occured."); //making sure that we catch any errors
	  	e.printStackTrace();
    }	
    
    
    //In the lines below I assign values to the corresponding results
		    circleCounter = m;
		    envelopsFirstLast= circleEnvelopsFirstLast(circles);
		    maxArea = MaxAreaOfCircles(circles);
	      minArea = MinAreaOfCircles(circles);
	    	averageArea = averageArea(circles);
	    	stdArea = areaStandardDeviation(circles);
		    medArea = MedianAreaOfCircles(circles);
		
	}
    /**
     * A function to calculate the avarage area of circles in the array provided. 
     *
     * @param circles  An array if Circles
     */
    public double averageArea(Circle[] circles) { //This method will be dealing with array of circles, so it accepts an array of circles
           // You need to fill in this method
      double sumOfAreas = 0; 
      
      for (int j = 0; j < circles.length; j++) { // Iterates through all circles in the array, and sums their areas.
        sumOfAreas += circles[j].area();       // This will sum all of the circle areas of the circle array
        
       }
       
       return sumOfAreas / circles.length;  // Returns value of average area in the circles array
      
      }

    // The method below calculates the maximum area of a circle in the circles array
    public double MaxAreaOfCircles(Circle[] circles) { // This method will be dealing with circles array as it's input parameter
      
      double max = Double.MIN_VALUE; //Initializing our variable MAX to be the smallest double value a number can be, so then ->
      // we can work with it in the loop conveniently maximizing it through every iteration which has a greater value of area than the previous->
      //iteration
    
      for (int j = 0; j < circles.length; j++) { // iterates through array to find maximum area
        
           if (circles[j].area() > max) { // Here I use the area method from the Circle class
           max = circles[j].area();
          }
          }
          return max; 
          } //Return the maximum area of a circle in the circles array

     // The method below calculates the minimum area of a circle in the circles array
    public double MinAreaOfCircles(Circle[] circles) {  // This method will be dealing with circles array as it's input parameter
      
          double min = Double.MAX_VALUE;  // Same way as in MaxAreaOfCircles but the opposite
    
           for (int j = 0; j < circles.length; j++) { // similar to max function
           if (circles[j].area() < min) {
            min = circles[j].area();
           }
           }
           return min;  //Return the maximum area of a circle in the circles array
          }
      // The method below shows the digit value of the envelops method applied to the first and last circle of the circle array
    public int circleEnvelopsFirstLast(Circle[] circles) { // This method will be dealing with circles array as it's input parameter
      
      int envelop = circles[0].envelops(circles[circles.length - 1]); 
      
      // Uses envelops from circle class and compares 1st and last circle in the array
     
      return envelop; //returns an integer as described in circle class.
    
    }
   
    
    /**
     * A function to calculate the standart deviation of areas in the circles in the array provided. 
     *
     * @param circles  An array of Circles
     */
      
     public double areaStandardDeviation(Circle[] circles) { // This method will be dealing with circles array as it's input parameter
      //You need to complete this method.
	  double sumOfAreasSqaured = 0;
	  for (int j = 0; j < circles.length; j++) {
		  sumOfAreasSqaured += Math.pow(circles[j].area(), 2); // Mathematically this represents a summation series of squared circle areas ->
	  } //in the circles array
    
    double squaredAreasAverage = Math.pow(averageArea(circles), 2);  //squaredAreasAverage is the value of the average area ->
    //(which is computed in a method above) squared
    double standardDeviation = Math.sqrt((sumOfAreasSqaured / circles.length) - squaredAreasAverage); // This is the standard deviation formula
    
    return standardDeviation;
    
  }

    // Below is a method which, as shown in the lecture notes, will sort the values in an array from min to max. The method will be implemented->
    // in the MedianAreaOfCicles method below
    public void bubbleSort(double[] arr) { 
      boolean doneSwap = true;
      while (doneSwap) {
        doneSwap = false;
        for (int j = 0; j < arr.length-1; j++) {
          if (arr[j+1] < arr[j]) {
            double tmp = arr[j+1]; 
            arr[j+1] = arr[j];
            arr[j] = tmp;
            doneSwap = true;
          }
        }
      }
    }
  //This method accepts an array of circles, sorts its values from min to max, and then proceeds to calculate the median of the areas of the array
    public double MedianAreaOfCircles (Circle[] circles) { // This method will be dealing with circles array as it's input parameter
      double[] areasArray = new double[circles.length];
      
      for (int i = 0; i < circles.length; i++) {
        areasArray[i] = circles[i].area(); // This will create an array of the circles' areas
      }
      
      bubbleSort(areasArray); // This method sorts the array of areas from smallest area to greatest area
      int a = areasArray.length; 
     //Now that we have sorted the areas Array, we can set the median formula accordingly
      if (a%2==1) {
        double median = areasArray[(a-1)/2]; 
        return median; // The if condition here serves as a check if the array is of odd or even number of elements, since the median ->
      } //formula would be different for each case
      else {
        double median = ((areasArray[a/2 - 1] + areasArray[a/2]) / 2);
        return median;
      }
        
    }

    

    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
      // You need to fill in this method.
  Project1 project = new Project1(); // Creating an object "project" of "project1" Class
  project.results("Project1.data"); // This opens the file and reads it with scanner
   System.out.println();
   System.out.println("Testing Project's 1 results");
   System.out.println();
  // The lines below will show the output of my tests
  
  System.out.println("Number of well defined circles in file project1: "+ project.circleCounter); 
  System.out.println("Result of envelops method of first and last well defined circles in file: "+project.envelopsFirstLast);
  System.out.println("Maximum area a circle attains in the file: " +project.maxArea);
  System.out.println("Minimum area a circle attains in the file: " +project.minArea);
  System.out.println("Average area of the circles in the file: " +project.averageArea);
  System.out.println("Standard deviation of the circles in the file: " +project.stdArea);
  System.out.println("Median area of the circles in the file: " +project.medArea);
   }
}
