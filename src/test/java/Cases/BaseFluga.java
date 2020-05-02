package Cases;

import Pojc.BackWriteData;
import Pojc.Variable;
import Utils.*;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.AfterSuite;

import java.util.Map;
import java.util.Set;

public class BaseFluga {
    public static String filePath="Config/case.xlsx";
    public String[] datas={"Params","ApiId","CaseId","PreValidateSql","AfterValidateSql"};
    public void interfaceTest(String parameter,String ApiId,String CaseId,
                              String PreValidateSql,String AfterValidateSql){
        if (PreValidateSql!=null&&PreValidateSql.trim().length()>0) {//接口调用前执行sql语句
             String preValidateResult= DBCheckUtils.doQuery(PreValidateSql);
            ExcelUtils.backWriteDatas.add(new BackWriteData
                    (CaseId,"用例",preValidateResult,"PreValidateResult"));
        }
        String type= FaceInfoUtils.getType(ApiId);//获取excel里的请求类型
        String url= FaceInfoUtils.getUrl(ApiId);//获取excel里的url
        //将excel里请求参数的json报文保存到map数组
        parameter=VariableUtils.replaceVarible(parameter);
        Map<String,String> para=(Map<String, String>) JSONObject.parse(parameter);
        Map<String,String> map=null;
        if (type.equals("post")){//判断请求类型，如果是post,则调用postRequst函数
                map = HttpUtils.postRequst(url,para);
        }else if (type.equals("get")){//判断请求类型，如果是get,则调用getRequst函数
            map = HttpUtils.getRequst(url,para);
        }
        String result= map.get("response");//将响应报文赋值给result
        System.out.println(result);//控制台输出响应报文
        BackWriteData backWriteData=new BackWriteData(CaseId,"用例",result,"ActualResult");
        ExcelUtils.backWriteDatas.add(backWriteData);//将对象保存到backWriteDatas的对象数组中
        if (AfterValidateSql!=null&&AfterValidateSql.trim().length()>0) {//接口调用前执行sql语句
            String AfterValidateResult= DBCheckUtils.doQuery(AfterValidateSql);
            ExcelUtils.backWriteDatas.add(new BackWriteData
                    (CaseId,"用例",AfterValidateResult,"AfterValidateResult"));
        }
    }
    @AfterSuite
    public void batchWriteBackDatas(){
        CaseUtils.BatchWriteData(filePath); //将结果回写到excel中
    }

}
