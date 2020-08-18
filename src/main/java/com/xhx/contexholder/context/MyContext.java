package com.xhx.contexholder.context;

import com.xhx.contexholder.detail.MyDetail;

/**
 * 内容接口
 */
public interface MyContext {

	MyDetail getDetail();

	void setDetail(MyDetail detail);
}
