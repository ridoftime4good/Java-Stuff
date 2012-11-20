public class Chap2Proj4 {
	public static final int SIZE = 12;
	public static void main(String[] args) {
 	//draw a staircase with five stick figures standing on each step (including floor)
		printStairs();
	}
	
	public static void printStairs() {
	//print the whole staircase with people
		for (int j = 1; j <= SIZE; j++) {	
			for (int i = 1; i <= (SIZE * 5 + 2) - (5 * j); i++) {
				System.out.print(" ");
			}
			System.out.print("o  ");
			for (int i = 1; i <= 6; i++) {
				System.out.print("*");
			}
			for (int i = 1; i <= (j - 1) * 5; i++) {
				System.out.print(" ");
			}
			System.out.println("*");
			for (int i = 1; i <= (SIZE * 5 + 1) - j * 5; i++) {
				System.out.print(" ");
			}
			System.out.print("/|\\ *");
			for (int i = 1; i <= j * 5; i++) {
				System.out.print(" ");
			}
			System.out.println("*");
			for (int i = 1; i <= (SIZE * 5 + 1) - j * 5; i++) {
				System.out.print(" ");
			}
			System.out.print("/ \\ *");
			for (int i = 1; i <= j * 5; i++) {
				System.out.print(" ");
			}
			System.out.println("*");
		}	
		for (int i = 1; i <= 5 * SIZE + 7; i++) {
			System.out.print("*");
		}
	}	
}