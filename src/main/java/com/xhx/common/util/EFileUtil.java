package com.xhx.common.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件寻找（支持tomcat启动）
 * 
 * @date 2019年6月19日
 * @author lihui
 */
public class EFileUtil {

	private static Logger logger = LoggerFactory.getLogger(EFileUtil.class);

	/**
	 * 在指定目录匹配名称符合条件的文件
	 * 
	 * @param dir
	 * @param fileName 支持? * 模糊匹配
	 * @return
	 */
	public static List<File> findFiles(String dir, String fileName) {
		List<File> resultList = new ArrayList<>();
		URL url = EFileUtil.class.getClassLoader().getResource(dir);
		if (null == url) {
			logger.info("File path error.");
			return resultList;
		}
		String baseDir = url.getPath();
		findFiles(baseDir, fileName, resultList);
		if (resultList.isEmpty()) {
			logger.info("No File Fount.");
			return resultList;
		}
		return resultList;
	}

	/**
	 * 
	 * 递归查找文件
	 * 
	 * @param baseDirName    查找的文件夹路径
	 * 
	 * @param targetFileName 需要查找的文件名
	 * 
	 * @param fileList       查找到的文件集合
	 * 
	 */
	private static void findFiles(String baseDirName, String targetFileName, List<File> fileList) {
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			// 判断目录是否存在
			logger.info("文件查找失败：{}不是一个目录！", baseDirName);
			return;
		}
		String tempName = null;
		// 判断目录是否存在
		File tempFile;
		File[] files = baseDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isDirectory()) {
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			} else if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (wildcardMatch(targetFileName, tempName)) {
					// 匹配成功，将文件名添加到结果集
					fileList.add(tempFile.getAbsoluteFile());
				}
			}
		}
	}

	/**
	 * 
	 * 通配符匹配
	 * 
	 * @param pattern 通配符模式
	 * 
	 * @param str     待匹配的字符串
	 * 
	 * @return 匹配成功则返回true，否则返回false
	 * 
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
	
}