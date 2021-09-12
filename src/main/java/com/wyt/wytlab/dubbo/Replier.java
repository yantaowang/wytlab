package com.wyt.wytlab.dubbo;

public interface Replier<T> {
    Object reply(T request);
}
