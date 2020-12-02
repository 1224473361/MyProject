package com.xhx.autoscan.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;

/**
 * 利用spring提供的工具类查询文件
 * 
 * @date 2019年7月2日
 * @author xhx
 */
public class SpringFileSearchUtil {

	private static PathMatcher matcher;
	private static ClassLoader cl = ClassUtils.getDefaultClassLoader();

	static {
		matcher = new AntPathMatcher();
		cl = ClassUtils.getDefaultClassLoader();
	}

	/**
	 * 利用spring提供的工具类查询文件
	 * 
	 * @param path 如com/mybatistest/&lowast;&lowast;/&lowast;.class
	 * @return
	 * @throws IOException
	 */
	public static List<File> find(String path) throws IOException {
		String rootPath = determineRootDir(path);
		String regexPath = path.substring(rootPath.length());

		List<File> fileList = new ArrayList<>(16);
		Enumeration<URL> el = cl.getResources(rootPath);
		while (el.hasMoreElements()) {
			URL url = el.nextElement();
			String toRegexPath = new File(url.getFile() + File.separator + regexPath).getPath();
			findFileByPath(fileList, url.getFile(), toRegexPath);
		}
		return fileList;
	}

	/**
	 * 查找指定目录下的符合匹配规则的文件
	 * 
	 * @param fileList
	 * @param filePath
	 * @param regexPath
	 * @throws IOException
	 */
	private static void findFileByPath(List<File> fileList, String filePath, String regexPath) throws IOException {
		File f = new File(filePath);
		if (f.isDirectory()) {
			for (File f2 : f.listFiles()) {
				if (f2.isDirectory()) {
					findFileByPath(fileList, f2.getPath(), regexPath);
				} else if (matcher.match(regexPath, f2.getAbsolutePath())) {
					fileList.add(f2);
				}
			}
		} else if (matcher.match(regexPath, f.getAbsolutePath())) {
			fileList.add(f);
		}
	}

	/**
	 * 获取根目录</br>
	 * <code>classpath&lowast;:com/mybatistest/&lowast;&lowast;/&lowast;.class->classpath&lowast;:com/mybatistest/</code>
	 * 
	 * @param location
	 * @return
	 */
	private static String determineRootDir(String location) {
		int prefixEnd = location.indexOf(':') + 1;
		int rootDirEnd = location.length();
		while (rootDirEnd > prefixEnd && matcher.isPattern(location.substring(prefixEnd, rootDirEnd))) {
			rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
		}
		if (rootDirEnd == 0) {
			rootDirEnd = prefixEnd;
		}
		return location.substring(0, rootDirEnd);
	}
}
