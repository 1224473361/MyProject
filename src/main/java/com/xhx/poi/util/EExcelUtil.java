package com.xhx.poi.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导出excel工具类
 * 
 * @date 2019年5月30日
 * @author lihui
 */
public class EExcelUtil {

	public static final String PREFIX_XLS = "xls";
	public static final String PREFIX_XLSX = "xlsx";

	private static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	}

	/**
	 * 可以根据名字自动匹配xlsx和xls格式
	 * 
	 * @param headers
	 * @param keys
	 * @param datas
	 * @param fileName
	 * @return
	 */
	public static Workbook getWorkBook(String[] headers, String[] keys, List<Map<String, String>> datas,
			String fileName) {
		return getWorkBook(headers, keys, datas, fileName, false);
	}

	/**
	 * 可以根据名字自动匹配xlsx和xls格式
	 * 
	 * @param headers
	 * @param keys
	 * @param datas
	 * @param fileName
	 * @param isBigData 当数据量超过65535时，需使用SXSSFWorkbook
	 * @return
	 */
	public static Workbook getWorkBook(String[] headers, String[] keys, List<Map<String, String>> datas,
			String fileName, boolean isBigData) {
		Workbook wb = null;
		if (getSuffix(fileName).equals(PREFIX_XLSX)) {
			if (isBigData) {
				//这样表示SXSSFWorkbook只会保留200条数据在内存中，其它的数据都会写到磁盘里，这样的话占用的内存就会很少
				wb = new SXSSFWorkbook(new XSSFWorkbook(), 200);
			} else {
				wb = new XSSFWorkbook();
			}
		} else if (getSuffix(fileName).equals(PREFIX_XLS)) {
			wb = new HSSFWorkbook();
		} else {
			return null;
		}

		Sheet sheet = wb.createSheet(fileName);
		Row row = sheet.createRow(0);
		CellStyle cellStyle = wb.createCellStyle();
		// 居中样式
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font2 = wb.createFont();
		// 粗体显示
		font2.setBold(true);
		cellStyle.setFont(font2);// 选择需要用到的字体格式

		// 表头
		Cell cell = null;
		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(cellStyle);
		}

		CellStyle ccellStyle = wb.createCellStyle();
		// 设置为文本
		DataFormat format = wb.createDataFormat();
		ccellStyle.setDataFormat(format.getFormat("@"));
		// 居中样式
		ccellStyle.setAlignment(HorizontalAlignment.CENTER);
		ccellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		Row crow = null;
		for (int i = 0; i < datas.size(); i++) {
			crow = sheet.createRow(i + 1);
			Map<String, String> map = datas.get(i);
			for (int j = 0; j < keys.length; j++) {
				cell = crow.createCell(j);
				cell.setCellValue(map.get(keys[j]));
				cell.setCellStyle(ccellStyle);
				cell.setCellType(CellType.STRING);
			}
		}
		return wb;
	}

}
