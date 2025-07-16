package com.example.dinewise.controller;


import com.example.dinewise.model.Feedback;
import com.example.dinewise.repo.FeedbackRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @PostMapping("/postfeedback")
    public ResponseEntity<?> submitFeedback(@Valid  @RequestBody Map<String, Object> request) {
        try {
            String stdId = (String) request.get("stdId");
            LocalDate mealDate = LocalDate.parse((String) request.get("mealDate"));
            boolean isLunch = Boolean.TRUE.equals(request.get("isLunch"));
            boolean isDinner = Boolean.TRUE.equals(request.get("isDinner"));
            String comment = (String) request.get("comment");
            boolean anonymous = Boolean.TRUE.equals(request.get("anonymous"));
            Integer rating = (Integer) request.get("rating");

            Optional<Feedback> existing = feedbackRepo.findByStdIdAndMealDate(stdId, mealDate);
            Feedback feedback = existing.orElse(new Feedback());
            feedback.setStdId(stdId);
            feedback.setMealDate(mealDate);
            feedback.setLunch(isLunch);
            feedback.setDinner(isDinner);
            feedback.setComment(comment);
            feedback.setAnonymous(anonymous);
            feedback.setRating(rating != null ? rating.shortValue() : null);

            feedbackRepo.save(feedback);
            return ResponseEntity.ok(Map.of("message", "Feedback submitted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/getfeedback")
    public ResponseEntity<?> getFeedback(
        @RequestParam String stdId,
        @RequestParam String mealDate
    ) {
        try {
            LocalDate date = LocalDate.parse(mealDate);
            return feedbackRepo.findByStdIdAndMealDate(stdId, date)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid parameters"));
        }
    }
}

