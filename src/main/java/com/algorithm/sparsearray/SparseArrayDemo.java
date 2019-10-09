package com.algorithm.sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 稀疏数组(稀疏矩阵)
 *
 * @author yusong
 * @date 2019/10/2 23:51
 */
public class SparseArrayDemo {

    public static void main(String[] args) {
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = 2;

        System.out.println("原始数组: ");
        printArr(arr);
        int[][] sparseArr = toSparseArr(arr);
        System.out.println();

        System.out.println("稀疏数组: ");
        printArr(sparseArr);
        int[][] arr2 = toArr(sparseArr);
        System.out.println();

        System.out.println("还原的数组：");
        printArr(arr2);

        String url = "D:\\work\\ideaWorks\\algorithm\\src\\test\\resources\\map.data";
        write2File(sparseArr, url);
        System.out.println("写入文件成功");

        int[][] sparseArr2 = readFormFile(url);
        printArr(sparseArr2);
        System.out.println("读取文件成功");
    }


    public static int[][] toArr(int[][] sparseArr) {
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

    public static int[][] toSparseArr(int[][] arr) {
        int sum = 0;
        for (int[] ints : arr) {
            sum += Arrays.stream(ints).filter(num -> num != 0).count();
        }
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }
        return sparseArr;
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void write2File(int[][] arr, String url) {
        StringBuilder builder = new StringBuilder();
        for (int[] arr1 : arr) {
            Arrays.stream(arr1).forEach(item -> builder.append(item).append(" "));
            builder.append("\r\n");
        }
        try (FileOutputStream out = new FileOutputStream(new File(url))) {
            out.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] readFormFile(String url) {
        int[][] sparseArr = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(url))) {
            List<String> stringList = bufferedReader.lines().collect(Collectors.toList());
            sparseArr = new int[stringList.size()][3];
            int curRow = 0;
            for (String str : stringList) {
                String[] split = str.split(" ");
                for (int i = 0; i < split.length; i++) {
                    sparseArr[curRow][i] = Integer.parseInt(split[i].trim());
                }
                curRow ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sparseArr;
    }
}