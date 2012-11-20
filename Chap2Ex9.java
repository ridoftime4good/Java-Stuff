public class Chap2Ex9 {
	public static void main(String[] args) {
	//create a line of 40 dashes, an empty line, then underscore, dash, superscript arrow, dash ten
	//times repeating, followed by a line with 2 ones, 2 twos, 2 threes, up until 0 taking up 40 spaces
	//Then an empty line and a line of 40 dashes.
		drawLine();
		drawPattern();
		drawNumbers();
		drawLine();
	}
	
	public static void drawLine() {
	//draw a line of 40 dashes
		for (int i = 1; i <= 40; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public static void drawPattern() {
	//draw a repeating pattern of _-^-
		for (int j = 1; j <= 10; j++) {
			System.out.print("_-^-");
		}
		System.out.println();
	}
	
	public static void drawNumbers() {
	//draw numbers 1-9 and 0 in couples, twice
		for (int m = 1; m <= 2; m++) {
			for (int k = 1; k <= 10; k++) {
				System.out.print(k % 10 + "" + k % 10);
			}	
		}	
		System.out.println();
	}
}
