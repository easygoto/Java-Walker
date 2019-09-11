package study.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

public class FileDemo {

    public static void main(String[] args) {

        String pathname = "D:" + File.separator + "study";
        File   dir      = new File(pathname);

        for (File file : Objects.requireNonNull(dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                System.out.println(!pathname.isHidden());
                return false;
            }
        }))) {
            System.out.println(file);
        }
    }
}
