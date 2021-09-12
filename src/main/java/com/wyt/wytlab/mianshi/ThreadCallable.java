package com.wyt.wytlab.mianshi;

import java.util.concurrent.Callable;

public class ThreadCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 2048;
    }
}
