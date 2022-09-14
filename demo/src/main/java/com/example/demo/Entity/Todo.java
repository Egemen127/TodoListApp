package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //ensures that todo label has less than 30 characters and doesn't consist of only whitespace
    @NotBlank
    @Size(max=30)
    private String label;
    // ensures that todo text has less than 150 characters and doesn't consist of
    // only whitespace
    @NotBlank
    @Size(max=150)
    private String text;
    private boolean isDone=false;
    private String day;
    private String userName;
    
    public String getLabel() {
        return this.label;
    }
    public void setLabel(String str) {
        this.label = str;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String str) {
        this.text = str;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int i) {
        this.id = i;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public void setIsDone(boolean b) {
        this.isDone = b;
    }
    public void setDay(String d) {
        this.day = d;
    }
    public String getDay() {
        return this.day;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
