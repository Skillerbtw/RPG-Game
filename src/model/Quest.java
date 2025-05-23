package model;

public class Quest {

    private String title;
    private String description;
    private boolean completed = false;

    public Quest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void complete() {
        this.completed = true;
        System.out.println("✅ Quest abgeschlossen: " + title);
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (completed ? "[✔]" : "[ ]") + " " + title + ": " + description;
    }
}
