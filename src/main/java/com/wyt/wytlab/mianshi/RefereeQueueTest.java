package com.wyt.wytlab.mianshi;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RefereeQueueTest {
    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<WeakReference> weakReferenceList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setId(Math.round(Math.random() * 1000));
            WeakReference<User> weakReference = new WeakReference<User>(user, referenceQueue);
            weakReferenceList.add(weakReference);
        }
        WeakReference weakReference;
        Integer count = 0;
        while ((weakReference = (WeakReference) referenceQueue.poll()) != null) {
            System.out.println("JVM清理了" + weakReference + ",val:" + weakReference.get());
            count++;
        }
        System.out.println("count:" + count);
        User user = new User();
        user.setId(Math.round(Math.random() * 1000));
        System.out.println("last" + new WeakReference(user, referenceQueue).get());
    }
}
