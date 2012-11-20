public class Chap2Ex19 {
	public static void main(String[] args) {
	//makes acii: +===+===+ next line: |   |   | (repeat this for two more lines) next line: +===+===+
	//next line: |   |   | (repeat this for two more lines) next line: +===+===+
		printBox();
	}
	
	public static void printBox() {
		System.out.print("+");
		for (int i = 1; i <= 3; i++){
			System.out.print("=");
		}
	}
}