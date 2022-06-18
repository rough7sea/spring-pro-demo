package org.example.demo.aop.advice.mi;

public class WorkerBean {
    public void doWork(int times){
        for (int i = 0; i < times; i++) {
            this.work();
        }
    }

    private void work() {
        System.out.print("");
    }
}
