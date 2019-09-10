package study.util;

interface Inter<KKK> {
	public void show(KKK k);
}

class Tool<QQ> {

	private QQ q;

	public Tool(QQ q) {
		super();
		this.q = q;
	}

	public QQ getQ() {
		return q;
	}

	public Tool<QQ> setQ(QQ q) {
		this.q = q;
		return this;
	}

	public void show(QQ q) {
		System.out.println(q);
	}
}

class Computer {

	private String name = null;
	private int price = 0;

	public Computer(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Computer setName(String name) {
		this.name = name;
		return this;
	}

	public int getPrice() {
		return price;
	}

	public Computer setPrice(int price) {
		this.price = price;
		return this;
	}

	public String toString() {
		return this.name + " computer is " + this.price;
	}
}

class InterImpl<KKK> implements Inter<KKK> {

	@Override
	public void show(KKK k) {
		System.out.println(k);
	}
}

public class GenericsDemo {

	public static void main(String[] args) {

		Tool<Computer> com = new Tool<>(new Computer("Acer", 5000));
		InterImpl<String> str = new InterImpl<>();
		InterImpl<Computer> com2 = new InterImpl<>();
		str.show("123456");
		com2.show(new Computer("Acer", 5000));

		System.out.println(com.getQ().setName("hehe").getName());
		com.show(new Computer("Acer", 5000));
	}
}