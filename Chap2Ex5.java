public class Chap2Ex5 {
	public static void main(String[] args) {
	//creates 5 lines of asterisk, 1st one, 2nd two, etc. Think staircase.
		drawStairs();
	}
	
	public static void drawStairs() {
		for (int lines = 1; lines <= 5; lines++) {
			for (int dots = (6 - lines); dots <= 5; dots++) {
				System.out.print("*");	
			}
			System.out.println();
		}
	}
}