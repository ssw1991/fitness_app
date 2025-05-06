package com.fitness.activitiesservice.service;

import com.fitness.activitiesservice.dto.ActivityRequest;
import com.fitness.activitiesservice.dto.ActivityResponse;
import com.fitness.activitiesservice.model.Activity;
import com.fitness.activitiesservice.repo.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    //private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .additionalMetrics(request.getAdditionalMetrics()).build();
        return new ActivityResponse(activityRepository.save(activity));

    }

    private ActivityResponse createResponse(Activity activity){
        return new ActivityResponse(activity);
    }
    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream().map(this::createResponse).collect(Collectors.toList());
    }

    public ActivityResponse getActivitybyId(String activityId) {
        return activityRepository.findById(activityId).map(this::createResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found!"));
    }
}