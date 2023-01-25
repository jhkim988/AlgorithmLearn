import java.util.Random;

public class SimulatedAnnealing {
    private Random rand = new Random();
    private double t=1, lim, d, k, ret;
    void simulatedAnnealing() {
        double e1, e2;
        while (t > lim) {
            e1 = scoring();
            e2 = scoring();
            double p = Math.exp((e1-e2)/(k*t));
            if (p < rand.nextDouble(1)) {
                t *= d;
            }
        }
    }
    double scoring() { return 0.0; }
}