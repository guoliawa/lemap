package com.restful.smarthome.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File getFile(MultipartFile file, String userid)
            throws IOException {

        String tmpPath = System.getProperties().getProperty("java.io.tmpdir")
                
                + File.separator + userid + File.separator + "tmp";
        File tmpDir = new File(tmpPath);

        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        byte[] buffer = new byte[1024];

        if (!file.getOriginalFilename().endsWith(".zip")) {

            File newFile = new File(tmpPath + File.separator
                    + file.getOriginalFilename());

            if (newFile.exists()) {
                newFile.delete();
            }

            newFile.createNewFile();
            file.transferTo(newFile);
            return newFile;
        } else {
            logger.info("The userid \""
                    + userid
                    + "\""
                    + "uploaded an compacted file, file needs to be extracted firstly.");

            ZipInputStream zis = new ZipInputStream(file.getInputStream());
            /*
             * Suppose the client only transfer one file in ZIP file. So only
             * get one entry default, the other processing methods will be added
             * lately.
             */
            ZipEntry ze = zis.getNextEntry();
            String fileName = ze.getName();
            File newFile = new File(tmpPath + File.separator + fileName);

            if (newFile.exists()) {
                newFile.delete();
            }

            logger.info("file unzip : " + newFile.getAbsoluteFile());

            new File(newFile.getParent()).mkdirs();
            newFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();
            zis.closeEntry();
            zis.close();
            return newFile;
        }
    }
}
