package study.lang;

class Resource {
	private int num = 20;

	public synchronized void in(){
		while (this.num>99){
			try{
				wait();
			} catch (Exception e){
				
			}
		}
		System.out.println("生产者 生产..."+(++this.num));
		notify();
	}
	
	public synchronized void out(){
		while (this.num<1){
			try {
				wait();
			} catch (Exception e) {
				
			}
		}
		System.out.println("消费者 消费..."+(--this.num));
		notify();
	}
}

class Input implements Runnable {
	
	private Resource r = null;
	
	public Input(Resource r) {
		super();
		this.r = r;
	}

	public void run() {
		while(true){
			r.in();
		}
	}
}

class Output implements Runnable {
	
	private Resource r = null;
	
	public Output(Resource r) {
		super();
		this.r = r;
	}

	public void run() {
		while(true){
			r.out();
		}
	}
}

public class CreateSimpleThreadDemo {

	public static void main(String[] args) {

		Resource r = new Resource();
		Input in = new Input(r);
		Output out = new Output(r);
		new Thread(in).start();
		new Thread(in).start();
		new Thread(out).start();
		new Thread(out).start();
	}
}
