package daoimpl;

import domain.User;
import dao.IUserDao;
import org.dom4j.Document;
import org.dom4j.Element;
import util.XmlUtils;

import java.text.SimpleDateFormat;

/*
IUserDao接口的实现类
@author szp
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public User find(String userName, String userPassword) {
        try {
            Document document = XmlUtils.getDocument();
            //使用XPath表达式来操作XML节点
            Element e = (Element) document.selectSingleNode("//user[@userName='\"+userName+\"' and @userPassword='\"+userPassword+\"']");
            if (e==null){
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPassword(e.attributeValue("userPassword"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public void add(User user) {
        try{
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            Element user_node = root.addElement("user"); //创建user节点，并挂到root
            user_node.setAttributeValue("id",user.getUserName());
            user_node.setAttributeValue("userName",user.getUserName());
            user_node.setAttributeValue("userPassword",user.getUserPassword());
            user_node.setAttributeValue("email",user.getEmail());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user_node.setAttributeValue("birthday",sdf.format(user.getBirthday()));

            XmlUtils.write2Xml(document);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String userName) {
        try{
            Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@userName='"+userName+"']");
            if (e==null){
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPassword(e.attributeValue("userPassword"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}