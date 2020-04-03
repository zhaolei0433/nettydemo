package test1;

import java.io.Serializable;

/**
 * author:zhaolei
 * Date:2018/7/5
 */

public class Cat {
    private int num;
    private String name;
    private int age;

    public Cat() {
    }

    public Cat(int num, int age) {
        this.num = num;
        this.age = age;
    }

    public Cat(int num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
