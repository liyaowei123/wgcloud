package edu.jit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//登录界面
@WebFilter(filterName = "jkyFilter",urlPatterns = "/*")
public class JkyFilter implements Filter {
    private static final String[] WHITE_LITE={"/login/toLogin","/login/login","/static/","/login/"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();//登录后要往session中增加内容
        //如果登录了，同时要在LOGIN_KEY中增加用户对象
        Object accountInfo = session.getAttribute("LOGIN_KEY");
        String path = req.getServletPath();//请求的路径
        String uri = req.getRequestURI();//请求的地址
        initMenuActive(session,uri);
        for (String url : WHITE_LITE) {
            if (path.startsWith(url)) {//以白名单中的地址开头，可以直接放过，让其继续访问
                chain.doFilter(req, resp);
                return;
            }
        }
        if (null == accountInfo) {//没有登录信息
            resp.sendRedirect("/wgcloud/login/toLogin");
            return;
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void initMenuActive(HttpSession session, String uri) {
        if(uri.contains("/static/")){
            return;
        }
        if(uri.contains("/dash/main")){
            session.setAttribute("menuActive","11");
            return;
        }else if(uri.contains("/dash/systemInfoList")){
            session.setAttribute("menuActive","12");
            return;
        }else if(uri.contains("/appInfo/list")){
            session.setAttribute("menuActive","13");
            return;
        }else if(uri.contains("/dbInfo/list")){
            session.setAttribute("menuActive","41");
            return;
        }else if(uri.contains("/dbTable/list")){
            session.setAttribute("menuActive","42");
            return;
        }else if(uri.contains("/heathMonitor/list")){
            session.setAttribute("menuActive","51");
            return;
        }else if(uri.contains("/log/list")){
            session.setAttribute("menuActive","21");
            return;
        }else if(uri.contains("/mailset/list")){
            session.setAttribute("menuActive","31");
            return;
        }
        session.setAttribute("menuActive","11");
    }
}
