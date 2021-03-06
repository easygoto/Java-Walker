package example.justcompile;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Random;

/**
 * @author trink
 */
public class Compile {

    public static void main(String[] args) throws Exception {
        // 生成文件
        String src = "package example.justcompile;\n" +
                "\n" +
                "public class Person {\n" +
                "\n" +
                "    private String name;\n" +
                "    private int    age;\n" +
                "    private Cat    cat;\n" +
                "\n" +
                "    public Person(Cat cat) {\n" +
                "        this.cat = cat;\n" +
                "    }\n" +
                "\n" +
                "    public Cat getCat() {\n" +
                "        return cat;\n" +
                "    }\n" +
                "\n" +
                "    public Person setCat(Cat cat) {\n" +
                "        this.cat = cat;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public Person setName(String name) {\n" +
                "        this.name = name;\n" +
                "        return this;\n" +
                "    }\n" +
                "\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "\n" +
                "    public Person setAge(int age) {\n" +
                "        this.age = age;\n" +
                "        return this;\n" +
                "    }\n" +
                "}";
        String path = System.getProperty("user.dir") + "/src/main/java/example/justcompile/Person.java";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
        writer.write(src);
        writer.flush();
        writer.close();

        // 编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> utils = fileManager.getJavaFileObjects(path);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, utils);
        task.call();
        fileManager.flush();
        fileManager.close();

        // 加载到内存
        URL[] urls = new URL[]{
                new URL("file:/" + System.getProperty("user.dir") + "/src?t=" + new Random().nextFloat())
        };
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("example.justcompile.Person");
        System.out.println(clazz);

//        Constructor constructor = clazz.getConstructor(Cat.class);
//        Person person = (Person) constructor.newInstance(new Cat());
//        System.out.println(person.setCat(new Cat().setName("123")).getCat().getName());
    }
}
