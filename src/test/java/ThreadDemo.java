import org.junit.Test;
import thread.DeadLock;
import thread.many2Many.Base;
import thread.many2Many.In;
import thread.many2Many.Out;
import thread.simple.Input;
import thread.simple.Output;
import thread.simple.Resource;

public class ThreadDemo {

    @Test
    public void simple() {
        Resource r = new Resource();
        Input in = new Input(r);
        Output out = new Output(r);
        new Thread(in).start();
        new Thread(in).start();
        new Thread(out).start();
        new Thread(out).start();
    }

    @Test
    public void deadLock() {
        DeadLock d = new DeadLock();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();
    }

    @Test
    public void many2Many() {
        Base b = new Base();
        In in1 = new In(b);
        In in2 = new In(b);
        Out out1 = new Out(b);
        Out out2 = new Out(b);
        new Thread(in1).start();
        new Thread(in1).start();
        new Thread(in2).start();
        new Thread(in2).start();
        new Thread(out1).start();
        new Thread(out1).start();
        new Thread(out2).start();
        new Thread(out2).start();
    }
}
