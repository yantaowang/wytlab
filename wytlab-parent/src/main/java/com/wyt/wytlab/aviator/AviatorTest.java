package com.wyt.wytlab.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AviatorTest {
    public static void main(final String[] args) throws Exception {
        // Compile the script into a Expression instance.
        AviatorEvaluator.addFunction(new AddFunction());
        AviatorEvaluator.addInstanceFunctions("s", String.class);
        AviatorEvaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
        Expression exp = AviatorEvaluator.getInstance().compileScript("examples/hello.av");
        // Run the exprssion.
        Object result = exp.execute();
        System.out.println("result: " + result);


//        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
//        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)"));
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("1", "a");
//        map.put("2", "b");
//        System.out.println(map.values().iterator().next());
//
//        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
//        blockingQueue.add("aaa");
//        blockingQueue.add("bbb");
//
//        String val = blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();
    }

    static class AddFunction extends AbstractFunction{

        @Override
        public String getName() {
            return "add";
        }

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            Object o = AviatorEvaluator.getInstance().compile("c").execute(env);
            System.out.println("************************");
            System.out.println(o);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }

    }

}
