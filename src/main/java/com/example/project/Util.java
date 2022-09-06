package com.example.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util {


    String path = "D://Java/test.txt";
    File file = new File(path);

    public String getFile(String path) {
        Scanner scanner;
        String testfile = null;

            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine())
                {
                    testfile = scanner.nextLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //System.out.println("testFile " + testfile);
        return testfile;


    }



}
