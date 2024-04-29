package com.pblgllgs.backend.book.file;
/*
 *
 * @author pblgl
 * Created on 29-04-2024
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)){
            return null;
        }
        try{
            Path filePath =  new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        }catch (IOException e){
            log.info("No fille found in the path {}, fileUrl");
        }
        return new byte[0];
    }
}
