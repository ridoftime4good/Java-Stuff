public class Chap2Proj3 {
	public static void main(String[] args) {
	//produce 2 tall rectangles on top of one another with asterisks down the middle. the first has a
	//diamond inside and the second, twin triangles intersecting at a point.
		drawShape();
	}
	
	public static void drawShape() {
	//draws the entire shape
		drawLine();
		drawTopOfDiamond();
		drawBottomOfDiamond();
		drawLine();
		drawBottomOfDiamond();
		drawTopOfDiamond();
		drawLine();
	}
	
	public static void drawBottomOfDiamond() {
	//draws bottom of diamond/ top of triangle pair
		for (int j = 1; j <= 4; j++) {	
			System.out.print("|");
			for (int i = 1; i <= j; i++) {
				System.out.print(" ");
			}
			for (int i = 1; i <= 4 - j; i++) {
				System.out.print("\\");
			}
			System.out.print("*");
			for (int i = 1; i <= 4 - j; i++) {
				System.out.print("/");
			}
			for (int i = 1; i <= j; i++) {
				System.out.print(" ");
			}
			System.out.println("|");
		}	
	}

	public static void drawTopOfDiamond() {
	//draws top of diamond/ bottom of triangle pair
		for (int j = 1; j <= 4; j++) {	
			System.out.print("|");
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print(" ");
			}
			for (int i = 1; i <= j - 1; i++) {
				System.out.print("/");
			}
			System.out.print("*");
			for (int i = 1; i <= j - 1; i++) {
				System.out.print("\\");
			}
			for (int i = 1; i <= 5 - j; i++) {
				System.out.print(" ");
			}
			System.out.println("|");
		}	
	}
	
	public static void drawLine() {
	//draws the line at the top, middle, and end
		System.out.print("*");
		for (int i = 1; i <= 9; i++) {
			System.out.print("-");
		}
		System.out.println("*");
	}
}