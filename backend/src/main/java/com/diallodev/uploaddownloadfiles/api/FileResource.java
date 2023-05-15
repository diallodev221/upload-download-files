package com.diallodev.uploaddownloadfiles.api;

import com.diallodev.uploaddownloadfiles.dto.ResourceResponse;
import com.diallodev.uploaddownloadfiles.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;


@RestController
@RequestMapping("file")
public class FileResource {

    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";

    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    // define a method to upload files
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) {
        List<String> filenames = fileService.uploadFiles(multipartFiles);
        return ResponseEntity.ok().body(filenames);
    }

    // define a method to download files
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {

        var response = fileService.downloadFiles(filename);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(response.getFilePath())))
                .header(String.valueOf(response.getHttpHeaders())).body(response.getResource());
    }
}
