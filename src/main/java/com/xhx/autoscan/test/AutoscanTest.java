package com.xhx.autoscan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.xhx.autoscan.annotaion.MyAnnScan;
import com.xhx.autoscan.service.MyInter;
import com.xhx.autoscan.service.MyInter2;
 

/**
 * 
 * @date 2019年9月2日
 * @author lihui
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@MyAnnScan(basePackages = { "com.xhx.autoscan.service" })
public class AutoscanTest {
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MyInter inter3;
	@Autowired
	MyInter2 inter4;

	@Test
	public void testt() {
		// 从容器中抓取
		MyInter inter = this.applicationContext.getBean(MyInter.class);
		inter.show();
		// 从容器中抓取
		MyInter2 inter2 = this.applicationContext.getBean(MyInter2.class);
		inter2.show();
		// 自动注入
		inter3.show();
		inter4.show();

		//MyInter3 inter6 = this.applicationContext.getBean(MyInter3.class);
		//System.out.println("是否存在？" + (null != inter6));

	}
}
