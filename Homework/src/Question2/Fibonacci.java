package Question2;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println("First 25 values of Fibonacci: ");
		fib();

	}
	public static void fib()
	{
		int a = 0;
		int b = 1;
		int temp;
		for(int i = 0; i < 25; i++) {
			System.out.println(i+1 + ") " + a);
			temp = a;
			a = b;
			b = (temp+b);
		}
		
	}
}
