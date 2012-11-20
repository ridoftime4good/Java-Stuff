public class Chap2Ex14 {
	public static void main(String[] args) {
	//writes 4 identical lines with 9 nines, 8 eights, 7 sevens, etc down to one
		drawNumbers();
	}
	
	public static void drawNumbers() {
		for (int k = 1; k <= 4; k++) {
			for (int j = 9; j >= 1; j--) {
				for (int i = 1; i <= j; i++) {
					System.out.print(j);
				}	
			}
			System.out.println();
		}	
	}
}