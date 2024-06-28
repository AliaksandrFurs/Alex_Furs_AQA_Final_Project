package elements;

public class Row {

    String title;
    String author;
    String id;

    public Row(String id){
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
