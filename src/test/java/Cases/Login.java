package Cases;

import Utils.CaseUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login extends BaseFluga{
    @Test(dataProvider = "parameterData2")
    public void Login(String parameter,String ApiId,String CaseId,String PreValidateSql,String AfterValidateSql){
        interfaceTest(parameter, ApiId, CaseId,PreValidateSql,AfterValidateSql);
    }
    @DataProvider
    public Object[][] parameterData2(){
        Object[][] paraData= CaseUtils.data("1",datas);
        return paraData;
    }
}
