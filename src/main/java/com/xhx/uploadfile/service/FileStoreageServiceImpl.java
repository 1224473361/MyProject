package com.xhx.uploadfile.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lihui
 * @date 2020年5月6日
 *
 */
@Service
@Slf4j
public class FileStoreageServiceImpl {

	private final Path path = Paths.get("fileStorage");

	/**
	 * 初始化文件夹
	 */
	public void init() {
		try {
			Files.createDirectory(path);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Could not init");
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 */
	public void save(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			log.info("file：[{}] save", fileName);
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			Files.copy(file.getInputStream(), this.path.resolve(System.currentTimeMillis() + suffix));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Could not save");
		}
	}

	/**
	 * 加载文件
	 * 
	 * @param fileName
	 * @return
	 */
	public Resource load(String fileName) {
		Path file = this.path.resolve(fileName);
		try {
			log.info("file：[{}] load", fileName);
			UrlResource resource = new UrlResource(file.toUri());
			if (resource == null || !resource.exists() || !resource.isReadable()) {
				throw new RuntimeException("Could not read this file");
			}
			return resource;
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Could not save");
		}
	}

	/**
	 * 加载所有文件
	 * 
	 * @param fileName
	 * @return
	 */
	public Stream<Path> load() {
		log.info("files: load");
		try {
			return Files.walk(this.path, 1).filter(path -> !path.equals(this.path)).map(this.path::relativize);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Could not that files");
		}
	}

	/**
	 * 清空文件夹
	 */
	public void clear() {
		log.info("files: clear");
		FileSystemUtils.deleteRecursively(this.path.toFile());
	}

}
