package StudyJavaMavenDay02;

import java.lang.reflect.Method;

public class Reflect {
    public static void main(String[] args) throws Exception {
      reflectTest();

    }
    public static void reflectTest() throws Exception {
//        1.获取类的字节码
        Class<Student> clazz= Student.class;
//        2.通过字节码去创建对象
        Student student= clazz.newInstance();
//        3.反射得到要调用的方法对象method
        Method setAgeMethod= clazz.getMethod("setAge", int.class);
//        4.通过反射调用
        setAgeMethod.invoke(student,18);
        System.out.println(student.getAge());

    }
}
