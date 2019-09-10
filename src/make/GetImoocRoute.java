package make;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetImoocRoute {

    public static void main(String[] args) {
        BufferedWriter bufw = null;
        try {
            bufw = new BufferedWriter(new FileWriter("C:/imooc.html"));
            bufw.write("<!DOCTYPE html><html><head><title>imooc学习路径大全</title><meta charset='utf-8'></head><body><h1>访问网址为http://www.imooc.com/course/programdetail/pid/某数字</h1><br>");
            for (int i = 1; i < 500; i++) {
                String str = findTitle("http://www.imooc.com/course/programdetail/pid/" + i).toString();
                if (str.equals("[]")) {
                    continue;
                }
                str = str.substring(1, str.length() - 1);
                String[] titles = str.split(",");
                bufw.write(i + "<br>");
                for (String title : titles) {
                    bufw.write(title + "<br>");
                }
                bufw.write("<br><br>");
            }
            bufw.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufw != null;
                bufw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> findTitle(String url) {
        ArrayList<String> list   = new ArrayList<>();
        BufferedReader    reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern bigTitle    = Pattern.compile("<h2>.+</h2>");
                Pattern smallTitle  = Pattern.compile("<h3 class=\"course-card-name\">(.+)</h3>");
                Matcher bigTitles   = bigTitle.matcher(line);
                Matcher smallTitles = smallTitle.matcher(line);
                while (bigTitles.find()) {
                    list.add(bigTitles.group());
                }
                while (smallTitles.find()) {
                    list.add(smallTitles.group(1));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
