package com.example.AnalyticsService.Repository;

import com.example.AnalyticsService.Model.Analytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends MongoRepository<Analytics, Double> {
}
