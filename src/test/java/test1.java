import com.entiy.student;
import com.mapper.studentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * mybatis 的测试
 * 包括mybatis的基础使用，以及mapper的代理开发
 */
public class test1 {
    public static void main(String[] args) {
        //1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象,用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//传入参数，将提交改为自动提交

        //3.执行sql
        //List<student> list = sqlSession.selectList("test.selectAll");

        //3.获取studentMapper接口的代理对象#Mapper代理开发
        studentMapper sm = sqlSession.getMapper(studentMapper.class);
//        List<student> list = sm.selectAll();
//        for(student s:list){
//            System.out.println(s);
//        }

        String name = "唐韩宇";
        // name = "%"+name+"%";//模糊查询的需要
        student yuan = new student();
        yuan.setId(5);
        yuan.setChinese(118);
        yuan.setMath(135);
        yuan.setEng(125);
        yuan.setName(name);
        String data = "2022-11-8";
        Date udata = null;
        try {
            udata = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = udata.getTime();
        java.sql.Date sDate = new java.sql.Date(time);
        int [] ids ={4,5};
        sm.deleteByIds(ids);
        List<student> list = sm.selectAll();
        for(student s:list){
            System.out.println(s);
        }
//        sm.deleteById(6);
//        List<student> list = sm.selectAll();
//        for(student s:list){
//            System.out.println(s);
//        }
        //yuan.setData(sDate);
        //int cnt = sm.update(yuan);
        //System.out.println("改变的行数为:"+cnt);

        //sm.add(yuan);
        //int id = yuan.getId();
        //System.out.println(id);
        //手动提交
        //sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }
}
