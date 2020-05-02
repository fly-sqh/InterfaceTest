package StudyJavaMavenDay03;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Tester {
    @Test
    @Parameters(value = {"browerType","driverPath"})
    public void test1(String browerType,String driverPath){
        System.out.println("浏览器类型"+browerType+"驱动地址"+driverPath);
    }
    public int test(int a,int b){
        return a+b;
    }
}
