package com.xhx.common.swagger;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * swagger文档导出excel实体（仅包含url）
 * 
 * @author xhx
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerExcelUrlDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Excel(name = "序号", width = 4)
	private String no;

	@Excel(name = "tag", width = 20)
	private String tag;

	@Excel(name = "接口描述", width = 26)
	private String desc;

	@Excel(name = "url", width = 30)
	private String url;

	@Excel(name = "请求方式", width = 10)
	private String method;
}
