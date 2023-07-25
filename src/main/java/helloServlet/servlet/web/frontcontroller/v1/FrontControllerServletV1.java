package helloServlet.servlet.web.frontcontroller.v1;

import helloServlet.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import helloServlet.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import helloServlet.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerServletV1", urlPatterns = "/front-controller/v1/*") //*: 하위 모든 경로가 매핑될때 이 컨트롤러를 거침
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String , ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1()); // 매평경로를 맵에 담아두기
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // 요청 URI를 string으로 만들고
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI); // 위의 맵으로 get해서 필요한 컨트롤러를 가져올 수 있다.
        //다형성으로 인해서 인터페이스로 받을 수 있다.

        if (controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controllerV1.process(request,response);
    }
}
