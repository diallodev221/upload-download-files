package com.diallodev.uploaddownloadfiles.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.nio.file.Path;


public class ResourceResponse {
    private Path filePath;
    private Resource resource;
    private HttpHeaders httpHeaders;

    public ResourceResponse() {
    }

    public ResourceResponse(Path filePath, Resource resource, HttpHeaders httpHeaders) {
        this.filePath = filePath;
        this.resource = resource;
        this.httpHeaders = httpHeaders;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
