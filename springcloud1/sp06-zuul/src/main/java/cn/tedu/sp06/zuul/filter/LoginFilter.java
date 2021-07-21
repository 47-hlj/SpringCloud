package cn.tedu.sp06.zuul.filter;


import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 47HLJ
 * @date 2021/7/21 9:40
 * @description
 * 模拟判断是否登录
 * 没有登录，不允许访问
 * http://localhost:3001/item-service/989
 *
 * 已登录可以访问
 * http://localhost:3001/item-service/989?token=423423fds
 *
 * 只检查对商品访问的权限
 * 用户和订单的访问不检查权限
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 指定过滤器类型 pre,routing,post,error
     * @return
     */
    @Override
    public String filterType() {
//        前置过滤器，一般自定义的过滤器都用前置过滤器
//        return "pre";
        return FilterConstants.PRE_TYPE;
    }


    /**
     * 设置过滤器的顺序号
     * @return
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 判断针对当前请求，是否执行过滤代码
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 调用商品检查权限
        // 调用用户和订单不检查权限

        // 判断当前请求调用的是否是 item-service

        // 1. 获得一个请求上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        // 2. 从上下文对象获取调用的服务id
        // "serviceId"
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        // 3. 判断服务id
        return "item-service".equals(serviceId);
    }

    /**
     * 过滤代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 1.获得上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        // 2.获得request对象
        HttpServletRequest request = ctx.getRequest();
        // 3.接收 token 参数
        String token = request.getParameter("token");
        // 4.如果没有token,阻止继续调用,直接返回响应
        if (StringUtils.isBlank(token)) {
            // 阻止继续调用
            ctx.setSendZuulResponse(false);
            // 直接返回响应
            // JsonResult --> {code:400,msg:Not Login,data:null}
            String json = JsonResult
                    .err()
                    .code(JsonResult.NOT_LOGIN)
                    .msg("Not Login! 未登录！")
                    .toString();
            ctx.addZuulResponseHeader(
                    "Content-Type",
                    "application/json;charset=UTF-8");
            ctx.setResponseBody(json);

        }
        // 在当前zuul版本中，这个返回值没有任何作用
        return null;
    }
}
