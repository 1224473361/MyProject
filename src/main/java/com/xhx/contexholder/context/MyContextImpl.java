package com.xhx.contexholder.context;

import com.xhx.contexholder.detail.MyDetail;

/**
 * 内容接口实现类
 */
public class MyContextImpl implements MyContext {

	private MyDetail detail;

	@Override
	public MyDetail getDetail() {
		return detail;
	}

	@Override
	public void setDetail(MyDetail detail) {
		this.detail = detail;
	}

}
