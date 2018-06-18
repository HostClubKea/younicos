package com.younicos.frequencygenerator.controller;

import com.younicos.frequencygenerator.generator.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneratorController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }

    @RequestMapping("/normal")
    public String normal(Model model){
        CurrentGenerator.set(new NormalGenerator());
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }

    @RequestMapping("/high")
    public String high(Model model){
        CurrentGenerator.set(new HighGenerator());
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }

    @RequestMapping("/low")
    public String low(Model model){
        CurrentGenerator.set(new LowGenerator());
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }

    @RequestMapping("/invalid")
    public String invalid(Model model){
        CurrentGenerator.set(new InvalidGenerator());
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }

    @RequestMapping("/none")
    public String none(Model model){
        CurrentGenerator.set(new EmptyGenerator());
        model.addAttribute("generator", CurrentGenerator.get().getName());
        return "index";
    }
}
