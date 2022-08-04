package com.ssf.week1.workshop13.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

    // method that creates directory
    public static void createDir(String dataDir) {
        File dir = new File(dataDir);
        boolean isDirCreated = dir.mkdirs();
        logger.info("isDirCreated > " + isDirCreated);
        if (isDirCreated) {         // set permissions
            String osName = System.getProperty("os.name");
            if (!osName.contains("Windows")) {
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                } catch (IOException e) {
                    logger.error("Error: " + e);
                }
            }
        }
    }
}
