package com.xhx.poi.controller;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhx.common.util.ECollectionUtil;
import com.xhx.poi.util.EExcelUtil;
import com.xhx.poi.view.EExcelView;

/**
 * 
 * @date 2019年5月30日
 * @author lihui
 */
@Controller
public class GenerateExcelViewController {

	/**
	 * 生成组织导入模板
	 * 
	 * @return
	 */
	@RequestMapping("/exportOrgExcel")
	public ModelAndView generateOrgExcel() {
		String[] headers = { "序号", "机构编号", "组织名称", "组织级别", "所处组织链", "领导账户", "领导级别" };
		String[] keys = { "index", "prop1", "prop2", "prop3", "prop4", "prop5", "prop6" };
		List<Map<String, String>> datas = ECollectionUtil.getList();
		for (int i = 0; i < 10; i++) {
			Map<String, String> m = ECollectionUtil.getMap();
			m.put(keys[0], i + "");
			for (int j = 0; j < 6 - 2; j++) {
				m.put(keys[j], "prop-" + j + "-" + i);
			}
			datas.add(m);
		}
		String filename = "测试.xlsx";
		Workbook wb = EExcelUtil.getWorkBook(headers, keys, datas, filename);
		Map<String, Object> model = ECollectionUtil.getMap();
		model.put("filename", filename);
		model.put("workbook", wb);
		return new ModelAndView(new EExcelView(), model);
	}
}