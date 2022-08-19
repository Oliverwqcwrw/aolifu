package com.aolifu.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ObservableHelloWorld {
    
    @Test
    public void test1(){
    
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
    
    @Test
    public void test2()  {
        AtomicInteger count = new AtomicInteger();
    
        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.just(count.get()))
                .subscribe(System.out::println);
    }
    
    @Test
    public void test3() {
        AtomicInteger count = new AtomicInteger();
        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.defer(() -> Single.just(count.get())))
                .subscribe(System.out::println);
    }
    
    @Test
    public void test4() {
        AtomicInteger count = new AtomicInteger();
    
        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.fromCallable(() -> count.get()))
                .subscribe(System.out::println);
    }
    
    @Test
    public void test5() {
        Observable<String> o = Observable.fromArray("a", "b", "c");
    
        Integer[] list = {5, 6, 7, 8};
        Observable<Integer> o2 = Observable.fromArray(list);
    
        Observable<String> o3 = Observable.just("one object");
    }
    
}
