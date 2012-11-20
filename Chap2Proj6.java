public class Chap2Proj6 {
	public static final int SIZE = 10;
	public static void main(String[] args) {
	//makes a scaleable ASCII representation of the space needle
		assembleSpaceNeedle();
	}
	
	public static void assembleSpaceNeedle() {
	//put together the methods that make the rockets separate pieces
		makeNeedle();
		makeHeadTop();
		makeUndersideHead();
		makeNeedle();
		makeThickTower();
		makeHeadTop();
	}
	
	public static void makeNeedle() {
	//make the long skinny tip of the needle, and skinniest part of tower
		for (int j = 1; j <= SIZE; j++) {	
			for (int i = 1; i <= SIZE * 3; i++) {
				System.out.print(" ");
			}
			System.out.println("||");
		}
	}

	public static void makeHeadTop() {
	//make the top of the head, or the bottom of the base
		for (int j = 1; j<= SIZE; j++) {	
			for (int i = 1; i <= (SIZE * 3) - j * 3; i++) {
				System.out.print(" ");
			}
			System.out.print("__/");
			for (int i = 1; i <= (j - 1) * 3; i++) {
				System.out.print(":");
			}
			System.out.print("||");
			for (int i = 1; i <= (j - 1) * 3; i++) {
				System.out.print(":");
			}
			System.out.println("\\__");
		}
		System.out.print("|");
		for (int k = 1; k <= SIZE * 6; k++) {	
			System.out.print("\"");
		}
		System.out.println("|");
	}
	
	public static void makeUndersideHead() {
	//make underside of cap only
		for (int j = 1; j <= SIZE; j++) {
			for (int k = 1; k <= (j - 1) * 2; k++) {
				System.out.print(" ");	
			}	
			System.out.print("\\_");
			for (int i = 1; i <= (SIZE * 3 + 1) - j * 2; i++) {
				System.out.print("/\\");
			}
			System.out.println("_/");
		}
	}

	public static void makeThickTower() {
	//make the thickest part of the tower upright
		for (int i = 1; i <= SIZE * 4; i++) {
			for (int j = 1; j <= SIZE * 3 - 3; j++) {
				System.out.print(" ");
			}
			System.out.println("|%%||%%|");
		}	
	}

	}