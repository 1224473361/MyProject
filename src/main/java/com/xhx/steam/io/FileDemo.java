package com.xhx.steam.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class FileDemo {

	public static void main(String[] args) throws IOException {
		files();
	}

	public static void files() throws IOException {
		Path path = Paths.get("F:\\tmp");
		Stream<Path> list = Files.list(path);
		// 循环直接下级目录
//		list.forEach(f -> {
//			System.out.print(f.getFileName());
//			System.out.print(">");
//			System.out.print(Files.isWritable(f));
//			System.out.println();
//		});

		//进入子目录
		Stream<Path> walk = Files.walk(path);
		walk.forEach(f -> {
			try {
			System.out.print(f.toString());
			System.out.print(">");
			System.out.print(Files.isDirectory(f));
			System.out.println();
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
	}

	/**
	 * 便捷读取文件，之适合中小文件
	 */
	public static void readFile() {
		Path path = Paths.get("F:\\tmp.dat");
		try {
			byte[] bytes = Files.readAllBytes(path);
			System.out.println(new String(bytes, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("-----------------------");
		try {
			List<String> lines = Files.readAllLines(path);
			lines.stream().forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 流处理
//		InputStream inputStream = Files.newInputStream(path);
//		BufferedReader reader=Files.newBufferedReader(path);

	}

	/**
	 * 便捷写文件，之适合中小文件
	 */
	public static void writeFile() {

		Path path = Paths.get("F:\\tmp.dat");
		String s = "HHHHHH";
		try {
			Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 流处理
//		OutputStream inputStream = Files.newOutputStream(path);
//		BufferedWriter reader=Files.newBufferedWriter(path);

	}

	public static void path() {
		// path
		String baseDir = System.getProperty("user.dir");
		Path basePath = Paths.get(baseDir);
		System.out.println(basePath);
		// 获得下级目录
		Path workP = Paths.get("work");
		System.out.println(workP);
		Path resolveP = basePath.resolve(workP);
		System.out.println(resolveP);
		resolveP = basePath.resolve("work");
		System.out.println(resolveP);
		// 规范化
		Path path = Paths.get("F:\\tmp\\..\\c\\d");
		Path path2 = path.normalize();
		System.out.println(path2);
		// 断开
		System.out.println(resolveP.getParent());
		System.out.println(resolveP.getRoot());
		System.out.println(resolveP.getFileName());
	}
}
