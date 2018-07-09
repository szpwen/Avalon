package domain;

import java.io.Serializable;
import java.util.Date;
/*
 @author szp
 用户实体类
 */
public class User implements Serializable{
    //定义序列号
    private static final long serialVersionUID=1234567890L;
    //用户ID
    private String id;
    private String userName;
    private String userPassword;
    private String email;
    private Date birthday;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public void setUserPassword(String userPassword){
        this.userPassword=userPassword;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

