package com.ensof.help;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;

/**
 * Created by SungHere on 2017-12-07.
 */
@Repository
public class MetaData {

    private static HashMap<String, String> msgData = new HashMap<>();

    private static HashMap<String, HashMap<String, String>> orgData = new HashMap<>();


    public static void clear(String command) {

        if (command.equals("ORG")) orgData.clear();

        else if (command.equals("MSG")) msgData.clear();
    }

    public static String getOrgName(String orgCode) {
        String result = orgData.get(orgCode).get("desc");

        return result;
    }

    public static HashMap<String, HashMap<String, String>> getOrgData() {
        return orgData;
    }

    public static String getMsgName(String path) {

        String name = msgData.get(path);
        return name;
    }

    public static void readMsgMeta(String srcDirName, String subDir, int flag, String name, String type) throws Exception {

        File dir = new File(srcDirName);
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];

            if (file.isDirectory()) {
                System.out.println("srcDirName = " + srcDirName + file.getName() + "/");

                readMsgMeta(srcDirName + "/" + file.getName() + "/", subDir + file.getName() + "/", flag, name, type);
            } else if (file.isFile()) {
                if (!file.getName().contains(name)) {
                    continue;
                }
                System.out.println(srcDirName + "/" + file.getName());

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcDirName + "/" + file.getName()), type));


                if (flag == 1) {
                    msgDataProcess(br, subDir);
                } else if (flag == 2) {
                    orgDataProcess(br, subDir);
                }
                br.close();

            }
        }


    }

    private static void orgDataProcess(BufferedReader br, String subdir) throws IOException {
        String line = "";
        String key = "";
        HashMap<String, String> fields = new HashMap<>();

        while ((line = br.readLine()) != null) {


            if (line.substring(0, 1).equals("[")) {

                String result = line.replaceAll("\\[", "");
                result = result.replaceAll("\\]", "");


                if (!key.equals("")) { // 키 초기화 작업
                    if (orgData.containsKey(key)) { // 중복이름인 경우
                        String[] tempSplit = subdir.split("/");

                        for (String split : tempSplit) {
                            if (split.length() > 2) {
                                key = split + "/" + key;
                                break;
                            }
                        }

                    }

                    System.out.println("[" + key + "]저장" + "next : " + result);
                    orgData.put(key, fields);
                    fields = new HashMap<>();
                }


                key = result;


            } else {
                String[] temp = line.split("=");

                if (temp.length > 1) {
                    fields.put(temp[0], temp[1]);
                } else if (temp.length == 1) {
                    fields.put(temp[0], "");


                }
            }


        }

        orgData.put(key, fields);

    }


    public static void msgDataProcess(BufferedReader br, String subDir) throws IOException {
        String line = "";
        while ((line = br.readLine()) != null) {


            String[] data = line.split("\\^")[0].split(".xml\\|desc=");

            if (!data[0].equals(""))
                if (data.length == 2) {

                    msgData.put(subDir + data[0], data[1]);
                    System.out.println(subDir + data[0] + ":" + data[1]);
                } else if (data.length == 1) {

                    msgData.put(subDir + data[0], "");
                }
        }

    }
}

