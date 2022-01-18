//Austin Hetherington

import java.util.Random;

public class LinearVsBinarySearch {

	public static void main(String[] args) {
		//instance variables for calculations
		int[] lChecks = new int[20];
		int[] bChecks = new int[20];
		int[] randomArray = new int[1000];		
		
		System.out.println("Welcome to the search tester. We are going to see which algorithm performs the best out of 20 tests");
		
		//main search engine for loop
		for(int i=0; i<20; i++)
		{
			Random r = new Random();
			
			//fills array with a new set of random ints between 0-999
			for(int j=0; j<randomArray.length; j++)
			{
				randomArray[j] = r.nextInt(1000);
			}
			
			//chooses a random int to be searched in the array
			int search = r.nextInt(1000);
			
			//sorts randomArray
			int[] sortedArray = sortArray(randomArray);
			
			//runs linear search
			System.out.println("\nSearching using linear search");
			lChecks[i] = linearSearch(sortedArray, search);
			
			//runs binary search
			System.out.println("Searching using binary search");
			bChecks[i] = binarySearch(sortedArray, search, 0, sortedArray.length-1);
			
			System.out.println("Linear Checks: "+lChecks[i]);
			System.out.println("Binary Checks: "+bChecks[i]);
		}
		
		System.out.println("\nThe average number of checks for 20 were:");
		//average linear search and binary search checks
		int avgLChecks = 0;
		int avgBChecks = 0;
		for(int i=0; i<lChecks.length; i++)//sums up linear search check runs
		{
			avgLChecks += lChecks[i];
		}
		avgLChecks = avgLChecks/20;
		for(int i=0; i<bChecks.length; i++)//sums up binary search check runs
		{
			avgBChecks += bChecks[i];
		}
		avgBChecks = avgBChecks/20;
		
		System.out.println("Linear Search "+avgLChecks);
		System.out.println("Binary Search "+avgBChecks);
	}

	private static int binarySearch(int[] anArray, int search, int minIndex, int maxIndex) {
		//first finds middle index value from passed bounds
		int midIndex = (minIndex + maxIndex)/2;
		
		//checks that binary search hasn't come to a stop
		if(minIndex>maxIndex)//would mean that search value was not found
		{
			System.out.println("Not found");
			return 0;
		}
		if(anArray[midIndex]==search)//case where value was found
		{
			System.out.println("Found!");
			return 0;
		}
		else//value not found yet so must check for upper or lower bounds
		{
			if(search > anArray[midIndex])//searched value is in upper bound
			{
				return 1 + binarySearch(anArray, search, midIndex+1, maxIndex);
			}
			else//case where searched value is in lower bounds
			{
				return 1 + binarySearch(anArray, search, minIndex, midIndex-1);
			}
		}
	}

	private static int linearSearch(int[] anArray, int search) 
	{
		int checks = 0;
		boolean found = false;
		
		//for loop to conduct linear search
		for(int i=0; i < anArray.length; i++)
		{
			if(anArray[i] == search)//case where searched value is found
			{
				System.out.println("Found!");
				found = true;
				break;
			}
			checks++;//tallys that linear search algorithm executed another time
		}
		
		if(!found)//checks if value was found in searching algorithm
		{
			System.out.println("Not Found");
		}
		return checks;
	}

	private static int[] sortArray(int[] anArray) 
	{
		//Selection Sort in ascending order
		for(int i=anArray.length-1; i>0; i--)
		{
			int maxIndex = i;
			for(int j=0; j<i; j++)
			{
				if(anArray[maxIndex] < anArray[j])//case where the value at maxIndex is less than compared index value
				{
					maxIndex = j;//changes maxIndex to compared index
				}
			}
			
			//checks to see if maxIndex changed at all and swaps values if needed
			if(maxIndex!=i)
			{
				int temp = anArray[i];
				anArray[i] = anArray[maxIndex];
				anArray[maxIndex] = temp;
			}
		}
		return anArray;
	}

}
