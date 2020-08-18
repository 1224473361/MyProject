package com.xhx.poi.handler;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 获取excel对象-处理接口
 * 
 * @author lihui
 * @date 2020年6月12日
 *
 */
@FunctionalInterface
public interface ExcelHandler {

	/**
	 * 支持xls版的excel
	 * 
	 * @param wb
	 * @return
	 */
	HSSFWorkbook getHSSFWorkbook(HSSFWorkbook wb);

}