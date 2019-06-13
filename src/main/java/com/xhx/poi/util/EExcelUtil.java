package com.xhx.poi.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

	public static Workbook getWorkBook(String[] headers, String[] keys, List<Map<String, String>> datas,
			String fileName) {
		Workbook wb = null;
		if (fileName.contains(PREFIX_XLS)) {
			wb = new HSSFWorkbook();
		} else if (fileName.contains(PREFIX_XLSX)) {
			wb = new XSSFWorkbook();
		} else {
			return null;
		}

		Sheet sheet = wb.createSheet(fileName);
		Row row = sheet.createRow(0);
		CellStyle cellStyle = wb.createCellStyle();
		// 居中样式
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		Font font2 = wb.createFont();
		// font2.setFontName("仿宋_GB2312");
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
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
		ccellStyle.setAlignment(CellStyle.ALIGN_CENTER);

		Row crow = null;
		for (int i = 0; i < datas.size(); i++) {
			crow = sheet.createRow(i + 1);
			Map<String, String> map = datas.get(i);
			for (int j = 0; j < keys.length; j++) {
				cell = crow.createCell(j);
				cell.setCellValue(map.get(keys[j]));
				cell.setCellStyle(ccellStyle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
			}
		}
		return wb;
	}

}
