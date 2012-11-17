public class Chap2Ex3 {
	public static void main(String[] args) {
	//produces the first 12 fibinacci numbers on a line, separated by a space
		produceFibonacci();
	}
	
	public static void produceFibonacci() {
	//print 2 ones then add them up (c) then make a and b values get the current
	//b and c values.
		int a = 1;
		int b = 1;
		System.out.print(a + " " + b + " ");
		for (int i = 0; i <= 9; i++) {
			int c = a + b;
			System.out.print(c + " ");
			a = b;
			b = c; 
		}
	}
}