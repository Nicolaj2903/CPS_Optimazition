import org.ojalgo.OjAlgoUtils;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class BasedModel {
    public static void main(String[] args) {
        ExpressionsBasedModel model = new ExpressionsBasedModel();

        /* 1. Defining the variables */
        //Max Profit = 60X + 40Y
        Variable X = model.addVariable("Number of red paint drum").weight(60);
        Variable Y = model.addVariable("Number of white paint drum").weight(40);

        /* 2. Constraints */
        //X + Y ≤ 100
        Expression totalDrums = model.addExpression("Total Drums")
                .upper(100) // Less (or: less and equal to...) something. Maximum
                .set(X, 1)  // Coefficient
                .set(Y, 1); // Coefficient

        //X ≥ 0
        Expression Xpositive = model.addExpression("X positive")
                .lower(0) // higher (or: higher and equal to...) something. Minimum
                .set(X, 1);

        //Y ≥ 0
        Expression Ypositive = model.addExpression("Y positive")
                .lower(0) // higher (or: higher and equal to...) something. Minimum
                .set(Y, 1);

        Optimisation.Result result = model.maximise();

        System.out.println("\nResult: " + result);
        System.out.println("Model: " + model);
    }
}
