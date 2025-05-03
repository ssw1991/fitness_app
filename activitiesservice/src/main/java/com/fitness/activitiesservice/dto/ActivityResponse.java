package com.fitness.activitiesservice.dto;

import com.fitness.activitiesservice.model.Activity;
import com.fitness.activitiesservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponse {

    public ActivityResponse(Activity activity){
        userId = activity.getUserId();
        type = activity.getType();
        duration = activity.getDuration();
        caloriesBurned = activity.getCaloriesBurned();
        startTime = activity.getStartTime();
        endTime = activity.getEndTime();
        additionalMetrics = activity.getAdditionalMetrics();
    }
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Object> additionalMetrics;
}
