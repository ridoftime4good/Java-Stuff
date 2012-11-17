public class Chap2Ex4 {
	public static void main(String[] args) {
	//create ASCII rectangle made of asterisks, 5 wide, 4 tall
		drawShape();
	}
	
	public static void drawShape() {
		for (int j = 1; j <= 4; j++) {
			for (int i = 1; i <= 5; i++) {
				System.out.print("*");
			}
			System.out.println();
		}	
	}
}