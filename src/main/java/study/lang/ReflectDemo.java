package study.lang;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

interface Office {
	void start();

	void end();
	
	int count(int i, float f, boolean b, Object o, double[] d);
}

class Word implements Office {

	@Override
	public void start() {
		System.out.println("word start");
	}

	@Override
	public void end() {
		System.out.println("word end");
	}

	@Override
	public int count(int i, float f, boolean b, Object o, double[] d) {
		// TODO Auto-generated method stub
		return 0;
	}

}

class Excel implements Office {

	@Override
	public void start() {
		System.out.println("excel start");
	}

	@Override
	public void end() {
		System.out.println("excel end");
	}

	@Override
	public int count(int i, float f, boolean b, Object o, double[] d) {
		// TODO Auto-generated method stub
		return 0;
	}

}

public class ReflectDemo {

	public static void main(String[] args) {

		String office = "tests.lang.Word";
		try {
			Class<?> klass = Class.forName(office);
			show((Office) klass.newInstance());
			/*
			 * for (Method method : klass.getMethods()) {
			 * System.out.println(method.getName()); }
			 */
			for (Method method : klass.getDeclaredMethods()) {
				StringBuilder sb = new StringBuilder();
				sb.append(method.getReturnType());
				sb.append(" ");
				sb.append(method.getName());
				sb.append("(");
				for (Type type : method.getGenericParameterTypes()) {
					sb.append(type);
					sb.append(", ");
				}
				if (method.getParameterTypes().length > 0) {
					sb.delete(sb.length()-2, sb.length());
				}
				sb.append(")");
				
				System.out.println(sb.toString());
			}
			// System.out.println(klass.getMethods());
			// System.out.println(klass.getDeclaredMethods().toString());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static void show(Office klass) {
		klass.start();
		klass.end();
	}
}
