package helloServlet.servlet.web.frontcontroller.v4;

import helloServlet.servlet.web.frontcontroller.ModelView;
import helloServlet.servlet.web.frontcontroller.MyView;
import helloServlet.servlet.web.frontcontroller.v3.ControllerV3;
import helloServlet.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import helloServlet.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import helloServlet.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import helloServlet.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import helloServlet.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import helloServlet.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerServletV4", urlPatterns = "/front-controller/v4/*") //*: 하위 모든 경로가 매핑될때 이 컨트롤러를 거침
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String , ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4()); // 매평경로를 맵에 담아두기
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); // 요청 URI를 string으로 만들고
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI); // 위의 맵으로 get해서 필요한 컨트롤러를 가져올 수 있다.
        //다형성으로 인해서 인터페이스로 받을 수 있다.

        if (controllerV4 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(paramMap, model);
        MyView view = viewResolver(viewName);

        view.render(model,request, response);
    }

    @NotNull
    private static MyView viewResolver(String viewName) { // 논리적 주소와 물리적 주소와 합침
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String > paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)) );
        return paramMap;
    }
}
