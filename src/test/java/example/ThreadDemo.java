package example;

import example.thread.DeadLock;
import org.junit.Test;
import example.thread.many2many.Base;
import example.thread.many2many.In;
import example.thread.many2many.Out;
import example.thread.simple.Input;
import example.thread.simple.Output;
import example.thread.simple.Resource;

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
