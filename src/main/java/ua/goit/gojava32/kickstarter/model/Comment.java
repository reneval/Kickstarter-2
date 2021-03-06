package ua.goit.gojava32.kickstarter.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Comparable {

  private Integer id;
  private String text;
  private  Date created;
  private  Project project;

    public Comment() {

    }

    public Comment(Integer id, String text, Date dateOfCreation, Project project) {
        this.id = id;
        this.text = text;
        this.created = dateOfCreation;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (getText() != null ? !getText().equals(comment.getText()) : comment.getText() != null)
            return false;
        return !(created != null ? !created.equals(comment.created) : comment.created != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return "("+format.format(getCreated())+") "+getText();
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        Comment entry = (Comment) o;
        return -getCreated().compareTo(entry.getCreated());
    }
}
