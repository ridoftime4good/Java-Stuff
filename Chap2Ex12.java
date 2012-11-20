public class Chap2Ex12 {
	public static void main(String[] args) {
	//writes 3 identical lines with 3 zeros, 3 ones, 3 twos, etc up to nine
		drawNumbers();
	}
	
	public static void drawNumbers() {
		for (int k = 1; k <= 3; k++) {
			for (int j = 1; j <=10; j++) {
				for (int i = 1; i <= 3; i++) {
					System.out.print(j - 1);
				}	
			}
			System.out.println();
		}	
	}
}