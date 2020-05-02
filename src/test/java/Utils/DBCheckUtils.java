package Utils;

import Pojc.DBChecker;
import Pojc.DBQueryResult;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据库查询工具类
 */
public class DBCheckUtils {
    /**
     *
     * @param preValidateSql excel里读取出来的sql（json格式）
     * @return 执行sql结束后的结果集转换成json格式返回   
     */
    public static String doQuery(String preValidateSql) {
        List<DBQueryResult> dbQueryResults=new ArrayList<DBQueryResult>();//定义DBQueryResult对象的集合来接收sql执行后的结果集对象
        //调用JSONObject.parseArray方法把excel里json格式的sql放到DBChecker对象中，返回一个对象的集合
        List<DBChecker> dbCheckers= JSONObject.parseArray(preValidateSql,DBChecker.class);
        //dbCheckers遍历对象集合数组
        for (DBChecker dbChecker:dbCheckers){
            String sql= dbChecker.getSql();//拿出sql
            String no=dbChecker.getNo();//拿出sql对应的编号
            Map<String,Object> columenLabelAndValues=JDBCQuery.Query(sql);//执行sql,结果返回一个map，定义一个map接收
            //调用DBQueryResult对象全参构造函数实例化
            DBQueryResult dbQueryResult=new DBQueryResult(no,columenLabelAndValues);
            dbQueryResults.add(dbQueryResult);//将结果集对象保存到数组中
        }return JSONObject.toJSONString(dbQueryResults);//将结果集的map数组转换成json格式

    }
}
