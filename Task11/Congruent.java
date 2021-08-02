// Comment -Good work!
/*
 * Written by Barr Caplan at 10/11/19
 * Congruent calss is getting two triangle's coordinates and calculates whether they are congruent or not.
 */
import java.util.Scanner;

public class Congruent
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program determines whether two given triangles are congruent or not \n");
        
        // Geting 3 points of the first triangle
        System.out.println ("Please enter three coordinate pairs of the first triangle:");
        double x11 = scan.nextDouble();
        double y11 = scan.nextDouble();
        double x12 = scan.nextDouble();
        double y12 = scan.nextDouble();
        double x13 = scan.nextDouble();
        double y13 = scan.nextDouble();
        
        // gerring 3 points of the second triangle
        System.out.println ("Please enter three coordinate pairs of the second triangle:");
        double x21 = scan.nextDouble();
        double y21 = scan.nextDouble();
        double x22 = scan.nextDouble();
        double y22 = scan.nextDouble();
        double x23 = scan.nextDouble();
        double y23 = scan.nextDouble();
        
        //calculating first triangle's sides' length
        double a1 = Math.sqrt( Math.pow((x11-x12),2) + Math.pow((y11-y12),2) );
        double b1 = Math.sqrt( Math.pow((x11-x13),2) + Math.pow((y11-y13),2) );
        double c1 = Math.sqrt( Math.pow((x13-x12),2) + Math.pow((y13-y12),2) );
        
        //calculating second triangle's sides' length
        double a2 = Math.sqrt( Math.pow((x21-x22),2) + Math.pow((y21-y22),2) );
        double b2 = Math.sqrt( Math.pow((x21-x23),2) + Math.pow((y21-y23),2) );
        double c2 = Math.sqrt( Math.pow((x23-x22),2) + Math.pow((y23-y22),2) );
        
        // printing first triangle's points and sides' lengths
        System.out.print ("The first triangle is (" + x11 + "," + y11 + ") (");
        System.out.println (x12 + "," + y12 + ") (" + x13 + "," + y13 + ").");
        System.out.println ("its lengths are " + a1 + ", " + b1 + ", " + c1 + ".");
        
        // printing second triangle's points and sides' lengths
        System.out.print ("The second triangle is (" + x21 + "," + y21 + ") (");
        System.out.println (x22 + "," + y22 + ") (" + x23 + "," + y23 + ").");
        System.out.println ("its lengths are " + a2 + ", " + b2 + ", " + c2 + ".\n");
        
        if ( // checking if the lengths are equal
            (a1==a2 && ( (b1==b2&&c1==c2) || (b1==c2&&c1==c2) ) ) ||
            (a1==b2 && ( (b1==a2&&c1==c2) || (b1==c2&&c1==a2) ) ) ||
            (a1==c2 && ( (b1==a2&&c1==b2) || (b1==b2&&c1==a2) ) )
           )
            System.out.println ("The triangles are congruent.");
        else
            System.out.println ("The triangles are not congruent.");
        
        
    } // end of method main
} // end of class
