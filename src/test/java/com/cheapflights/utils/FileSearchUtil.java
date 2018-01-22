package com.cheapflights.utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSearchUtil {

    public static void testFirst(String folderPath, String extention){
        JsonUtil.setFileName(getDirectoryFiles(folderPath, extention).get(0));

    }

    public static void testAll(String folderPath, String extention){
        while (getDirectoryFiles(folderPath, extention).iterator().hasNext()){
            JsonUtil.setFileName(getDirectoryFiles(folderPath, extention).iterator().next());
        }
    }

    public static List<String> getDirectoryFiles(String folderPath, String extention){
        List<String> files = new ArrayList<>();
        try {
            DirectoryStream<Path> stream= Files.newDirectoryStream(Paths.get(folderPath),
                    path -> path.toString().endsWith(extention));
            for (Path file : stream){
                files.add(file.toString());
                System.out.println(files);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


}
