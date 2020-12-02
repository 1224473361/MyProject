package com.xhx.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用nio MappedByteBuffer读取大文件，但是无法按行处理
 * 
 * @date 2019年8月23日
 * @author xhx
 */
@Data
@Slf4j
public class MappedBiggerFileReader {

	private MappedByteBuffer[] mappedBuArray;
	private int count = 0;
	private int number;
	private RandomAccessFile afile;
	private long fileLength;
	private int arraySize;
	private byte[] array;

	public MappedBiggerFileReader(String filePath, int arraySize) throws IOException {
		super();
		this.afile = new RandomAccessFile(filePath, "rw");
		FileChannel fc = afile.getChannel();
		this.fileLength = fc.size();
		log.info("文件大小：{}", this.fileLength);
		this.number = (int) Math.ceil((double) this.fileLength / (double) Integer.MAX_VALUE);
		log.info("数组大小：{}", this.number);
		// 内存文件映射数组
		this.mappedBuArray = new MappedByteBuffer[this.number];
		long preLenth = 0;
		// 映射区域的大小
		long regionSize = (long) Integer.MAX_VALUE;
		for (int i = 0; i < this.number; i++) {
			// 将文件的连续区域映射到内存文件映射数组中
			if (this.fileLength - preLenth < (long) Integer.MAX_VALUE) {
				// 最后一片区域的大小
				regionSize = this.fileLength - preLenth;
			}
			this.mappedBuArray[i] = fc.map(FileChannel.MapMode.READ_ONLY, preLenth, regionSize);
			// 下一片区域的开始
			preLenth += regionSize;
		}
		this.arraySize = arraySize;
	}

	public int read() {
		if (count >= number) {
			return -1;
		}

		int limit = this.mappedBuArray[count].limit();
		int position = this.mappedBuArray[count].position();
		if (limit - position > arraySize) {
			array = new byte[arraySize];
			mappedBuArray[count].get(array);
			return arraySize;
		} else {
			// 本内存文件映射最后一次读取数据
			array = new byte[limit - position];
			mappedBuArray[count].get(array);
			// 转换到下一个内存文件映射
			count++;
			return limit - position;
		}
	}

	public void close() throws IOException {
		this.afile.close();
		array = null;
	}

	/**
	 * 数组最大长度
	 */
	public static final int MAX_ARRAYSIZE = 61858764;

	public static void main(String[] args) throws IOException {
		MappedBiggerFileReader reader = new MappedBiggerFileReader("D:\\tt\\catalina.out", 65536 * 100);
		long start = System.currentTimeMillis();
		int index = 1;
		while (reader.read() != -1) {
			String s = new String(reader.getArray(), StandardCharsets.UTF_8);
			System.err.println(s.substring(0, 20));
			index++;
		}

		log.info("总数组数量：{}", index);
		log.info("mappedbuff数量：{}", reader.getMappedBuArray().length);
		long end = System.currentTimeMillis() - start;
		log.info("时间：{},{}s", end, end / 1000.0);
	}

}
