package com.xhx.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @date 2019年7月23日
 * @author xhx
 */
public class WriteFile {

	/**
	 * 将byte数组写入文件
	 * 
	 * @param path
	 * @param fileName
	 * @param content
	 */
	public static void writeFile(String path, String fileName, byte[] content) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		try (FileOutputStream fos = new FileOutputStream(path + fileName);) {
			fos.write(content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
