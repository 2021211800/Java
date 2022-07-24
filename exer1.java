package 蓝山02;

import java.util.Scanner;

public class exer1 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		arr = new int[10];
		func(0, 0, 1);
	}
	
	static int n;
	static int[] arr;
	
	public static void func(int sum, int cur, int pre) {
		if(sum > n) return;
		if(sum == n) {
			System.out.print(arr[0]);
			for(int i = 1; i < cur; i++)
				System.out.print("+" + arr[i]);
			System.out.println();
			return;
		}
		for(int i = pre; i < n; i++) {
			arr[cur] = i;
			func(sum + i, cur + 1, i);
		}
	}
	
}


