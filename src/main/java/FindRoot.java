import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;

public class FindRoot {
    public static void main(String[] args)
    {
        BisectionSolver solver = new BisectionSolver();
        double result = solver.solve(100000000, new Function(), -100000000, 100000000);
        System.out.println("\nResult: " + result);
    }

    private static class Function implements UnivariateFunction
    {
        public double value(double x) // x-value
        {
//            return x - 4; // Returns the y-value
//            return Math.sin(x);
            return Math.pow(x, 3) - 2 * x - 5;
        }
    }
}
