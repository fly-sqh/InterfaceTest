package Pojc;

public class DBChecker {
    private String no;
    private String sql;
//用来保存sql的对象
    public DBChecker(String no, String sql) {
        this.no = no;
        this.sql = sql;
    }

    public DBChecker() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
