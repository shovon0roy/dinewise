package com.example.dinewise.repo;

import com.example.dinewise.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByStdIdAndMealDate(String stdId, LocalDate mealDate);
}
