package study.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LineUtil {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
    /*
      统计文件中有效代码的行数，
      逐步扩展成为统计文件夹中文件有效行数及总字符数。
      
      第一步，打开文件夹，并过滤不需要统计的文件类型；
      第二步，遍历该文件数组，以行为单位读取该文件；
      第三步，判断空行，以//开头，以/*开头* /结尾，（以 } 开头，不太确定是否要加入到统计中）
    */

        try {
//      System.out.println(getEffectiveLine(new BufferedReader(new FileReader("E:/amp/www/.urls/.javascript/jquery.js"))));
            System.out.println(getEffectiveLine(new BufferedReader(new FileReader("E:/amp/www/.urls/.javascript/angular.js"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> getEffectiveLine(BufferedReader file) {
        String               line      = null;
        Map<String, Integer> map       = new HashMap<>();
        int                  sum       = 0;
        int                  empty     = 0;
        int                  note      = 0;
        int                  effective = 0;
        boolean              noting    = false;
        BufferedWriter       bufw      = null;
        try {
            bufw = new BufferedWriter(new FileWriter("E:/angular.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while ((line = file.readLine()) != null) {
                sum++;
                if (noting) {
                    note++;
                    if (line.endsWith("*/")) {
                        noting = false;
                    }
                } else {
                    if (line.matches("^\\s*$")) {
                        empty++;
                    } else if (line.trim().startsWith("//")) {
                        note++;
                    } else if (line.trim().startsWith("/*")) {
                        note++;
                        noting = true;
                    } else {
                        effective++;
                        bufw.write(line);
                        bufw.newLine();
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                file.close();
                bufw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("sum", sum);
        map.put("empty", empty);
        map.put("note", note);
        map.put("effective", effective);
        return map;
    }
}
