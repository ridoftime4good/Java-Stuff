public class Chap2Ex8 {
	public static void main(String[] args) {
	//makes a line with 4 spaces and a 1, then 3 spaces and 2 twos, etc, up until 5.
		drawStairs();
	}
	
	public static void drawStairs() {
		for (int j = 1; j <= 5; j++) {
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= j; k++) {	
				System.out.print(j);
			}
			System.out.println();	
		}	
	}
}