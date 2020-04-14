package com.zz.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author cy
 */
public class UploadUtils {
    public static synchronized void upload(MultipartFile blobFile, String path) throws IOException, FileNotFoundException {
        InputStream in = null;
        in = blobFile.getInputStream();
        OutputStream out = null;
        out = new FileOutputStream(new File(path));
        int len = 0;
        byte[] buf = new byte[3 * 1024];
        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        out.close();
        in.close();
    }
}
