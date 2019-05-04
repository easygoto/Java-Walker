package com.trink.Proxy.ImitateJDK;

import com.trink.Proxy.Simple.Movable;
import com.trink.Proxy.Simple.Tank;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;

public class Proxy {

    private static final String LINE_BREAK = "\n";

    public static void newProxyInstance(Class interfaces) throws Exception {
        String interfaceName = interfaces.getName();

        Method[]      methods           = interfaces.getMethods();
        StringBuilder methodBuilderText = new StringBuilder();
        for (Method method : methods) {
            Parameter[]   parameters         = method.getParameters();
            StringBuilder stateParameterText = new StringBuilder();
            StringBuilder useParameterText   = new StringBuilder();
            for (Parameter parameter : parameters) {
                String parameterName = parameter.getName();
                String parameterType = parameter.getType().getName();
                stateParameterText.append(parameterType).append(" ").append(parameterName).append(", ");
                useParameterText.append(parameterName).append(", ");
            }
            if (stateParameterText.length() > 2) {
                stateParameterText.delete(stateParameterText.length() - 2, stateParameterText.length());
            }
            if (useParameterText.length() > 2) {
                useParameterText.delete(useParameterText.length() - 2, useParameterText.length());
            }

            String methodName = method.getName();
            String returnType = method.getReturnType().getName();
            methodBuilderText
                    .append("    @Override").append(LINE_BREAK)
                    .append("    public ").append(returnType).append(" ").append(methodName).append("(").append(stateParameterText).append(") {").append(LINE_BREAK)
                    .append("        long startTime = System.currentTimeMillis();").append(LINE_BREAK);
            if (!returnType.equals("void")) {
                methodBuilderText
                        .append("        ").append(returnType).append(" result = inter.").append(methodName).append("(").append(useParameterText).append(");").append(LINE_BREAK);
            } else {
                methodBuilderText
                        .append("        inter.").append(methodName).append("(").append(useParameterText).append(");").append(LINE_BREAK);
            }
            methodBuilderText
                    .append("        long stopTime = System.currentTimeMillis();").append(LINE_BREAK)
                    .append("        System.out.println(\"Run Time: \" + (stopTime - startTime) + \" ms\");").append(LINE_BREAK);
            if (!returnType.equals("void")) {
                methodBuilderText.append("        return result;").append(LINE_BREAK);
            }
            methodBuilderText.append("    }").append(LINE_BREAK).append(LINE_BREAK);
        }
        methodBuilderText.delete(methodBuilderText.length() - 1, methodBuilderText.length());
        String methodText = methodBuilderText.toString();

        String src = "package com.trink.Proxy;" + LINE_BREAK + LINE_BREAK +
                "public class TempProxy implements " + interfaceName + " {" + LINE_BREAK + LINE_BREAK +
                "    private " + interfaceName + " inter;" + LINE_BREAK + LINE_BREAK +
                "    public TempProxy(" + interfaceName + " inter) {" + LINE_BREAK +
                "        super();" + LINE_BREAK +
                "        this.inter = inter;" + LINE_BREAK +
                "    }" + LINE_BREAK + LINE_BREAK + methodText +
                "}" + LINE_BREAK;
        String         path   = System.getProperty("user.dir") + "/src/main/java/com/trink/Proxy/TempProxy.java";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
        writer.write(src);
        writer.flush();
        writer.close();

        // 编译
        JavaCompiler                 compiler    = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager      fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable                     utils       = fileManager.getJavaFileObjects(path);
        JavaCompiler.CompilationTask task        = compiler.getTask(null, fileManager, null, null, null, utils);
        task.call();
        fileManager.close();

        // 加载到内存
        URL[] urls = new URL[]{
                new URL("file:/" + System.getProperty("user.dir") + "/src/")
        };
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class          clazz       = classLoader.loadClass("com.trink.Proxy.TempProxy");
        System.out.println(clazz);

        Constructor constructor = clazz.getConstructor(Movable.class);
        Movable     movable     = (Movable) constructor.newInstance(new Tank());
        movable.move();
        movable.stop();
    }
}
