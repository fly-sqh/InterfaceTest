package Pojc;
//接口信息对象
public class InterfaceInfo {
    private String ApiId;
    private String ApiName;
    private String Type;
    private String Url;

    public InterfaceInfo() {
    }

    public InterfaceInfo(String apiId, String apiName, String type, String url) {
        ApiId = apiId;
        ApiName = apiName;
        Type = type;
        Url = url;
    }

    public String getApiId() {
        return ApiId;
    }

    public void setApiId(String apiId) {
        ApiId = apiId;
    }

    public String getApiName() {
        return ApiName;
    }

    public void setApiName(String apiName) {
        ApiName = apiName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return  "ApiId='" + ApiId + "ApiName='" + ApiName + "Type=" + Type + "Url=" + Url;
    }
}
