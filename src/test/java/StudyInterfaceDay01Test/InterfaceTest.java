package StudyInterfaceDay01Test;

import Utils.ExcelUtils;
import Utils.HttpUtils;
import Utils.CaseUtils;
import Utils.FaceInfoUtils;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

public class InterfaceTest {
    @Test(dataProvider = "parameterData2")
    public void interfaceTest(String parament,String ApiId) {
        String url = FaceInfoUtils.getUrl(ApiId);
        Map<String, String> para = (Map<String, String>) JSONObject.parse(parament);
        Map<String, String> map = HttpUtils.postRequst(url, para);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " ：" + map.get(key));
        }
    }

    @DataProvider(name = "parameterData1")
    public Object[][] parameterData1() {
        int[] Rows={1};
        int[] Cells={1};
        Object[][] a= ExcelUtils.parameterData("D:\\ParameterData\\Login1.xlsx",
                "Json数据",Rows,Cells);
        return a;
    }
    @DataProvider
    public Object[][] parameterData2(){
        String[] b={"Params","ApiId"};
       Object[][] a= CaseUtils.data("1",b);
        return a;
    }
}