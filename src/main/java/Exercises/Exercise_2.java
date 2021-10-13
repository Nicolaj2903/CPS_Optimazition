package Exercises;

import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class Exercise_2
{

    /*
    At a certain refinery, the refining process requires the production of
    at least two gallons of gasoline for each gallon of fuel oil. To meet
    the anticipated demands of winter, at least three million gallons of
    fuel oil a day will need to be produced. The demand for gasoline, on
    the other hand, is not more than 6.4 million gallons a day.
    If gasoline is selling for $1.90 per gallon and fuel oil sells
    for $1.50/gal, how much of each should be produced in order to
    maximize revenue?
    */

    public static void main(String[] args)
    {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        /* 1. Defining the variables */

        // Gasoline cost per gallon: $1.90
        // Fuel oil cost per gallon: $1.50

        // 0 <= x <= 6.400.000   Can't produce more gasoline and can't produce a negative amount of either
        // y >= 3.000.000        Minimum amount of fuel to produce

        //Maximize = 1.9x + 1.5y
        Variable X = model.addVariable("X - Gallons of gasoline").weight(1.9);
        Variable Y = model.addVariable("Y - Gallons of fuel oil").weight(1.5);

        /* 2. Constraints */
        // x - y2 >= 0
        Expression constraint_1 = model.addExpression("Constraint 1")
                .lower(0) // Less or equal: Use upper
                .set(X, 1)
                .set(Y, -2);

        // y => 3.000.000
        Expression constraint_2 = model.addExpression("Constraint 2")
                .lower(3_000_000) // Higher or equal: Use lower
                .set(X, 1)
                .set(Y, 1);

        // x =< 6.400.000
        Expression constraint_3 = model.addExpression("Constraint 3")
                .upper(6_400_000)
                .set(X, 1);

        // x => 0
        Expression constraint_4 = model.addExpression("Constraint 4")
                .lower(0)
                .set(X, 1);

        // y => 0
        Expression constraint_5 = model.addExpression("Constraint 5")
                .lower(0)
     //           .set(X, 1)
                .set(Y, 1);

        Optimisation.Result result = model.maximise();

        System.out.println("\nResult: " + result);
        System.out.println("Model: " + model);
    }
}