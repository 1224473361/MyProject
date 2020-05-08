package com.xhx.uploadfile.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author lihui
 * @date 2020年5月6日
 *
 */
@Data
@AllArgsConstructor
public class UploadFile {

	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String url;

}
