package com.trink.Proxy.ImitateJDK;

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

    public static Object newProxyInstance(Class interfaces, InvocationHandler handler) throws Exception {
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
                    .append("        Method method;").append(LINE_BREAK)
                    .append("        try {").append(LINE_BREAK)
                    .append("            method = ").append(interfaceName).append(".class.getMethod(\"").append(methodName).append("\");").append(LINE_BREAK)
                    .append("            handler.invoke(this, method);").append(LINE_BREAK)
                    .append("        } catch (NoSuchMethodException e) {").append(LINE_BREAK)
                    .append("            e.printStackTrace();").append(LINE_BREAK)
                    .append("        }").append(LINE_BREAK)
                    .append("    }").append(LINE_BREAK).append(LINE_BREAK);
        }
        methodBuilderText.delete(methodBuilderText.length() - 1, methodBuilderText.length());
        String methodText = methodBuilderText.toString();

        String src = "package com.trink.Proxy.ImitateJDK.Cache;" + LINE_BREAK + LINE_BREAK +
                "import java.lang.reflect.Method;" + LINE_BREAK + LINE_BREAK +
                "import com.trink.Proxy.ImitateJDK.InvocationHandler;" + LINE_BREAK + LINE_BREAK +
                "public class TempProxy implements " + interfaceName + " {" + LINE_BREAK + LINE_BREAK +
                "    private InvocationHandler handler;" + LINE_BREAK + LINE_BREAK +
                "    public TempProxy(InvocationHandler handler) {" + LINE_BREAK +
                "        super();" + LINE_BREAK +
                "        this.handler = handler;" + LINE_BREAK +
                "    }" + LINE_BREAK + LINE_BREAK + methodText +
                "}" + LINE_BREAK;
        String         path   = System.getProperty("user.dir") + "/src/main/java/com/trink/Proxy/ImitateJDK/Cache/TempProxy.java";
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
        fileManager.flush();
        fileManager.close();

        // 加载到内存
        URL[] urls = new URL[]{
                new URL("file:/" + System.getProperty("user.dir") + "/src/")
        };
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class          clazz       = classLoader.loadClass("TempProxy");
//        Class          clazz       = Class.forName("com.trink.Proxy.ImitateJDK.Cache.TempProxy");
//        System.out.println(clazz);

        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        return constructor.newInstance(handler);
    }
}
