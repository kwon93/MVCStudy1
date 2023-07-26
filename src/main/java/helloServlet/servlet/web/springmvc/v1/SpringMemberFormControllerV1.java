package helloServlet.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 스프링이 자동으로 스프링 빈으로 등록함. ,스프링 MVC에서 애노태이션 기반 컨트롤러로 인식함.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView precess(){
        return new ModelAndView("new-form");
    }
}
