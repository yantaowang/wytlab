package com.wyt.wytlab.mianshi;

import com.wyt.wytlab.leetcode.list.Person;
import ognl.Ognl;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

public class ONGLTest {
    public static void main(String[] args) throws OgnlException {
        Person person = new Person();
        person.setAge(11);
        person.setName("ddd");
        System.out.println((Integer) Ognl.getValue("age=19",person));
        System.out.println(person.hashCode());
        System.out.println((Integer) Ognl.getValue("hashCode()",person));

        Map<String, Person> map = new HashMap<>();
        map.put("user1", person);
        System.out.println((boolean) Ognl.getValue("#user1.age != 19",map, new Object()));
    }
}
