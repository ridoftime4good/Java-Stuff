public class Chap2Ex13 {
	public static void main(String[] args) {
	//writes 5 identical lines with 5 nines, 5 eights, 5 sevens, etc down to zero
		drawNumbers();
	}
	
	public static void drawNumbers() {
		for (int k = 1; k <= 5; k++) {
			for (int j = 10; j >= 1; j--) {
				for (int i = 1; i <= 5; i++) {
					System.out.print(j - 1);
				}	
			}
			System.out.println();
		}	
	}
}