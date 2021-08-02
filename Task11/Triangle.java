// Comment -Good work!
/*
 * Written by Barr Caplan at 10/11/19
 * Triangle class is getting three triangle's side from the user and calculates the triangle's perimeter and area
 */
import java.util.Scanner;

public class Triangle
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area and the perimeter of a given triangle.");
        System.out.println ("Please enter the three lengths of the triangle's sides:");
        
        // getting the triangle's sides from the user:
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        if (a>0 && b>0 && c>0) // checks that the user entered only natural numbers
        {
            if (a+b>c && a+c>b && b+c>a) // checks that the user entered valid triangle's sides
            {
                int perimeter = a+b+c; // calculate the triangle's perimeter
                double s = perimeter/2.0; // define half of the triangle's perimeter, for Heron's formula
                double area = Math.sqrt(s*(s-a)*(s-b)*(s-c)); // calulates the area using Heron's formula
                System.out.println ("The perimeter of the triangle is " + perimeter);
                System.out.println ("The area of the triangle is " + area);
            }
            else
                System.out.println ("The values (" + a + ", " + b + ", " + c + ") are not valid triangle sides!");
        }
        else
            System.out.println ("One or more of the values (" + a + ", " + b + ", " + c + ") is not a natural number!");
    } //end of main method
} // end of class Triangle