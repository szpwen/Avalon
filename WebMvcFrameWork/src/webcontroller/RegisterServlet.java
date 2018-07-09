package webcontroller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import domain.User;
import exception.UserExistException;
import service.IUserService;
import serviceimpl.UserServiceImpl;
import util.WebUtils;
import webformbean.RegisterFromBean;
/*
  处理用户注册的Servlet
  @author szp
 */

public class RegisterServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        //将客户端提交的表单数据封装到RegisterFormBean对象中
        RegisterFromBean fromBean = WebUtils.request2Bean(request,RegisterFromBean.class);
        //校验用户注册填写的表单数据
        if (fromBean.validate() == false) {//若校验失败
            //将封装了用户填写的表单数据的formBean对象发送回register.jsp页面的form表单中进行显示
            request.setAttribute("formBean",fromBean);
            //校验失败就说明是用户填写的表单数据有问题，那么就跳转回register.jsp
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
            return;
        }

        User user = new User();
        try {
            // 注册字符串到日期的转换器
            ConvertUtils.register(new DateLocaleConverter(),Date.class);
            BeanUtils.copyProperties(user,fromBean);//把表单数据填充到javaBean中
            user.setId(WebUtils.makeId());//设置用户id属性
            IUserService service = new UserServiceImpl();
            //调用service层提供的注册用户服务实现用户注册
            service.registerUser(user);
            String message = String.format(
                    "注册成功！！3秒后为您自动调到登录页面！！<meta http-equiv='refresh' content='3;url=%s'/>",
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request,response);

        }catch (UserExistException e) {
            fromBean.getErrors().put("userName","注册用户已存在");
            request.setAttribute("fromBean",fromBean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
        }catch (Exception e) {
            e.printStackTrace();//在后台记录异常
            request.setAttribute("message","对不起，注册失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request,response);
    }
}
