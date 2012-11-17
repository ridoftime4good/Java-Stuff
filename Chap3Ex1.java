public class Chap3Ex1 {
	public static void main (String[] args) {
	//solve for S (final position) when S = initial position + initial velocity * time + 1/2 * rate of
	//acceleration * time^2
		solveForS();
	}
	
	public static void solveForS() {
		double initialVelocity = 4, time = 6, rateOfAcceleration = 7, initialPosition = 4;
		//input values of variable
		double finalPosition = initialPosition + initialVelocity * time + (1/2) * rateOfAcceleration * (time * time);
		System.out.println(finalPosition);
	}
}