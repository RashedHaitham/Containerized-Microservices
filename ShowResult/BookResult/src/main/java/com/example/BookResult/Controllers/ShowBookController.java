package com.example.BookResult.Controllers;

import com.example.BookResult.Model.Analytics;
import com.example.BookResult.Service.ShowBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/showBook")
public class ShowBookController {

    @Autowired
    private ShowBookService showBookService;

    @GetMapping("/BookAnalysis")
    public String showBookAnalysis(Model model) {
        List<Analytics> analyticsList = showBookService.getBookAnalyticResults();
        model.addAttribute("analyticsList", analyticsList);
        return "results";
    }
}