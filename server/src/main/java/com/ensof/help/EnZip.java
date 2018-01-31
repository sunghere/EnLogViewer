package com.ensof.help; /**
 * Title:        중계시스템 자바 버젼
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      enSOF technology co.
 *
 * @author 강천수
 * @version 1.0
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class EnZip {
    static byte[] hexValue = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static final byte[] compress(byte[] src) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zout = new ZipOutputStream(out);
        zout.putNextEntry(new ZipEntry("0"));
        zout.write(src);
        zout.closeEntry();
        byte[] compressed = out.toByteArray();
        zout.close();

        return compressed;
    }

    public static final byte[] compress(String str) throws IOException {
        return compress(str.getBytes());
    }

    public static final byte[] decompressbyte(byte[] compressed) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        ZipInputStream zin = new ZipInputStream(in);
        zin.getNextEntry();
        byte[] buffer = new byte[1024];
        int offset = -1;
        while ((offset = zin.read(buffer)) != -1) {
            out.write(buffer, 0, offset);
        }
        byte[] decompressed = out.toByteArray();
        out.close();
        zin.close();

        return decompressed;
    }

    public static final String decompress(byte[] compressed) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        ZipInputStream zin = new ZipInputStream(in);
        zin.getNextEntry();
        byte[] buffer = new byte[1024];
        int offset = -1;
        while ((offset = zin.read(buffer)) != -1) {
            out.write(buffer, 0, offset);
        }
        String decompressed = out.toString();
        out.close();
        zin.close();

        return decompressed;
    }

    public static final byte[] byte2hexstream(byte[] data) {
        int value = 0;
        byte[] result = new byte[data.length * 2];
        int k = 0;
        for (int i = 0; i < data.length; i++) {
            value = (data[i] >> 4) & 0xF;
            result[k++] = hexValue[value];
            value = data[i] & 0xF;
            result[k++] = hexValue[value];
        }

        return result;
    }

    public static final String byte2hexstr(byte[] data) {
        return (new String(byte2hexstream(data)));
    }

    public static final byte[] hexstream2byte(byte[] data) throws ArrayIndexOutOfBoundsException {
        return hexstr2byte(new String(data));
    }

    public static final byte[] hexstr2byte(String data) throws ArrayIndexOutOfBoundsException {
        byte[] srcdata = data.getBytes();
        byte[] result = new byte[srcdata.length / 2];
        int k = 0;
        for (int i = 0; i < result.length; i++) {
            k = i * 2;
            result[i] = (byte) (Integer.parseInt(data.substring(k, k + 2), 16));
        }

        return result;
    }

    public static final byte[] zip(byte[] src) throws IOException {
        if (src == null) {
            return null;
        }

        byte[] comp = compress(src);
        byte[] hexstream = byte2hexstream(comp);

        return hexstream;
    }

    public static final String zip(String str) throws IOException {
        byte[] comp = compress(str);
        String hexstr = byte2hexstr(comp);

        return hexstr;
    }

    public static final byte[] unzip(byte[] src) throws IOException {
        byte[] bytedata = null;
        try {
            bytedata = hexstr2byte(new String(src));
        } catch (Exception ex) {
            return null;
        }

        return decompressbyte(bytedata);
    }

    public static final String unzip(String str) throws IOException {
        byte[] bytedata = null;
        try {
            bytedata = hexstr2byte(str);
        } catch (Exception ex) {
            return null;
        }

        return decompress(bytedata);
    }
}
