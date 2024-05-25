package elements;

import org.openqa.selenium.By;

public class Row {

    String name;
    String author;
    String id;
    boolean isDraft;

    public Row(String name, String author, String id){

        this.name = name;
        this.author = author;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public String getName() {
        return name;
    }
}
