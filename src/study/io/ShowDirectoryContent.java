package study.io;

import java.io.File;

public class ShowDirectoryContent {

    public static void main(String[] args) {

//    deleteAll(new File("D:/test"));
        getAll(new File("F:\\myLife\\[airi]\\2017 1009- 分享趣\\分享趣\\设计\\C端"), 1);
    }

    public static void deleteAll(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteAll(file);
            }
            System.out.println(dir.getAbsolutePath() + ":" + (dir.delete() ? "删除成功" : "删除失败"));
        } else {
            System.out.println(dir.getAbsolutePath() + ":" + (dir.delete() ? "删除成功" : "删除失败"));
        }
    }

    public static void getAll(File dir, int index) {
        if (dir.isDirectory()) {
            System.out.println(tree(index++) + dir.getName());
            for (File file : dir.listFiles()) {
                getAll(file, index);
            }
        } else {
            System.out.println(dir.getName());
//      System.out.println(tree(index)+dir.getName());
        }
    }

    public static String tree(int len) {
        StringBuilder sb = new StringBuilder();
        if (len > 0) {
            while ((--len) > 0) {
                sb.append("|  ");
            }
            sb.append("|--");
        }
        return sb.toString();
    }
}