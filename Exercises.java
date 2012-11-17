public class Exercises {
	public static void main (String[] args) {
	//solve for s when s = s + vt + 1/2at^2
		solveForS();
	}
	
	public static void solveForS() {
		double v = 4;
		double t = 6;
		double a = 7;
		double s = 4;
		double S = s + v * t + (1/2) * a * (t * t);
		System.out.println(S);
	}
}