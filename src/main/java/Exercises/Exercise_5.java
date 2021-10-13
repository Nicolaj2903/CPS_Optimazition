package Exercises;

import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class Exercise_5
{
    public static void main(String[] args)
    {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        /* 1. Defining the variables */
        //Maximize = 3x + 4y
        Variable X = model.addVariable("X").weight(3);
        Variable Y = model.addVariable("Y").weight(4);

        /* 2. Constraints */
        // x + y2 <= 14
        Expression constraint_1 = model.addExpression("Constraint 1")
                .upper(14) // Less or equal: Use upper
                .set(X, 1)
                .set(Y, 2);

        //3x - y >= 0
        Expression constraint_2 = model.addExpression("Constraint 2")
                .lower(0) // Higher or equal: Use lower
                .set(X, 3)
                .set(Y, -1);

        //x -y <= 2
        Expression constraint_3 = model.addExpression("Constraint 3")
                .upper(2)
                .set(X, 1)
                .set(Y, -1);

        Optimisation.Result result = model.maximise();

        System.out.println("\nResult: " + result);
        System.out.println("Model: " + model);
    }
}