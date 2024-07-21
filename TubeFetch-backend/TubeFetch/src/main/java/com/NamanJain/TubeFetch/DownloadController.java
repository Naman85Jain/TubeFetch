package com.NamanJain.TubeFetch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @PostMapping("/download")
    public ResponseEntity<InputStreamResource> downloadVideo(@RequestBody DownloadObject url) throws IOException {
        File videoFile = downloadService.downloadVideo(url.url);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(videoFile));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + videoFile.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(videoFile.length())
                .body(resource);
    }
}

