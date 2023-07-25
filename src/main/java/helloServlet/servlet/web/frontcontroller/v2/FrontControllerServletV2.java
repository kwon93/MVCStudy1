package helloServlet.servlet.web.frontcontroller.v2;

import helloServlet.servlet.web.frontcontroller.MyView;
import helloServlet.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import helloServlet.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import helloServlet.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerServletV2", urlPatterns = "/front-controller/v2/*") //*: 하위 모든 경로가 매핑될때 이 컨트롤러를 거침
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String , ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2()); // 매평경로를 맵에 담아두기
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2()  );
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); // 요청 URI를 string으로 만들고
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI); // 위의 맵으로 get해서 필요한 컨트롤러를 가져올 수 있다.
        //다형성으로 인해서 인터페이스로 받을 수 있다.

        if (controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
