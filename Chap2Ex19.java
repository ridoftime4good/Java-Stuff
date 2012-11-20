public class Chap2Ex19 {
	public static void main(String[] args) {
	//makes acii: +===+===+ next line: |   |   | (repeat this for two more lines) next line: +===+===+
	//next line: |   |   | (repeat this for two more lines) next line: +===+===+
		printBox();
	}
	public static final int SIZE = 3;
	//declare constant
	
	public static void printBox() {
	//draw the full box
		for (int m = 1; m <= SIZE; m++) {	
			printLine();
			for (int k = 1; k <= 3; k++) {	
				for (int j = 1; j <= SIZE; j++) {
					System.out.print("|");
					for (int i = 1; i <= 3; i++) {
						System.out.print(" ");
					}
				}
				System.out.println("|");
			}
		}	
		printLine();
	}
		
	public static void printLine() {
	//draws 1st, 5th, and 9th line
		for (int j = 1; j <= SIZE; j++) {
				System.out.print("+");
				for (int i = 1; i <= 3; i++) {
					System.out.print("=");
				}	
		}
			System.out.println("+");
	}	
		
}
