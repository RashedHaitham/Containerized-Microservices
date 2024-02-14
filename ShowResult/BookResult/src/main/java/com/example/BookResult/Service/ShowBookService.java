package com.example.BookResult.Service;

import com.example.BookResult.Model.Analytics;
import com.example.BookResult.Repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowBookService {
    @Autowired
    AnalyticsRepository analyticsRepository;

    public List<Analytics> getBookAnalyticResults(){
        return analyticsRepository.findAll();
    }
}
