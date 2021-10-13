package Exercises;

import org.apache.commons.math3.analysis.function.Exp;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class Exercise_3
{

    /*
    A carpenter makes tables and chairs. Each table can be sold for a
    profit of £30 and each chair for a profit of £10. The carpenter can
    afford to spend up to 40 hours per week working and takes six hours
    to make a table and three hours to make a chair. Customer demand
    requires that he makes at least three times as many chairs as tables.
    Tables take up four times as much storage space as chairs and there is
    room for at most four tables each week
    */

    public static void main(String[] args)
    {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        /* 1. Defining the variables */

        // X - Number of tables
        // Y - Number of chairs

        //Maximize = 30x + 10y
        Variable X = model.addVariable("X - table").weight(30);
        Variable Y = model.addVariable("Y - chairs").weight(10);

        /* 2. Constraints */

        // Hours:
        // Max 40 hours
        // Table: 6 hours
        // Chair: 3 hours
        // Customer demand: Must make 3x as many chairs as tables
        // Customer demand: y => 3x
        // Customer demand: -3x + y => 0

        // -6x + 3y <= 40   (6 hours per table and 3 hours per chair)
        Expression constraint_1 = model.addExpression("Constraint 1")
                .upper(40) // Less or equal: Use upper
                .set(X, -3)
                .set(Y, 1);



        // Storage space:
        // Max storage: 4 tables a week
        // Tables: 4x times as much space as chairs

        // Tables take up 4 times as much storage space as chairs.
        // And there is room for at most four tables (which is equal to: 4 * 4 = 16 (chairs)) each week.

        // 4x + y <= 16   ->   x + 1/4 <= 4
        Expression constraint_2 = model.addExpression("Constraint 2")
                .upper(4)
                .set(X, 1)
                .set(Y, 1/4);

        // x => 0
        Expression constraint_3 = model.addExpression("Constraint 3")
                .lower(0)
                .set(X, 1);

        Expression constraint_4 = model.addExpression("Constraint 4")
                .lower(0)
                .set(Y, 1);

        Optimisation.Result result = model.maximise();

        System.out.println("\nResult: " + result);
        System.out.println("Model: " + model);
    }
}