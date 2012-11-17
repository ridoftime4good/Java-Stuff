public class Chap2Ex2 {
	public static void main(String[] args) {
	//produces output: produces one line of square numbers from 1 to 100 with space between each
		produceSquares();
	}
	
	public static void produceSquares() {
		for (int i = 1; i <= 10; i++) {
			int square = i * i;
			System.out.print(square + " ");
		}
	}
}