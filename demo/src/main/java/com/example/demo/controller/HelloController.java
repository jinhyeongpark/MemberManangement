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
        model.addAttribute("data", "������");
        return "hello";
    }
    //MVC
    @GetMapping("hello-mvc")
    //�ܺο��� �Ű������� �ޱ� ���� @RequestParam ���
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        //resources: templates�� hello-template�� ��ȯ
        return "hello-template";
    }
    @GetMapping("hello-myself")
    public String helloMyself(Model model) {
        model.addAttribute("name", "������");
        model.addAttribute("height", "180");
        model.addAttribute("weight", "81");
        return "hello-myself";
    }
    //API - ���� ��ȯ
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    //API - ��ü ��ȯ
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
        private  String name; //���� ����
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
