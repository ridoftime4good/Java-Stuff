public class Chap2Proj5 {
	public static void main(String[] args) {
	//produces a rocket in acii
		assembleRocket();
	}
	
	public static void assembleRocket() {
	//assemble the methods that make up the rockets recurring parts
		makeConeTail();
		makeLine();
		makeDiamondTops();
		makeDiamondBottoms();
		makeLine();
		makeDiamondBottoms();
		makeDiamondTops();
		makeLine();
		makeConeTail();
	}
	
	public static void makeDiamondTops() {
	//makes the top of the diamonds box and the bottom of the "triangles" box
		for (int j = 1; j <= 3; j++) {	
			System.out.print("|");
			for (int i = 1; i <= 3 - j; i++) {
				System.out.print(".");
			}	
			for (int i = 1; i <= j; i++) {
				System.out.print("/\\");
			}	
			for (int i = 1; i <= (3 - j) * 2; i++) {
				System.out.print(".");
			}	
			for (int i = 1; i <= j; i++) {
				System.out.print("/\\");
			}	
			for (int i = 1; i <= 3 - j; i++) {
				System.out.print(".");
			}	
			System.out.println("|");
		}	
	}
	
	public static void makeDiamondBottoms() {
	//makes the counterpart for the Tops piece above
		for (int j = 1; j <= 3; j++) {	
			System.out.print("|");
			for (int i = 1; i <= j - 1; i++) {
				System.out.print(".");
			}	
			for (int i = 1; i <= 4 - j; i++) {
				System.out.print("\\/");
			}	
			for (int i = 1; i <= (j - 1) * 2; i++) {
				System.out.print(".");
			}	
			for (int i = 1; i <= 4 - j; i++) {
				System.out.print("\\/");
			}	
			for (int i = 1; i <= j - 1; i++) {
				System.out.print(".");
			}	
			System.out.println("|");
		}	
	}

	
	public static void makeLine() {
	//Creates the =* line that recurs 3 times in the rocket
		System.out.print("+");
		for (int i = 1; i <= 6; i++) {
			System.out.print("=*");	
		}
		System.out.println("+");
	}
	
	public static void makeConeTail() {
	//makes the nose cone and the exhaust piece at the bottom of the rocket
		for (int j = 1; j <= 5; j++) {	
			for (int i = 1; i <= 6 - j; i++) {
				System.out.print(" ");
			}
			for (int i = 1; i <= j; i++) {
				System.out.print("/");
			}
			System.out.print("**");
			for (int i = 1; i <= j; i++) {
				System.out.print("\\");
			}
			for (int i = 1; i <= 6 - j; i++) {
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}