package com.example.AnalyticsService.Controllers;


import com.example.AnalyticsService.AnalyticsService.AnalyticsService;
import com.example.AnalyticsService.Model.Analytics;
import com.example.AnalyticsService.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsRepository analyticsRepository;
    @Autowired
    private AnalyticsService analyticsService;

    @PostMapping("/updateAnalytics")
    public void updateAnalytics(){
        analyticsRepository.deleteAll(); // delete existing analysis to keep the logic concise and clear
        Double maxPrice = analyticsService.maxPrice();
        Double minPrice = analyticsService.minPrice();
        Double avgPrice = analyticsService.avgPrice();
        Integer numOfBooks = analyticsService.numOfBooks();
        Date lastUpdated = analyticsService.lastUpdated();
        Analytics mongoDocument = new Analytics(maxPrice, minPrice, avgPrice, numOfBooks, lastUpdated);
        analyticsRepository.save(mongoDocument);
    }
}
