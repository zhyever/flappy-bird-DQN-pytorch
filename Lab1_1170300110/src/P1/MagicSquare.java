package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MagicSquare {

	/**
	 * 判断是否为幻方
	 * @param fileName
	 * @return 如果是返回true
	 */
	public static boolean isLegalMagicSquare(String fileName) {

		// 打开文件
		String path = "src" + File.separator + "P1" + File.separator + "txt";

		File f = new File(path, fileName);
		//System.out.println(f.getPath());

		ArrayList<String> arrayList = new ArrayList<>();

		try {

			InputStreamReader input = new InputStreamReader(new FileInputStream(f));
			BufferedReader bf = new BufferedReader(input);
			String str;
			while ((str = bf.readLine()) != null) {
				arrayList.add(str);
			}
			bf.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length = arrayList.size();
		int width = arrayList.get(0).split("\t").length;

		// 测试是否是方阵
		for (int i = 1; i < length; i++) {
			int widthNext = arrayList.get(i).split("\t").length;
			if (widthNext != width) {
				return false;
			}
		}

		/// 测试是否有负数 和 小数
		for (int i = 0; i < length; i++) {
			if (arrayList.get(i).contains("-") || arrayList.get(i).contains(".")) {
				return false;
			}
		}

		/// 应该测试一下是否是\t分割了
		for (int i = 0; i < length; i++) {
			if (arrayList.get(i).contains(" ")) {
				return false;
			}
		}

		/// 开始转到一个二维数组中
		int[][] arr = new int[length][length];
		String[] arrayString = new String[length + 1];

		for (int i = 0; i < length; i++) {
			arrayString = arrayList.get(i).split("\t");
			for (int j = 0; j < length; j++) {
				// System.out.println(arrayString[j]);
				arr[i][j] = Integer.valueOf(arrayString[j]);
			}
		}

		/// 测试是否转化成功
//		for(int i = 0; i < length; i++) {
//			for(int j = 0; j < length; j++) {
//				System.out.println(arr[i][j]);
//			}
//		}
//		

		/// 第一行求和
		int sumFlag = 0;
		for (int i = 0; i < length; i++) {
			sumFlag += arr[0][i];
		}

		/// 行求和
		int sum = 0;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sum += arr[i][j];
			}
			if (sum != sumFlag) {
				return false;
			}
			sum = 0;
		}

		/// 列求和
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				sum += arr[j][i];
			}
			if (sum != sumFlag) {
				return false;
			}
			sum = 0;
		}

		/// 两个对角线求和
		for (int i = 0; i < length; i++) {
			sum += arr[i][i];
		}
		if (sum != sumFlag) {
			return false;
		}
		sum = 0;

		for (int i = 0; i < length; i++) {
			sum += arr[i][length - 1 - i];
		}
		if (sum != sumFlag) {
			return false;
		}

		return true;
	}

	/**
	 * 构造幻方函数
	 * 
	 * @param n
	 * @return 如果n是非法输入返回false 否则返回true
	 */
	public static boolean generateMagicSquare(int n) {

		/// 等于零
		if (n % 2 == 0) {
			System.out.println("Input Error");
			return false;
		}

		/// 小于零
		if (n < 0) {
			System.out.println("Input Error");
			return false;
		}

		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;

		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				/// 每输入了n个数字，下一个数字出现在前一个数字的下方，即row++即可。
				row++;
			else {
				/// 总结，尝试放到右上方，如果越界，则回旋回去。
				if (row == 0)
					/// 如果向上越界了，那么下一个数字出现在最后一行。
					row = n - 1;
				else
					/// 每次尝试放到当前数字的上一行。
					row--;
				if (col == (n - 1))
					/// 如果向右越界了，那么下一个数字出现在第一列。
					col = 0;
				else
					/// 每次尝试放到当前数字的下一列。
					col++;
			}
		}

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		
		///文件输出部分
		String path = "src" + File.separator + "P1" + File.separator + "txt" + File.separator + "6.txt";
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path))));
			
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					out.print(magic[i][j]);
					out.print("\t");
				}
				out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isLegalMagicSquare("1.txt"));
		System.out.println(isLegalMagicSquare("2.txt"));
		System.out.println(isLegalMagicSquare("3.txt"));
		System.out.println(isLegalMagicSquare("4.txt"));
		System.out.println(isLegalMagicSquare("5.txt"));
		if(generateMagicSquare(7)) {
			///如果构造成功，判断是否为幻方。
			System.out.println(isLegalMagicSquare("6.txt"));
		}
	}
}
