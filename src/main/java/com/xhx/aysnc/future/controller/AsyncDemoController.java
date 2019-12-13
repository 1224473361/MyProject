package com.xhx.aysnc.future.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhx.aysnc.future.task.AysncDemoTask;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/demo")
@Slf4j
public class AsyncDemoController {

	@Autowired
	private AysncDemoTask aysncDemoTask;

	@GetMapping("async")
	public String exeTask() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();

		Future<String> task1 = aysncDemoTask.task1();
		Future<String> task2 = aysncDemoTask.task2();
		Future<String> task3 = aysncDemoTask.task3();

		while (true) {
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				break;
			}
		}
		log.info("【{}】结果：{}","task1",task1.get());
		log.info("【{}】结果：{}","task2",task2.get());
		log.info("【{}】结果：{}","task3",task3.get());
		
		long end = System.currentTimeMillis();
		long total = end - start;
		log.info("总耗时:{}", total);
		return String.valueOf(total);
	}

}
