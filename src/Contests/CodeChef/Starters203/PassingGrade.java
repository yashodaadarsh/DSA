package Contests.CodeChef.Starters203;

import java.util.*;
import java.lang.*;
import java.io.*;

class PassingGrade
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for( int i = 0 ; i < t ; i++ ){
		    int n = sc.nextInt();
		    int marks = 0;
		    int count = 0;
		    int[] arr = new int[n];
		    for( int j = 0 ; j < n ; j++ ){
		        arr[j] = sc.nextInt();
		        if( j == 0 ) marks = arr[0];
		        if( arr[j] >= marks ) count++;
		    }
		    System.out.println(count);
		}

	}
}
