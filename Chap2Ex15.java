public class Chap2Ex15 {
	public static void main(String[] args) {
	//makes ASCII: 5 dashes, 1, 5 dashes, next line: 4 dashes, 3 threes, 4 dashes, next line: etc. 
	//up until 9's on the 5th line.
		drawPyramid();
	}
	
	public static void drawPyramid() {
		for (int j = 1; j <= 5; j++) {	
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print("-");
			}
			for (int k = 1; k <= j * 2 - 1; k++) {
				System.out.print(j * 2 - 1);
			}
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print("-");
			}
			System.out.println();
		}
	}	
}