package com.NamanJain.TubeFetch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class DownloadService {

    @Value("${download.directory}")
    private String downloadDir;

    public File downloadVideo(String url) throws IOException {
        // Create the download directory if it doesn't exist
        File dir = new File(downloadDir); // Creates a File object representing the directory.
        if (!dir.exists()) { //  Checks if the directory exists and creates it if it doesn't.
            dir.mkdirs();
        }


        Process process = new ProcessBuilder("java", "-version").start();
//        System.out.println("pb startted");
//        try {
////            int exitCode = process.waitFor();
//            System.out.println(exitCode + "naman");
//            System.out.println("pb started");
//            System.out.println(process.getOutputStream());
//            System.out.println("pb finished");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        process.getOutputStream().close();

        StringWriter output = new StringWriter();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            System.out.println("Buffer created");

            reader.transferTo(output);
        }
        System.out.println("Read lines from buffer");
        System.out.println("=".repeat(80));
        System.out.println(output);
        System.out.println("=".repeat(80));

        try {
            int exitVal = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Waited for process exit");


//        List<String> results = readOutput(process.getInputStream());
//
//        assertThat("Results should not be empty", results, is(not(empty())));
//        assertThat("Results should contain java version: ", results, hasItem(containsString("java version")));
//
//        int exitCode = process.waitFor();
//        assertEquals("No errors should be detected", 0, exitCode);
//

//        // Use yt-dlp to download the video
       String fileName = "downloaded_video.mp4"; // fileName is ASCII
//        ProcessBuilder pb = new ProcessBuilder();
//        pb.command("yt-dlp", "-o", downloadDir + "/" + fileName, url);
//        System.out.println("pb startted");
//        System.out.println(pb);
//        System.out.println("pb ended");
//        try {
//        // pb.redirectErrorStream(true);
//        Process process = pb.start();
//            process.waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        File downloadedFile = new File(downloadDir, fileName);

        System.out.println("a");

        if (downloadedFile.exists() && downloadedFile.isFile()) {
            // String localFilePath = "C:\\Users\\DELL 5470\\Downloads\\downloaded_video.mp4"; // Replace with your desired local file path
            File localFile = new File(downloadDir);
            try {
                Files.copy(downloadedFile.toPath(), localFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Failed to save downloaded video to local file.");
            }

            // Return the local file object
            return downloadedFile;
        } else {
            throw new IOException("Failed to download video");
        }
    }
}


/* package com.NamanJain.TubeFetch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DownloadService {

    @Value("${download.directory}") // Injects the value from application.properties into the downloadDir variable.
    private String downloadDir;

    public File downloadVideo(String url) throws IOException {
        // Create the download directory if it doesn't exist
        File dir = new File(downloadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Use yt-dlp to download the video
        String fileName = "downloaded_video.mp4"; // Name of the downloaded video file
        ProcessBuilder pb = new ProcessBuilder(
                "yt-dlp",
                "-o", downloadDir + "/" + fileName,
                url
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the downloaded file (assuming only one file is downloaded)
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            File downloadedFile = files[0];

            // Optionally, read and save the content using BufferedReader
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(downloadedFile)))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Process each line if needed
                    content.append(line).append("\n");
                }

                // Example: Writing content to a file on local disk
                String localFilePath = "/path/to/save/downloaded_video.mp4"; // Replace with your desired local file path
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(localFilePath))) {
                    writer.write(content.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Failed to read or write downloaded video content.");
            }

            // Return the downloaded file
            return downloadedFile;
        } else {
            throw new IOException("Failed to download video");
        }
    }
}
*/