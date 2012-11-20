public class Chap2Proj2 {
	public static void main(String[] args) {
	//makes ascii of two rectangles on top of one another, filled with v's and ^'s
		drawShape();
	}
	
	public static void drawShape() {
	//draw the entire shape
		printLine();	
		printHalfCarrotSquare();
		printHalfCarrotSquare();
		printLine();
		printHalfVSquare();
		printHalfVSquare();
		printLine();
	}

	public static void printHalfCarrotSquare() {
	//print one half of the square with carrots
		for (int j = 1; j <= 3; j++) {	
				System.out.print("|");
				for (int i = 1; i <= 3 - j; i++) {
					System.out.print(" ");
				}
				System.out.print("^");
				for (int i = 1; i <= (j - 1) * 2; i++) {
					System.out.print(" ");
				}
					System.out.print("^");
				for (int i = 1; i <= 3 - j; i++) {
					System.out.print(" ");
				}
				System.out.println("|");
		}	
	}
	
	public static void printHalfVSquare() {
	//print one half of the square with v's
		for (int j = 1; j <= 3; j++) {	
				System.out.print("|");
				for (int i = 1; i <= j - 1; i++) {
					System.out.print(" ");
				}
				System.out.print("v");
				for (int i = 1; i <= 6 - 2 * j; i++) {
					System.out.print(" ");
				}
					System.out.print("v");
				for (int i = 1; i <= j - 1; i++) {
					System.out.print(" ");
				}
				System.out.println("|");
		}	
	}


	public static void printLine() {
	//print one line of: +------+
		System.out.print("+");
		for (int i = 1; i <= 6; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}	
}