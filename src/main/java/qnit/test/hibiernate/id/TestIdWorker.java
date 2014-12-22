package qnit.test.hibiernate.id;

import java.util.concurrent.BrokenBarrierException; 
import java.util.concurrent.CountDownLatch; 
import java.util.concurrent.CyclicBarrier; 
import java.util.concurrent.TimeUnit; 

public class TestIdWorker { 
    public static void main(String []args){ 
    	TestIdWorker test = new TestIdWorker(); 
        test.test2(); 
    } 

    public void test2(){ 
        final IdWorker w = new IdWorker(1,2); 
        final CyclicBarrier cdl = new CyclicBarrier(100); 

        for(int i = 0; i < 100; i++){ 
            new Thread(new Runnable() { 
                @Override 
                public void run() { 
                try { 
                    cdl.await(); 
                } catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } catch (BrokenBarrierException e) { 
                    e.printStackTrace(); 
                } 
                System.out.println(w.nextId());} 
             }).start(); 
        } 
        try { 
            TimeUnit.SECONDS.sleep(5); 
        } catch (InterruptedException e) { 
           e.printStackTrace(); 
        } 

    } 
}
