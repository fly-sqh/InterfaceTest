package Pojc;
//用例对象
public class Case {
    private String CaseId;
    private String Desc;
    private String ApiId;
    private String Params;
    private String ActualResult;
    private String PreValidateSql;
    private String PreValidateResult;
    private String AfterValidateSql;
    private String AfterValidateResult;

    public String getPreValidateSql() {
        return PreValidateSql;
    }

    public void setPreValidateSql(String preValidateSql) {
        PreValidateSql = preValidateSql;
    }

    public String getPreValidateResult() {
        return PreValidateResult;
    }

    public void setPreValidateResult(String preValidateResult) {
        PreValidateResult = preValidateResult;
    }

    public String getAfterValidateSql() {
        return AfterValidateSql;
    }

    public void setAfterValidateSql(String afterValidateSql) {
        AfterValidateSql = afterValidateSql;
    }

    public String getAfterValidateResult() {
        return AfterValidateResult;
    }

    public void setAfterValidateResult(String afterValidateResult) {
        AfterValidateResult = afterValidateResult;
    }

    public Case(String caseId, String desc, String apiId, String params, String ActualResult) {
        CaseId = caseId;
        Desc = desc;
        ApiId = apiId;
        Params = params;
    }

    public Case() {
    }

    public String getCaseId() {
        return CaseId;
    }

    public void setCaseId(String caseId) {
        CaseId = caseId;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getApiId() {
        return ApiId;
    }

    public void setApiId(String apiId) {
        ApiId = apiId;
    }

    public String getActualResult() {
        return ActualResult;
    }

    public void setActualResult(String actualResult) {
        ActualResult = actualResult;
    }

    public String getParams() {
        return Params;
    }

    public void setParams(String params) {
        Params = params;
    }
    public String toString(){
        return "caseId="+getCaseId()+" apiId="+getApiId()+" desc="+getDesc()+" params="+getParams();
    }
}
