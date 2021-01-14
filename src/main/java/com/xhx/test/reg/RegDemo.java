package com.xhx.test.reg;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则
 */
public class RegDemo {

	public static void main(String[] args) throws MalformedURLException {
		getHref(new URL("http://www.baidu.com"));
	}

	public static void match() {
		Pattern p1 = Pattern.compile("(1*)|(2?)");
		Matcher m1 = p1.matcher("121321");
		System.out.println(m1.matches());
	}

	public static void getHref(URL url) {
		try (InputStreamReader in = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);) {
			StringBuilder sb = new StringBuilder();
			int ch;
			while ((ch = in.read()) != -1) {
				sb.append((char) ch);
			}
			System.out.println(sb);
			System.out.println("start");
			// 搜索匹配项
			//String patternStr = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			String patternStr = "<span\\s+class\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			Matcher m = pattern.matcher(sb);
			while (m.find()) {
				String match = m.group();
				System.out.println(match);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
