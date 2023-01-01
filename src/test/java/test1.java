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
 * mybatis �Ĳ���
 * ����mybatis�Ļ���ʹ�ã��Լ�mapper�Ĵ�����
 */
public class test1 {
    public static void main(String[] args) {
        //1.����mybatis�ĺ��������ļ�����ȡSqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.��ȡSqlSession����,������ִ��sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//������������ύ��Ϊ�Զ��ύ

        //3.ִ��sql
        //List<student> list = sqlSession.selectList("test.selectAll");

        //3.��ȡstudentMapper�ӿڵĴ������#Mapper������
        studentMapper sm = sqlSession.getMapper(studentMapper.class);
//        List<student> list = sm.selectAll();
//        for(student s:list){
//            System.out.println(s);
//        }

        String name = "�ƺ���";
        // name = "%"+name+"%";//ģ����ѯ����Ҫ
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
        //System.out.println("�ı������Ϊ:"+cnt);

        //sm.add(yuan);
        //int id = yuan.getId();
        //System.out.println(id);
        //�ֶ��ύ
        //sqlSession.commit();
        //4.�ͷ���Դ
        sqlSession.close();
    }
}
