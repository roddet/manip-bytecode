package com.roddet.agent.simple;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.List;

public class MySimpleAgent {

    public static void premain(String agentArgs, Instrumentation instr) {
        System.out.println("I'm your secret agent");
        instr.addTransformer(new MySimpleTransformer());
    }

    public static void agentmain(String agentArgs, Instrumentation instr) throws UnmodifiableClassException {
        System.out.println("I'm your dynamic secret agent");
        instr.addTransformer(new MySimpleTransformer(), true);
        instr.retransformClasses(getModifiedClasses(instr));
    }

    private static Class<?>[] getModifiedClasses(Instrumentation instr) {
        List<Class<?>> modifiableClasses = new ArrayList<Class<?>>();
        for(Class<?> aClass : instr.getAllLoadedClasses()) {
            if(instr.isModifiableClass(aClass)){
                modifiableClasses.add(aClass);
            }
        }
        Class<?>[] classesTab = new Class<?>[modifiableClasses.size()];
        int i = 0;
        for (Class<?> aClass : modifiableClasses){
            classesTab[i] = aClass;
            i++;
        }
        return classesTab;
    }
}
