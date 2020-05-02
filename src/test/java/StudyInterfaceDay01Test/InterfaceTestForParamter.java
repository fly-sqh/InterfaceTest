package StudyInterfaceDay01Test;

import Utils.ExcelUtils;
import Utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class InterfaceTestForParamter {
    @Test(dataProvider = "parameterData2")
    public void HttpTest(String username,String password) throws IOException {
        String url="https://www.fluga.com/secure/authenticate";
        Map<String,String> para=new HashMap<String, String>();
        para.put("username",username);
        para.put("password",password);
        Map<String,String> map= HttpUtils.postRequst(url,para);
        Set<String> keys= map.keySet();
        for (String key:keys){
            System.out.println(key+" ："+map.get(key));
        }
    }
    @DataProvider(name="parameterData1")
        public Object[][] parameterData1(){
            Object[][] a= ExcelUtils.parameterData("D:\\ParameterData\\Login1.xlsx",
                    "登录数据",1,1,1,1);
            return a;
    }
    @DataProvider(name="parameterData2")
    public Object[][] parameterData2(){
        int[] Rows={2,4,5};
        int[] Cells={1,2};
        Object[][] a=ExcelUtils.parameterData("D:\\ParameterData\\Login.xlsx",
                "登录数据",Rows,Cells);
        return a;
    }
}
