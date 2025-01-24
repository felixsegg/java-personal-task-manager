package logic.dto;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;

import java.util.Collection;
import java.util.Objects;

public class Task {
    public enum Priority {
        LOW, MEDIUM, HIGH;
        
        public static Priority parse(String s) {
            try {
                return Priority.valueOf(s.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }
    
    private final ObjectProperty<Priority> priority = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final ObjectProperty<Person> owner = new SimpleObjectProperty<>();
    private final ReadOnlySetWrapper<Person> associates = new ReadOnlySetWrapper<>(FXCollections.observableSet());
    
    {
        // Initializer
        initializeAssociatesObservable();
    }
    
    public Task(Priority priority, String name, String comment, Person owner, Collection<Person> associates) {
        if (name == null)
            throw new IllegalArgumentException("Param name must not be null!");
        setPriority(priority);
        setName(name);
        setComment(comment);
        setOwner(owner);
        
        if (associates != null)
            this.associates.addAll(associates);
    }
    
    private void initializeAssociatesObservable() {
        this.associates.addListener((SetChangeListener<Person>) change -> {
            if (change.wasAdded()) {
                Person addition = change.getElementAdded();
                addition.getAssociatedTasks().add(this);
            } else if (change.wasRemoved()) {
                Person removal = change.getElementRemoved();
                removal.getAssociatedTasks().remove(this);
            }
        });
    }
    
    public Priority getPriority() {
        return priority.get();
    }
    
    public ObjectProperty<Priority> priorityProperty() {
        return priority;
    }
    
    public void setPriority(Priority priority) {
        this.priority.set(priority);
    }
    
    public String getName() {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getComment() {
        return comment.get();
    }
    
    public StringProperty commentProperty() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment.set(comment);
    }
    
    public Person getOwner() {
        return owner.get();
    }
    
    public ObjectProperty<Person> ownerProperty() {
        return owner;
    }
    
    public void setOwner(Person owner) {
        if (getOwner() != null)
            getOwner().getOwnedTasks().remove(this);
        
        if (owner != null)
            owner.getOwnedTasks().add(this);
        
        this.owner.set(owner);
    }
    
    protected SetProperty<Person> getAssociates() {
        return associates;
    }
    
    public ReadOnlySetProperty<Person> getAssociatesReadOnly() {
        return associates.getReadOnlyProperty();
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", name=" + name +
                ", comment=" + comment +
                ", owner=" + owner +
                ", associates=" + associates +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getPriority(), task.getPriority())
                && Objects.equals(getName(), task.getName())
                && Objects.equals(getComment(), task.getComment())
                && Objects.equals(getOwner(), task.getOwner())
                && Objects.equals(getAssociates(), task.getAssociates());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getPriority(), getName(), getComment(), getOwner(), getAssociates());
    }
}
