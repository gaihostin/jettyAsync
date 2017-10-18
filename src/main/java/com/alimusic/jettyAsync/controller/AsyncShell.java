package com.alimusic.jettyAsync.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date 17/10/18 15:38
 *
 * @author: shuhan.lyn@alibaba-inc.com
 */
public class AsyncShell {
    private static final Logger logger = LoggerFactory.getLogger(AsyncShell.class);


    public static void runShellCmd() {

        logger.info("start subjet interpose!");

        InputStreamReader stdInput = null;
        InputStreamReader errInput = null;
        Process process = null;
        String command = "sh -c /home/admin/redis-data-cache/bin/prc_trident_subject.sh";

        try {

            process = Runtime.getRuntime().exec(command);
            int exitValue = process.waitFor();

            String line = null;
            stdInput = new InputStreamReader(process.getInputStream());
            BufferedReader stdBuffer = new BufferedReader(stdInput);
            while ((line = stdBuffer.readLine()) != null) {
               logger.info(line);
            }

            errInput = new InputStreamReader(process.getErrorStream());
            BufferedReader errBR = new BufferedReader(errInput);
            while ((line = errBR.readLine()) != null) {
                logger.info(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stdInput != null) {
                    stdInput.close();
                }
                if (errInput != null) {
                    errInput.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e) {

                logger.info("正式执行命令：{} 有IO异常", command);
            }
        }
    }
}
