package Question12;

public class enhancedForLoop {
	public static void main(String[] ars)
	{
		int[]store = new int[100];
		for(int i= 0; i <100; i++)
			store[i]= i+1;
		for(int j :store)
			if(j%2 == 0)	
				System.out.println(j + " ");
	}
}
