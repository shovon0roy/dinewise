// Feedback.java (Entity)
package com.example.dinewise.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_id", nullable = false)
    private String stdId;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "is_lunch", nullable = false)
    private boolean isLunch;

    @Column(name = "is_dinner", nullable = false)
    private boolean isDinner;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private boolean anonymous = false;

    @Column
    private Short rating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
    
