package helloServlet.servlet.web.frontcontroller.v4.controller;

import helloServlet.servlet.domain.member.Member;
import helloServlet.servlet.domain.member.MemberRepository;
import helloServlet.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members",members);
//        ModelView modelView = new ModelView("members");
//        modelView.getModel().put("members",members);
        return "members";

    }
}
