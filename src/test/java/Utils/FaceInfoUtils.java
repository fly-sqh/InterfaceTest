package Utils;

import Cases.BaseFluga;
import Pojc.InterfaceInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * 接口信息对象工具类
 */
public class FaceInfoUtils {
    public static List<InterfaceInfo> infos=new ArrayList<InterfaceInfo>();
    static {
        infos= ExcelUtils.load(BaseFluga.filePath,"接口信息", InterfaceInfo.class);
    }
    /**
     * 获取URL
     */
    public static String getUrl(String ApiId) {

        for (InterfaceInfo info : infos) {//循环判断，如果对象里的ApiId和传入的ApiId一致，则返回URL列的信息，不一致则返回空
            if (ApiId.equals(info.getApiId())) {
                return info.getUrl();
            }
        }return "";
    }
    /**
     * 获取接口请求类型
     */

    public static String getType(String ApiId){

        for (InterfaceInfo info:infos){//循环判断，如果对象里的ApiId和传入的ApiId一致，则返回Type列的信息，不一致则返回空
            if (ApiId.equals(info.getApiId())){
                return info.getType();
            }
        }return null;
    }

}
