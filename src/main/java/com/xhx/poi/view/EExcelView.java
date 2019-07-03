package com.xhx.poi.view;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;

/**
 * 导出excel视图(用于控制excel浏览器下载)
 * 
 * @date 2019年5月30日
 * @author lihui
 */
@Slf4j
public class EExcelView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String filename = (String) model.get("filename");

		// 响应信息，弹出文件下载窗口
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
		try (OutputStream os = response.getOutputStream();) {
			Workbook workbook = (Workbook) model.get("workbook");
			workbook.write(os);
		} catch (Exception e) {
			log.error("导出出错：" + e.getMessage(), e);
			throw e;
		}

	}

}
