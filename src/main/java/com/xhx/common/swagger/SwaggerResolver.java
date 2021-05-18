package com.xhx.common.swagger;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xhx.common.util.EasyPoiUtils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.io.file.FileReader;

/**
 * swagger解析器<br>
 * 主要功能：转为在线swagger返回json至excel，md文件等
 * 
 * 
 * @since 2021-05-18
 *
 */
public class SwaggerResolver {

	/**
	 * 
	 */
	public static final String KEY_PATHS = "paths";
	/**
	 * 
	 */
	public static final String KEY_SUMMARY = "summary";
	/**
	 * 
	 */
	public static final String KEY_TAGS = "tags";

	/**
	 * 后缀名
	 */
	public static final String SUFFIX_XLSX = ".xlsx";
	public static final String SUFFIX_XLS = ".xls";

	/**
	 * 转换swaggerJson文件至url统计excel文件
	 * 
	 * @param jsonFilePath json文件路径
	 * @param excelDir     excel文件目录
	 * @param excelName    excel名称
	 * @return
	 */
	public static String convertJsonToUrlExcel(String jsonFilePath, String excelDir, String excelName) {
		FileReader fileReader = new FileReader(jsonFilePath);
		String str = fileReader.readString();
		JSONObject pObj = JSON.parseObject(str);
		JSONObject path = pObj.getJSONObject(KEY_PATHS);
		List<SwaggerExcelUrlDto> list = new ArrayList<>();
		path.forEach((key, obj) -> {
			JSONObject jobj = (JSONObject) obj;
			jobj.forEach((key2, obj2) -> {
				JSONObject jobj2 = (JSONObject) obj2;
				list.add(new SwaggerExcelUrlDto("", jobj2.getJSONArray(KEY_TAGS).get(0).toString(),
						jobj2.getString(KEY_SUMMARY), key, key2));
			});
		});
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(excelName, excelName, ExcelType.XSSF),
				SwaggerExcelUrlDto.class, list);
		String epath = excelDir + excelName + SUFFIX_XLSX;
		EasyPoiUtils.exportToFile(epath, workbook);
		return epath;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String epath = convertJsonToUrlExcel("D:/appServer.json", "D:/", "appServer接口文档");
		System.out.println("导出完毕:" + epath);
	}

}