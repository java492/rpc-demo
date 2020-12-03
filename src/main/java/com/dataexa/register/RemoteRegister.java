package com.dataexa.register;

import com.dataexa.framework.URL;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * @Author 胡志成
 * @Date 2020/11/6
 */
@Component
public class RemoteRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    public static void register(String interfaceName, URL url){
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);
        REGISTER.put(interfaceName, list);
        saveFile();
    }

    public static List<URL> getUrlList(String interfaceName) {
        REGISTER = getFile();
        assert REGISTER != null;
        return REGISTER.get(interfaceName);
    }


    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
