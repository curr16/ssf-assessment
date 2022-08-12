package vttp2022.ssf.ssf_assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.ssf.ssf_assessment.models.News;
import vttp2022.ssf.ssf_assessment.services.NewsService;


@Controller
@RequestMapping (path = "/crypto")

public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping
    public String getArticles(Model model) {
    List<News> articles = newsSvc.getArticles();
    model.addAttribute("list", articles);
    return "index";
    }

    @PostMapping
    public String getsaveArticles(Model model, @RequestBody MultiValueMap<String, String> form) {
        return null;
    }
    
}