package com.example.project;


//import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class TestController {

    String path = "D://Java/test.txt";
    //File file = new File(path);

    @GetMapping("/test")
    public String test() {
        return "test work";
    }

    private Util util = new Util();


    @GetMapping(value = "/file")
    public @ResponseBody
    ResponseEntity<ByteArrayResource> getFile(@RequestParam(name = "delay") String value) throws IOException, InterruptedException {
        //InputStream in = getClass().getResourceAsStream(path);
        byte[] bytes = getBytes();
        int secondsToSleep = Integer.valueOf(value);

        try {

            Thread.sleep(secondsToSleep * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        //new Thread().join();

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("data").build().toString())
                .body(resource);


        //Thread.sleep(30000);

    }

    //@Scheduled(initialDelay = 5000, fixedRate = 3000)
    private byte[] getBytes() throws IOException {
        File initialFile = new File(path);
        InputStream in = new FileInputStream(initialFile);
        return IOUtils.toByteArray(in);
    }


//    public String readFile(){
//        return util.getFile(path);
//    }

}
