package com.ensof;

/*@SpringBootApplication
@EnableScheduling
public class ProjectApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}*/

import com.ensof.help.MetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class EnsofApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        File dir = new File("log");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        if (!dir.exists())
            dir.mkdir();
        File file = new File("log/weblog" + simpleDateFormat.format(c.getTime()) + ".log");
        BufferedReader br = null;
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(file));
            System.setOut(printStream);
            System.setErr(printStream);
            br = new BufferedReader(new FileReader("conf/application.properties"));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.contains("msgs.path")) {
                    MetaData.readMsgMeta(line.split("=")[1], "/", 1, ".metaPacket", "UTF-8");
                } else if (line.contains("config.path")) {
                    MetaData.readMsgMeta(line.split("=")[1], "/", 2, "service.ini", "EUC-KR");

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        SpringApplication.run(EnsofApplication.class, args);


    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {


        return builder.sources(EnsofApplication.class);
    }
}
