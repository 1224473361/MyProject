package com.xhx.steam.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class IODdemo {

	public static void main(String[] args) throws IOException {
		writeByteFile();
		readByteFile();
	}

	/**
	 * 读二进制
	 */
	public static void readByteFile() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream("F://byts.dat"));) {
			String readLine = dis.readLine();
			System.out.println(readLine);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 写二进制
	 */
	public static void writeByteFile() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("F://byts.dat"));) {
			dos.write(1);
			dos.writeChars("大师傅但是");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

	}

	/**
	 * 读取文件
	 */
	public static void readFile() {
		try (FileInputStream fi = new FileInputStream("F://tmp.dat");
				BufferedReader br = new BufferedReader(new InputStreamReader(fi, StandardCharsets.UTF_8));) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 写文件
	 */
	public static void writeFile() {
		try (PrintWriter pw = new PrintWriter("F://tmp.dat");) {
			pw.append("heloo");
			pw.print(' ');
			pw.println("谁啊");
			pw.print(' ');
			pw.println("wo");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取控制输入的内容
	 */
	public static void readConsole() {
		try (InputStreamReader reader = new InputStreamReader(System.in);) {
			char[] arr = new char[10];
			reader.read(arr);
			System.out.println(String.valueOf(arr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
