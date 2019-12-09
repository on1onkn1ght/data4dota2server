package com.onionknight.data4dota2;

/**
 * @Author :fdy
 * @Date :Created in 18:48 2019/9/10
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class Student {
    public String name;
    public Student(String name){
        this.name = name;
    }
}

class Main{
    public static void changeName(Student student){
        student.name = "b";
    }
    public static void changeName(String name){
        name = "b";
    }

    public static void main(String[] args) {
        int i = 0;
        String a = "";
        while (i < 10000){
            a += i;
            i++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("b");
        System.out.println(a);
    }
}
