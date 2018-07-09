package webformbean;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/*
 封装的用户注册表单bean，用来接收register.jsp中的表单输入项的值
 RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
 RegisterFormBean的职责除了负责接收register.jsp中的表单输入项的值之外还担任着校验表单输入项的值的合法性
 @author szp
 */
public class RegisterFromBean {
    //RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
    //<input type="text" name="userName"/>
    private String userName;
    //<input type="password" name="userPassword"/>
    private String userPassword;
    //<input type="password" name="confirmPassword"/>
    private String confirmPassword;
    //<input type="text" name="email"/>
    private String email;
    //<input type="text" name="birthday"/>
    private String birthday;

    /*
    存储校验不通过时给用户的错误提示信息
     */
    private Map<String,String > errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors){
        this.errors = errors;
    }

    /*
    validate负责校验表单输入项
    校验规则：
    private String userName; 用户名不能为空，并且要是3-8的字母 abcdABcd
             private String userPwd; 密码不能为空，并且要是3-8的数字
             private String confirmPwd; 两次密码要一致
             private String email; 可以为空，不为空要是一个合法的邮箱
             private String birthday; 可以为空，不为空时，要是一个合法的日期
     */
    public boolean validate(){
        boolean isOK = true;

        if (this.userName == null || this.userName.trim().equals("")){
            isOK = false;
            errors.put("userName","用户名不能为空！！");
        }else{
            if (!this.userName.matches("[a-zA-Z]{3,8}")){
                isOK = false;
                errors.put("userName","用户名必须是3~8位字母！！");
            }
        }
        if (this.userPassword == null || this.userPassword.trim().equals("")) {
            isOK = false;
            errors.put("userPassword", "密码不能为空！！");
        }else {
            if (!this.userPassword.matches("\\d{3,8}")){
                isOK = false;
                errors.put("userPassword","密码必须是3~8位数字！！");
            }
        }

        //两次密码发要一致
        if (this.confirmPassword != null){
            if (!this.confirmPassword.equals(this.userPassword)){
                isOK = false;
                errors.put("confirmPassword","两次密码不一致！！");
            }
        }

        //可以为空，不为空时须是一个合法的邮箱
        if (this.email != null && !this.email.trim().equals("")){
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")){
                isOK = false;
                errors.put("email","不是一个合法的邮箱！！");
            }
        }

        //birthday 可以为空，不为空时是一个合法的日期
        if (this.birthday != null && !this.birthday.trim().equals("")){
            try {
                DateLocaleConverter converter = new DateLocaleConverter();
                converter.convert(this.birthday);
            }catch (Exception e){
                isOK = false;
                errors.put("birthday","生日必须是一个合法的日期！！");
            }
        }

        return isOK;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPwd(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
