package com.xhx.poi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhx.common.util.ECollectionUtil;
import com.xhx.poi.util.EExcelUtil;
import com.xhx.poi.view.EExcelView;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年5月30日
 * @author lihui
 */
@Controller
@Slf4j
public class GenerateExcelViewController {

	String[] headers = { "序号", "机构编号", "组织名称", "组织级别", "所处组织链", "领导账户", "领导级别" };
	String[] keys = { "index", "prop1", "prop2", "prop3", "prop4", "prop5", "prop6" };

	private List<Map<String, String>> getDatas() {
		StopWatch watch = new StopWatch();
		watch.start();
		List<Map<String, String>> datas = ECollectionUtil.getList();
		for (int i = 0; i < 100 * 10000; i++) {
			Map<String, String> m = ECollectionUtil.getMap();
			m.put(keys[0], (i + 1) + "");
			for (int j = 1; j < keys.length; j++) {
				m.put(keys[j], "prop-" + j + "-" + i);
			}
			datas.add(m);
		}
		watch.stop();
		log.info("数据准备时间:{}，数据量:{}", (watch.getTotalTimeMillis()), datas.size());
		return datas;
	}

	/**
	 * 生成组织导入模板 基础写法
	 * 
	 * @return
	 */
	@RequestMapping("/exportXExcel")
	public ModelAndView exportXExcel() {
		List<Map<String, String>> datas = this.getDatas();
		String filename = "测试.xlsx";
		Workbook wb = EExcelUtil.getWorkBook(headers, keys, datas, filename, true);
		Map<String, Object> model = ECollectionUtil.getMap();
		model.put("filename", filename);
		model.put("workbook", wb);
		return new ModelAndView(new EExcelView(), model);
	}

	/**
	 * 使用EExcelUtil.doReturnExcel 导出excel
	 * 
	 * @param response
	 */
	@RequestMapping("/exportXExcel")
	public void exportXExcelWithLambda(HttpServletResponse response) {
		List<Map<String, String>> datas = this.getDatas();
		String filename = "测试.xlsx";

		EExcelUtil.doReturnExcel(wb -> {
			HSSFSheet sheet = wb.createSheet("nodes");
			EExcelUtil.formatHeaders(headers, wb, sheet);
			CellStyle cellStyle = wb.createCellStyle();
			Row row = sheet.createRow(0);
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
		}, response, filename);
	}
}