# myMath

This code can receive a monomial or a polynomial , ax^b when a is a real number and b is a positive integer (natural)number, 
and can perform basic task : add, multiply and subtract.
And more complex tasks such as: finding root of a polynomial between two points, and finding the area of a polynomial using Riemann Integral between two points.
It can also display the Graph of the polinomian in gui created with gral : https://github.com/eseifert/gral.

Notes:
⦁ Creating a new Monom and not giving it any values will set it to 0.
⦁ Creating a new Polynom and not giving it any values will leave it empty.
If you do the basic tasks on the empty polynom it will simply add the other polynom to the empty one.

Instructions on String input:
Constructing new monom or polynom with string : The string must be written as such :"ax^b"
allowed chars are: 0,1,2,3,4,5,6,7,8,9,+,-,.,x,^
No spaces allowed.
When a is a real number and b is a positive integer (natural)number.
a can be positive or negative , b must be positive.
Constructing a new Polynom with string: The string must be written as such:"ax^b+a1x^b1..."
No spaces are allowed.

The program will detect invalid input and throw a runtime exception.

examples of allowed stings for monom constructor: "2x^2" , "x" , "19" , "-x" , "-19x^21".
examples of allowed stings for polynom constructor: "2x^2+x^3" , "x^2" , "-19+1x^12+x^13" , "-1" , "-19x^21+2x-x^2".


Using the gral gui: 
You can use the format created in test_new class and simply change the polynom written in m1.
And in the Plots Polynom frame line you can adjust the numbers after m1 to display the graph from x1 to x2.
in the example it will render the polynom: 0.2x^4-1.5x^3+3.0x^2-x-5 from x1=-2 tp x2=6.

Authors: Alex vaisman , Shay naor.
