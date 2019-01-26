package com.ai.test.utils;
import com.ai.test.vo.User;
import com.ai.utils.GenSetInvoker;

public class GenSetInvokerTest {

    public static void main(String[] args) throws ClassNotFoundException {
        GenSetInvoker.gen(User.class);
    }

}
