package span.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Создано Span 20.04.2017.
 */
@Controller
public class PageController {



    @RequestMapping(value = "/login-page", method = RequestMethod.GET)
    public String loginPage() {
        System.out.println("loginPage");
        return "login.html";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage404() {
        System.out.println("404");
        return "404.html";
    }


    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String getLogs() {
        System.out.println("getLogs");
        return "homepage.html";
    }

}
