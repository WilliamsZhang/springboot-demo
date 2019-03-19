package com.example.demo2.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther willi
 * @create-time 2019-03-19-21:56
 */

//进行 session 验证的过滤器
public class SessionFilter implements Filter {

    protected Logger log = LoggerFactory.getLogger(SessionFilter.class);

    //将 set 设置为全局变量，在启动时添加 URL 白名单
    private static Set<String> set = new HashSet<>();

    //不需要进行 session 验证的 URL
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        set.add("/loginPage");
        set.add("/toAdd");
        set.add("/toLogin");
        set.add("/toRegister");
        set.add("/login");
        set.add("/register");
        set.add("/verified");
    }

    //设置过滤规则
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        //filter 过滤静态资源
        if (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".gif") || uri.endsWith(".png")){
            log.warn("静态资源过滤器，pass" + request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //判断所执行的 URL 是否在白名单内，在的话直接通过
        if(set.contains(uri) || uri.contains("/verified/")){
            log.warn("url 在过滤白名单内" + request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //session 验证,判断 session 中是否存在用户 id
        String id = (String)request.getSession().getAttribute(WebConfiguration.LOGIN_KEY).toString();
        if(StringUtils.isEmpty(id)){
            String html = "<script type=\"text/javascript\">window.location.href=\"/toLogin\"</script>";
            servletResponse.getWriter().write(html);
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
