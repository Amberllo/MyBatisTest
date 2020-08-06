package org.myapp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.myapp.data.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisUtils {
    public void init() {

        String resource = "org/myqpp/data/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        // 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
        String resource = "mybatis-configs.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 然后根据 sqlSessionFactory 得到 session
        SqlSession session = sqlSessionFactory.openSession();
        List<UserMapper> listStudent = session.selectList("listUser");
        for (UserMapper user : listStudent) {
            System.out.println("ID:" + user.get_userid() + ",NAME:" + user.get_username());
        }

    }
}
