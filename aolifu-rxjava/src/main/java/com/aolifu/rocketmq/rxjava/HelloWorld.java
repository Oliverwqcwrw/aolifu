package com.aolifu.rocketmq.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HelloWorld {
    
    public static void main(String[] args) throws InterruptedException {
        
        // Flowable
        Flowable.just("Hello world").
                subscribe(System.out::println);
    
        Flowable.range(1, 6)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                .subscribe(System.out::println);
        
        Flowable.fromCallable(() -> {
                    Thread.sleep(1000); //  imitate expensive computation
                    return "Done";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);
    
        Thread.sleep(2000); // <--- wait for the flow to finish
        // Observable
        Observable.create(emitter -> {
                    while (!emitter.isDisposed()) {
                        long time = System.currentTimeMillis();
                        emitter.onNext(time);
                        if (time % 2 != 0) {
                            emitter.onError(new IllegalStateException("Odd millisecond!"));
                            break;
                        }
                    }
                })
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

}
