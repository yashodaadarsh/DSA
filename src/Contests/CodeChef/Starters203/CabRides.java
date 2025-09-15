package Contests.CodeChef.Starters203;

import java.util.*;
import java.lang.*;
import java.io.*;

class CabRides
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for( int i = 0 ; i < t ; i++ ){
		    int cost = 0;
		    int n = sc.nextInt();
		    if( n == 1 ){
		        System.out.println(200);
		    }
		    else{
		        int minCars = (int)Math.ceil(n/4.0);
    		    cost += minCars*200;
    		    int left = n - minCars*2;
    		    cost += left * 100;
    		    System.out.println(cost);
		    }
		}

	}
}
