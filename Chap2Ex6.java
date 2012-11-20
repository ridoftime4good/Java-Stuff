public class Chap2Ex6 {
	public static void main(String[] args) {
	//makes a line with 1 one, another with 2 two's, etc.,up until 7.
		makeStairs();
	}
	
	public static void makeStairs() {
		for (int i = 1; i <= 7; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i);
			}
			System.out.println();
		}	
	}
}