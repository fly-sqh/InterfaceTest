package StudyJavaMavenDay03;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestPractice {
    @Test(groups = "test1")
    public void a1(){
        System.out.println("a1");
    }
    @Test(dependsOnMethods = "a4",groups = "test1")
    public void a2(){
        System.out.println("a2");
    }
    @Test
    public void a4(){
        System.out.println("a3");
    }
    @Test
    @Parameters(value = {"browerType"})
    public void a3(String browerType){
        System.out.println(browerType);
    }

}
