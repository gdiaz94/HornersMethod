# HornersMethod
This was done as an assignment in my Numerical Analysis course.
The program takes a polynomial and an initial point and finds an approximation to a zero of the polynomial.
You pass the polynomial as an argument to the function, for instance calling:
java HornersMethod  2 4 -3 2 3 1 -4 0 -2
in the command line will have the program evaluate the polynomial 2x^4 -3x^2 + 3x -4 with initial point x0 = -2.
If a term of the polynomial has a coefficient of value 0, don't add that term to the arguments.
