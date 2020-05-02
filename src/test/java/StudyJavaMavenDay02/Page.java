package StudyJavaMavenDay02;

public class Page {
    private String keyword;

    public Page() {
    }

    public Page(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Page{" + "keyword='" + keyword + '\'' + '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
