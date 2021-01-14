package com.xhx.steam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.xhx.steam.entity.Employee;

/**
 * 序列化
 */
public class SerializationDemo {

	public static void main(String[] args) {
		Employee emp = Employee.builder().isDr(true).id("sdfsdfds").name("Scott").age(19).amount(20.5).build();
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("F:\\e.dat"));) {
			os.writeObject(emp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("F:\\e.dat"));) {
			Employee em2 = (Employee) is.readObject();
			System.out.println(em2);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
