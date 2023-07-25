package helloServlet.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //name: 서블릿 이름 urlPattern 매핑 주소 (서로겹치면 안됨.)
public class HelloServlet extends HttpServlet {

    @Override //서블릿이 호출되면 서비스 메서드가 호출된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String userName = request.getParameter("userName");
        System.out.println(userName);

        //Http 헤더에 들어가는 정보 설정하기
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello~ "+userName);

    }
}
