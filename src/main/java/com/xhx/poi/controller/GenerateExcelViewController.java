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

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date 2019年5月30日
 * @author lihui
 */
@Controller
@Slf4j
public class GenerateExcelViewController {

	/**
	 * 生成组织导入模板
	 * 
	 * @return
	 */
	@RequestMapping("/exportXExcel")
	public ModelAndView generateOrgExcel() {
		long time1 = System.currentTimeMillis();
		String[] headers = { "序号", "机构编号", "组织名称", "组织级别", "所处组织链", "领导账户", "领导级别" };
		String[] keys = { "index", "prop1", "prop2", "prop3", "prop4", "prop5", "prop6" };
		List<Map<String, String>> datas = ECollectionUtil.getList();
		for (int i = 0; i < 100*10000; i++) {
			Map<String, String> m = ECollectionUtil.getMap();
			m.put(keys[0], (i + 1) + "");
			for (int j = 1; j < keys.length; j++) {
				m.put(keys[j], "prop-" + j + "-" + i);
			}
			datas.add(m);
		}
		long time2 = System.currentTimeMillis();
		log.info("数据准备时间:{}，数据量:{}", (time2 - time1),datas.size());
		String filename = "测试.xlsx";
		Workbook wb = EExcelUtil.getWorkBook(headers, keys, datas, filename,true);
		Map<String, Object> model = ECollectionUtil.getMap();
		model.put("filename", filename);
		model.put("workbook", wb);
		long time3 = System.currentTimeMillis();
		log.info("Excel生成时间：{}", (time3 - time2));
		log.info("Excel生成总耗时：{}", (time3 - time1));
		return new ModelAndView(new EExcelView(), model);
	}
	
	
	
}