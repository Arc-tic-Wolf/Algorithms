package Algorithms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class JavaIO {

	public static void main(String[] args) throws IOException {
		//writeToFile();
		//readFromFile();
		Queue<Character> q = new LinkedList<>();
		for(int i = 0; i < 8; i++) {
			q.add('0');
		}
		if(q.peek().equals('0')) {
			System.out.println("horrah!");
		}
	}

	private static void checkQueue(Queue q) {
		System.out.println((int)q.peek());
		q.poll();
		if(null == null) System.out.println("!");
	}

	private static void readFromFile() throws IOException {
		FileInputStream fos = new FileInputStream("encodedFile.txt");
		DataInputStream dos = new DataInputStream(fos);
		for (;;) {
			try {
				System.out.println((int)dos.readUnsignedByte());
				System.out.println(Integer.parseInt("000101", 2));
			} catch (EOFException eof) {
				break;
			}
		}
		dos.close();
	}

	private static void writeToFile() throws IOException {
		//FileInputStream fis = new FileInputStream("TestInput.txt");
		//DataInputStream dis = new DataInputStream(fis);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("encodedFile.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in FileOutputStream");
		}
		DataOutputStream dos = new DataOutputStream(fos);
		int i = 65;
		while (i<75) {
			try {
				dos.writeByte((char)'c');
				i++;
				String s = Integer.toBinaryString(32);
				while(s.length() != 8) s = "0" + s;
				System.out.println(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("End of file");
				break;
			}
		}
	}
	
	
}
