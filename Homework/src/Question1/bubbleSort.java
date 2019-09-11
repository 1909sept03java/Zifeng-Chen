package Question1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class bubbleSort {

	public static void main(String[] args) {
	int[] ar = {1,0,5,6,3,2,3,7,9,8,4};
	bubble (ar);
	for(int i :ar) System.out.print(i + " ");
	Test();
	}
	@Test
	public static void Test()
	{
		int[] ar = {1,0,5,6,3,2,3,7,9,8,4};
		bubble (ar);
		assertArrayEquals(ar,new int[]{0,1,2,3,3,4,5,6,7,8,9});
	}
	
	static void bubble(int[] arr)
	{
		int len = arr.length;
		int temp = 0;
		for (int i = 0; i < len;i++)
		{
			for( int j = 1 ; j < (len - i); j++)
			{
				if(arr[j-1] > arr[j])
				{
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j]= temp;
				}
			}
		}
	}
}
