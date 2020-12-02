package com.xhx.uploadfile.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.xhx.uploadfile.service.FileStoreageServiceImpl;
import com.xhx.uploadfile.vo.Result;
import com.xhx.uploadfile.vo.UploadFile;

/**
 * @author xhx
 * @date 2020年5月6日
 *
 */
@RestController
@RequestMapping("file")
public class FileUploadController {

	@Autowired
	private FileStoreageServiceImpl fileStorageService;

	@PostMapping("upload")
	public ResponseEntity<Result> upload(@RequestParam("file") MultipartFile file) {
		this.fileStorageService.save(file);
		return ResponseEntity.ok(new Result("Upload success"));
	}

	@GetMapping("files")
	public ResponseEntity<List<UploadFile>> files() {
		List<UploadFile> list = this.fileStorageService.load().map(path -> {
			String fileName = path.getFileName().toString();
			// 使用spring工具拼装附件地址
			String url = MvcUriComponentsBuilder
					.fromMethodName(this.getClass(), "getFile", path.getFileName().toString()).build().toString();
			return new UploadFile(fileName, url);
		}).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}

	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) {
		Resource resource = this.fileStorageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
