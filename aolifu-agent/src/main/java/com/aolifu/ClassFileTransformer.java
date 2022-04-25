package com.aolifu;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.security.ProtectionDomain;

public class ClassFileTransformer implements java.lang.instrument.ClassFileTransformer {
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer){
        if (!"com/aolifu/SkywalkingTest".equals(className)) {
            // 非SkywalkingTest不拦截
            return null;
        }
        final ClassPool cp = ClassPool.getDefault();
        try {
            final CtClass ctClass = cp.getCtClass(className.replace("/", "."));
            final CtMethod method = ctClass.getDeclaredMethod("main");
            method.addLocalVariable("beginTime",CtClass.longType);
            method.insertBefore("long begiinTime = System.currentTimeMillis();");
            method.insertAfter("System.out.print(System.currentTimeMillis() - beginTime);");
            return ctClass.toBytecode();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
