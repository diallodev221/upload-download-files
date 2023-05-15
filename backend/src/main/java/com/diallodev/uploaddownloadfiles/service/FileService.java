package com.diallodev.uploaddownloadfiles.service;

import com.diallodev.uploaddownloadfiles.dto.ResourceResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

@Service
public class FileService {

    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";

    //

    public List<String> uploadFiles(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .map(file -> {
                    String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
                    try {
                        copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return filename;
                }).toList();

    }

    public ResourceResponse downloadFiles(String filename) throws FileNotFoundException, MalformedURLException {
        Path filePath = get(DIRECTORY).toAbsolutePath().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on th server");
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return new ResourceResponse(filePath, resource, httpHeaders);
    }
}
