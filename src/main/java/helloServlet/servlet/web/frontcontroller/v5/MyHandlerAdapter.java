package helloServlet.servlet.web.frontcontroller.v5;

import helloServlet.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    boolean support(Object handler);

    ModelView handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)throws ServletException, IOException;
}
