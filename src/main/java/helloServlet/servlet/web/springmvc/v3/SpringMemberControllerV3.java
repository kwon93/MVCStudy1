package helloServlet.servlet.web.springmvc.v3;

import helloServlet.servlet.domain.member.Member;
import helloServlet.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String  newForm(){
        return "new-form";
    }



    @GetMapping
    public String list(Model model){

        List<Member> members = memberRepository.findAll();
        ModelAndView modelView = new ModelAndView("members");

        model.addAttribute("members", members);
        return "members";

    }


    @PostMapping("/save")
    public String  save(@RequestParam("username") String username, int age, Model model){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }


}
