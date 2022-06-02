package com.aolifu.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.Test;


public class FlowableHelloWorld {
    
    @Test
    public void test1() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
    
    @Test
    public void test2() {
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                ;
        flow.subscribe(System.out::println);
    }
    
    @Test
    public void test3() throws InterruptedException {
        Flowable.fromCallable(() -> {
                    Thread.sleep(1000); //  imitate expensive computation
                    return "Done";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);
    
        Thread.sleep(2000); // <--- wait for the flow to finish
    }
    
    @Test
    public void test4() throws InterruptedException {
        Flowable<String> source = Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        });
    
        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());
    
        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());
    
        showForeground.subscribe(System.out::println, Throwable::printStackTrace);
    
        Thread.sleep(2000);
    }
    
    @Test
    public void test5() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);
    }
    
    @Test
    public void parallelProcessing() {
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
    }
    
    @Test
    public void test6() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);
    }
    
    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }
    
    public static void hello2(String... args) {
        Flowable.fromArray(args).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }
    
    @Test
    public void test7() {
        hello("c","d");
        hello2("a","b");
    }
    
    
}
