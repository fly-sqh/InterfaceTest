package StudyJavaMavenDay03;

import com.beust.jcommander.Parameter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviderDemo {
    @Test(dataProvider = "identity")
    public void provider(String name, String gender, String age) {
        System.out.println("姓名:" + name + " 性别:" + gender + " 年龄:" + age );
    }

    @DataProvider(name = "identity")
    //定义数组，向引用数据驱动的方法提供数据
    public Object[][] datapro() {
        Object[][] a = {{"张安", "男", "17"}, {"刘希", "女", "16"}, {"罗斌", "男", "21"}};
        return a;
    }
}

















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































