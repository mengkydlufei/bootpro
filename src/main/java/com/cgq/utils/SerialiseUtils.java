package com.cgq.utils;

import java.io.*;

public class SerialiseUtils {

    public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(o);
        return byteArrayOutputStream.toByteArray();
    }


    public static Object unserialize(byte[] bytes) throws Exception{
        Object o = null;
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayOutputStream);
        o = inputStream.readObject();
        return o;
    }
}
