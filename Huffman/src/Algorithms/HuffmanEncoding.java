package Algorithms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanEncoding {

	static int extraBit = 0;
	static String bitString = "";
	static HuffmanLeafNode root = null;
	static String[] inputString = new String[128];

	public static void main(String[] args) throws IOException {
		encode();
		for(int i=0;i<inputString.length;i++){
			if(inputString[i]!="") {
				System.out.println((char)i+" "+inputString[i]);
			}
		}
		decode();
	}

	private static void decode() throws IOException {
		FileInputStream fis = new FileInputStream("EncodedFile.txt");
		DataInputStream dis = new DataInputStream(fis);
		
		FileOutputStream fos = new FileOutputStream("DecodedFile.txt");
		DataOutputStream dos = new DataOutputStream(fos);

		int i = 0;
		Queue<Character> q = new LinkedList<>();
		while (true) {
			try {
				i = (int) dis.readUnsignedByte();
				String s = Integer.toBinaryString(i);
				while (s.length() != 8)
					s = "0" + s;
				
				for (int j = 0; j < 8; j++) {
					q.add(s.charAt(j));
				}
				traverseTree(q, root, dos);
			} catch (EOFException e) {
				while(!q.isEmpty()) {
					if(q.size() == extraBit) break;
					traverseTree(q, root, dos);
				}
				System.out.println("Decoding completed");
				dis.close();
				dos.flush();
				dos.close();
				break;
			}
		}
	}

	private static void traverseTree(Queue q, HuffmanLeafNode node, DataOutputStream dos) throws IOException {

		//System.out.println(q.peek());
		if (node.left == null && node.right == null) {
			dos.writeByte((char)node.input);
			return;
		} else if (q.peek().equals('0')) {
			q.poll();
			traverseTree(q, node.left, dos);
		} else if (q.peek().equals('1')) {
			q.poll();
			traverseTree(q, node.right, dos);
		}
	}

	private static void encode() throws IOException {
		// scanning character and frequency of unique character
		FileInputStream file = new FileInputStream("OriginalFile.txt");
		DataInputStream dis = new DataInputStream(file);

		System.out.println("Scanning the file. It might take a bit longer");
		int[] input = new int[128];
		try {
			int i;
			while (true) {
				i = dis.readUnsignedByte();
				input[i] = input[i] + 1;
			}
		} catch (EOFException eof) {
			System.out.println("Scanning done");
		} finally {
			dis.close();
		}

		// creating sorted priority queue
		System.out.println("Creating the priority queue. Please wait...");
		PriorityQueue<HuffmanLeafNode> pq = new PriorityQueue<>(new CompareFrequency());
		for (int i = 0; i < 128; i++) {
			if (input[i] != 0) {
				HuffmanLeafNode huffNode = new HuffmanLeafNode();
				huffNode.input = (char) i;
				huffNode.frequency = input[i];
				huffNode.left = null;
				huffNode.right = null;
				pq.add(huffNode);
			}
		}
		System.out.println("Priority Queue created.");

		// creating tree
		System.out.println("Creating the tree. Please wait...");

		while (pq.size() > 1) {
			HuffmanLeafNode extractedNode1 = pq.peek();
			pq.poll();
			HuffmanLeafNode extractedNode2 = pq.peek();
			pq.poll();

			HuffmanLeafNode node = new HuffmanLeafNode();
			node.frequency = extractedNode1.frequency + extractedNode2.frequency;
			node.input = '*';
			node.left = extractedNode1;
			node.right = extractedNode2;
			root = node;
			pq.add(node);
		}
		System.out.println("Tree created");

		// Assigning code to the tree

		for (int i = 0; i < 128; i++) {
			inputString[i] = "";
		}
		String binaryString = "";
		System.out.println("Assignig code to the tree. Please wait...");
		assignCode(root, input, inputString, binaryString);
		System.out.println("Code assigned to the tree.");

		// printing to the console
		// printToTheConsole(inputString);

		// writing the encoded string to the file
		writeToFile(inputString);
		System.out.println("Encoding completed.");

	}

	private static void writeToFile(String[] inputString) throws IOException {
		System.out.println("Writing to the file. Please wait...");

		FileInputStream fis = new FileInputStream("OriginalFile.txt");
		DataInputStream dis = new DataInputStream(fis);

		FileOutputStream fos = new FileOutputStream("EncodedFile.txt");
		DataOutputStream dos = new DataOutputStream(fos);

		int i = 0;
		Queue<Character> q = new LinkedList<>();

		while (true) {
			try {
				i = (int) dis.readUnsignedByte();
			} catch (EOFException e) {
				if ((q.isEmpty())) {
					break;
				} else {
					while (q.size() != 8) {
						q.add('0');
						extraBit++;
					}
					String bitString = "";
					for (int k = 1; k <= 8; k++) {
						bitString += q.peek();
						q.poll();
					}
					int bitToByte = Integer.parseInt(bitString, 2);
					try {
						dos.writeByte(bitToByte);
					} catch (IOException io) {
					}
					dis.close();
					dos.flush();
					dos.close();
					break;
				}
			}

			String s = inputString[i];
			for (int j = 0; j < s.length(); j++) {
				q.add((s.charAt(j)));
			}
			if (q.size() >= 8) {
				String bitString = "";
				for (int k = 1; k <= 8; k++) {
					bitString += q.peek();
					q.poll();
				}
				int bitToByte = Integer.parseInt(bitString, 2);
				try {
					dos.writeByte(bitToByte);
				} catch (IOException e) {
				}
			}
		}
	}

	private static void printToTheConsole(String[] inputString) throws IOException {

		FileInputStream fis = new FileInputStream("OriginalFile.txt");
		DataInputStream dis = new DataInputStream(fis);
		try {
			int i;
			while (true) {
				i = dis.readUnsignedByte();
				System.out.println((char) i + " --> " + inputString[i]);
			}
		} catch (EOFException eof) {

		} finally {
			dis.close();
		}
	}

	private static void assignCode(HuffmanLeafNode node, int[] input, String[] inputString, String binaryString) {
		if (node.left == null && node.right == null) {
			inputString[(int) (node.input)] = binaryString;
			return;
		}
		assignCode(node.left, input, inputString, binaryString + "0");
		assignCode(node.right, input, inputString, binaryString + "1");
	}

}