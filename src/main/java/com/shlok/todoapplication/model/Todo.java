package com.shlok.todoapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Todo extends Basemodel{
    private String description;
    private LocalDate targetDate;
    private Boolean isDone;
}
