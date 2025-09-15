package Contests.CodeChef.Starters203;

import java.util.*;
import java.lang.*;
import java.io.*;

class MaximumMST
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for( int i = 0 ; i < t ; i++ ){
		    int n = sc.nextInt();
		    int ed = (n*(n-1))/2;
		    int[] w = new int[ed];
		    for( int j = 0 ; j < ed ; j++ ){
		        w[j] = sc.nextInt();
		    }
		    Arrays.sort(w);
		    long cost = 0;
		    int x = 0;
		    for( int j = 1 ; j < n ; j++ ){
		        x += j-1;
		        cost += w[x];
		    }
		    System.out.println(cost);
		}

	}
}
