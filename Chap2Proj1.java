public class Chap2Proj1 {
	public static void main(String[] args) {
	//creates ASCII of two upside down right triangles made from *'s. Between them is a square, the
	//top left side of which is made of /'s and the bottom left from \'s. 
		printImage();	
	}
	
	public static void printImage() {
		for (int j = 1; j <= 7; j++) {	
			for (int i = 1; i <= 7 - j; i++) {
				System.out.print("*");
			}
			for (int i = 1; i <= j; i++) {
				System.out.print(" ");
			}	
			for (int i = 1; i <= 14 - j * 2; i++) {
				System.out.print("/");
			}
			for (int i = 1; i <= (j - 1) * 2; i++) {
				System.out.print("\\");
			}
			for (int i = 1; i <= j; i++) {
				System.out.print(" ");
			}	
			for (int i = 1; i <= 7 - j; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}