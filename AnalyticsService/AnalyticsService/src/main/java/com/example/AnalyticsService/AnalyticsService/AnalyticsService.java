package com.example.AnalyticsService.AnalyticsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnalyticsService {
    @PersistenceContext
    private EntityManager entityManager;

    public Double maxPrice() {
        Query query = entityManager.createNativeQuery("SELECT MAX(price) FROM books");
        Number result = (Number) query.getSingleResult();
        return result.doubleValue();
    }

    public Double minPrice() {
        Query query = entityManager.createNativeQuery("SELECT MIN(price) FROM books");
        Number result = (Number) query.getSingleResult();
        return result.doubleValue();
    }

    public Double avgPrice() {
        Query query = entityManager.createNativeQuery("SELECT AVG(price) FROM books");
        Number result = (Number) query.getSingleResult();
        return result.doubleValue();
    }

    public Integer numOfBooks() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM books");
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    public Date lastUpdated() {
        Query query = entityManager.createNativeQuery("SELECT MAX(last_updated) FROM books");
        return (Date) query.getSingleResult();
    }
}
