// Programmer: 	Gabriel Diaz
// Date:		02/14/2021
// Course:		Numerical Analysis CS384
// Purpose:		Implement the Horner’s method for applying Newton method on polynomials

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class HornersMethod 
{
	public static void main(String[] args)
	{
		if (args.length % 2 == 0)
		{
			System.out.println("You must enter an odd number of arguments.");
			System.exit(1);
		}
		
		List<Term> polynomial = new ArrayList<>();
		
		// Initial Point
		float x0 = Float.parseFloat(args[args.length - 1]);
		
		// Fill the polynomial with the terms provided in args in reverse
		// that way polymomial[0] aligns with the term with degree of 0,
		// polynomial[1] aligns with the term with degree of 1, and so on.
		for (int i = args.length - 2; i >= 0; i = i - 2)
		{
			polynomial.add(new Term(Float.parseFloat(args[i - 1]), Integer.parseInt(args[i])));
		}
		
		// If a term for a certain degree isn't provided, add the Term to
		// the polynomial ArrayList with a coefficient of 0.
		for (int i = 0; i < polynomial.size(); i++)
		{
			if (polynomial.get(i).getDegree() != i)
			{
				polynomial.add(i, new Term(0, i));
			}
		}
		
		
		// Apply Horner's Method
		float y = 0, z = 0; // At the end of the iteration, y = P(x0) & z = P'(x0)
		float xn = x0; // xn represents the calculated points using the Horner's method
		
		for (int i = 0; i < 10; i++)
		{
			y = z = polynomial.get(polynomial.size() - 1).getCoefficient();
			
			for (int j = polynomial.size() - 2; j > 0; j--)
			{
				y = xn * y + polynomial.get(j).getCoefficient();
				z = xn * z + y;
			}
			
			y = xn * y + polynomial.get(0).getCoefficient();
			
			// Update the initial point by applying Newton Method
			xn = xn - y / z;
			
			float approximation = 0;
			
			for (int k = 0; k < polynomial.size(); k++)
			{
				approximation += (polynomial.get(k).getCoefficient() * Math.pow(xn, polynomial.get(k).getDegree()));
			}
			
			// If the absolute value of approximation from the current
			// calculated point is less than 10^-4, break out of the loop
			if (Math.abs(approximation) < Math.pow(10, -4))
			{
				//System.out.println("Iteration #" + i);
				//System.out.println(approximation);
				break;
			}
		}
		
		
		
		System.out.println("An approximation to a zero of the polynomial:");
		for (int i = polynomial.size() - 1; i >= 0; i--)
		{
			if (polynomial.get(i).getCoefficient() != 0)
			{
				if (i != 0)
					System.out.printf("(%f)x^%d + ", polynomial.get(i).getCoefficient(), polynomial.get(i).getDegree());
				else
					System.out.printf("(%f)x^%d", polynomial.get(i).getCoefficient(), polynomial.get(i).getDegree());
			}
		}
		
		System.out.println("\nusing Horner's Method with initial point x0 = " + x0 + " is: x = " + xn);
		
	}
}

class Term
{
	private float coefficient;
	private int degree;
	
	public Term(float coefficient, int degree)
	{
		this.coefficient = coefficient;
		this.degree = degree;
	}
	
	// Getters
	public float getCoefficient() { return coefficient; }
	public int getDegree() { return degree; }
	
	// Setters
	public void setCoefficient(float coefficient) { this.coefficient = coefficient; }
	public void setDegree(int degree) { this.degree = degree; }
}
