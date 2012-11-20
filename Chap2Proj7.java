public class Chap2Proj7 {
	public static final int SIZE = 8;
	public static void main(String[] args) {
	//makes a 3d textbook image called building java programs. is scaleable. only use even numbers, 8+
	//for size, please!!!
	makeTextbook();
	}
	
	public static void makeTextbook() {
	//assemble the individual pieces made from the various methods
		makeLineOne();
		makeTopHalf();
		makeLineTwo();
		makeBottomHalf();
		makeLineThree();
	}
	
	public static void makeLineOne() {
	//make the first line with spaces at the front
		for (int i = 1; i <= SIZE + 1; i++) {
			System.out.print(" ");
		}
		System.out.print("+");
		for (int i = 1; i <= SIZE * 3; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
	
	public static void makeTopHalf() {
	//make the pattern between the first and second "lines"
		for (int j = 1; j <= SIZE; j++) {	
			for (int i = 1; i <= SIZE + 1 - j; i++) {
				System.out.print(" ");
			}
			System.out.print("/");
			for (int i = 1; i <= SIZE * 3 - j * 3; i++) {
				System.out.print(" ");
			}
			System.out.print("_");
			for (int i = 1; i <= j; i++) {
				System.out.print("__/");
			}
			for (int i = 1; i <= j - 1; i++) {
				System.out.print("/");
			}
			System.out.println();
		}	
	}
	
	public static void makeLineTwo() {
	//make the second line including the /'s
		System.out.print("+");
		for (int i = 1; i <= SIZE * 3; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		for (int i = 1; i <= SIZE - 1; i++) {
			System.out.print("/");
		}
		System.out.println("/");
	}
	
	public static void makeBottomHalf() {
	//make pattern between second and final "Lines"
		for (int j = 1; j <= SIZE / 2; j++) {	
			System.out.print("|");
			for (int i = 1; i <= (SIZE * 3 - 22) / 2; i++) {
				System.out.print(" ");
			}
			System.out.print("Building Java Programs");
			for (int i = 1; i <= (SIZE * 3 - 22) / 2; i++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for (int i = 1; i <= (SIZE + 2) - j * 2; i++) {
				System.out.print("/");
			}
			System.out.println();
		}
	}
	
	public static void makeLineThree() {
	//make the third line and you done, son.
		System.out.print("+");
		for (int i = 1; i <= SIZE * 3; i++) {
			System.out.print("-");
		}
		System.out.print("+");
	}
}