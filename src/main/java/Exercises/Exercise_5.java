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
        //Maximize = x + y
        Variable X = model.addVariable("Phones").weight(1);
        Variable Y = model.addVariable("Tablets").weight(1);

        /* 2. Constraints */
        // 50x + 24y <= 2400
        Expression constraint_1 = model.addExpression("Process time, machine A")
                .upper(2400)
                .set(X, 50)
                .set(Y, 24);

        //30x + 33y <= 2100
        Expression constraint_2 = model.addExpression("Process time, machine B")
                .upper(2100)
                .set(X, 30)
                .set(Y, 33);

        //x => 75 - 30   (x + 30 - 75)
        Expression constraint_3 = model.addExpression("X amount produced")
                .lower(45)
                .set(X, 1);

        //y => 95 - 90   (y + 90 - 95)
        Expression constraint_4 = model.addExpression("Y amount produced")
                .lower(5)
                .set(Y, 1);

        // (x + 30 - 75) + (y + 90 - 95) = (x + y - 50)
        Optimisation.Result result = model.maximise();

        System.out.println("\nResult: " + result);
        System.out.println("Model: " + model);
    }
}