package Contests.CodeChef.Starters203;

import java.util.*;
import java.lang.*;
import java.io.*;

class ColouredOrbs
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int b = sc.nextInt();
		int min = Math.min(r,b);
		int score = min * 5;
		r -= min;
		b -= min;
		if( b != 0 ) score += b* 2;
		if( r != 0 ) score += r * 1;
		System.out.println(score);
	}
}
