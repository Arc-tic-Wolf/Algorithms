package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Create7GBFile {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("F:\\Academic\\5th Semester\\CSE221\\LabSolve\\Dynammic Progrmming\\OriginalFile.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		PrintWriter write = new PrintWriter(file);
		char data = 'a';
		for (int k = 1; k <= 7; k++) {
			for (int j = 1; j <= 1073741824; j++) {
				data = (char) (Math.random() * 128);
				write.print(data);
			}
		}

		System.out.println("done");
		write.close();
	}

}
