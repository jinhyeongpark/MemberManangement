package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "여러분");
        return "hello";
    }
    //MVC
    @GetMapping("hello-mvc")
    //외부에서 매개변수를 받기 위해 @RequestParam 사용
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        //resources: templates의 hello-template로 반환
        return "hello-template";
    }
    @GetMapping("hello-myself")
    public String helloMyself(Model model) {
        model.addAttribute("name", "박진형");
        model.addAttribute("height", "180");
        model.addAttribute("weight", "81");
        return "hello-myself";
    }
    //API - 문자 반환
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    //API - 객체 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(
        @RequestParam(name="name", required = false) String name,
        @RequestParam(name = "height", required = false) String height) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setHeight(height);
        return hello;
    }
    static class Hello {
        private  String name; //접근 제어
        private String height;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
    @GetMapping("/calculator")
    public String showCalculatorForm() {
        return "calculator-form";
    }

    @PostMapping("/calculate")
    public String calculateResult(
        @RequestParam(name = "num1", required = false, defaultValue = "0") int num1,
        @RequestParam(name = "num2", required = false, defaultValue = "0") int num2,
        Model model) {
        int result = num1 + num2;
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "result";
    }

}
