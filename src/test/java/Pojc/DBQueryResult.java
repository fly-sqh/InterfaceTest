package Pojc;

import java.util.Map;

/**
 * 数据库查询结果实体类
 */
public class DBQueryResult {
    private String no;
    private Map<String,Object> columenLabelAndValues;

    public DBQueryResult(String no, Map<String, Object> columenLabelAndValues) {
        /**
         * 脚本编号
         */
        this.no = no;
        /**
         * 脚本执行查到得数据，保存到map中（key保存的是字段名，value保存的字段查询出来的值）
         */
        this.columenLabelAndValues = columenLabelAndValues;
    }

    public DBQueryResult() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getColumenLabelAndValues() {
        return columenLabelAndValues;
    }

    public void setColumenLabelAndValues(Map<String, Object> columenLabelAndValues) {
        this.columenLabelAndValues = columenLabelAndValues;
    }
}
