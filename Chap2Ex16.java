public class Chap2Ex16 {
	public static void main(String[] args) {
	//makes ASCII: 22 !'s, next line: 2 \'s, 18 !'s, 2 /'s, next line: 4 \'s, 14 !'s, 4 /'s, etc up
	//until the 6th line
		drawPyramid();
	}
	public static final int CONSTANT = 8;
	//declare constant
	
	public static void drawPyramid() {
	//method to draw pyramid
		for (int j = 1; j <= CONSTANT; j++) {	
			for (int k = 1; k <= (j - 1) * 2; k++) {
				System.out.print("\\");
			}
			for (int i = 1; i <= (CONSTANT * 4 - 2) - (j - 1) * 4; i++) {
				System.out.print("!");
			}
			for (int m = 1; m<= (j - 1) * 2; m++) {
				System.out.print("/");
			}
			System.out.println();
		}
	}
	
	
}