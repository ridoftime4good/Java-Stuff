public class Chap2Ex7 {
	public static void main(String[] args) {
	//writes a line with four spaces and a 1, then three space and a 2, etc, up until 5.
		drawNumbers();
	}
	
	public static void drawNumbers() {
		for (int j = 1; j <= 5; j++) {	
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print(" ");
			}
			System.out.println(j);
		}
	}	
}