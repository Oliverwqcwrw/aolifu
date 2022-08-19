package com.aolifu.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import lombok.SneakyThrows;
import org.junit.Test;

public class Javassist1Test {
    
    @Test
    @SneakyThrows
    public void setSuperClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.aolifu.javassist.Cat");
        cc.defrost();
        cc.setSuperclass(pool.get("com.aolifu.javassist.Animal"));
        cc.writeFile();
        System.out.println(cc.getSuperclass());
    }
    
    @Test
    @SneakyThrows
    public void obtainByteCode() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.aolifu.javassist.Cat");
        System.out.println(new String(cc.toBytecode()));
        System.out.println(cc.toClass());
    }
    
    @Test
    @SneakyThrows
    public void defineClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.aolifu.javassist.Dog");
        System.out.println(cc.toClass());
    }
    
    @Test
    @SneakyThrows
    public void defrost() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.aolifu.javassist.Pig");
        cc.setSuperclass(pool.get("com.aolifu.javassist.Animal"));
        cc.writeFile();
        cc.defrost();
        cc.setSuperclass(pool.get("com.aolifu.javassist.Person"));
        System.out.println(cc.getSuperclass());
    }
    
    @Test
    @SneakyThrows
    public void stopPrun() {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.aolifu.javassist.Chicken");
        cc.stopPruning(true);
        cc.writeFile();
    }
}
