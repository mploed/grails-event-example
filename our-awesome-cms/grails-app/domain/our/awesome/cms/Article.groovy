package our.awesome.cms

class Article {

    String headline
    String fullText
    String articleUrlKey

    static constraints = {
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", version=" + version +
                ", headline='" + headline + '\'' +
                ", fullText='" + fullText + '\'' +
                ", articleUrlKey='" + articleUrlKey + '\'' +
                '}';
    }
}
