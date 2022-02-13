public class Article {
    private String title;
    private String author;
    private String description;

    Article(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    @Override
    public String toString() {
        return title + ':' + description + ':' + author;
    }
}
