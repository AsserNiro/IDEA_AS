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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class testSystem {
    private String resource = "mybatis-config.xml";
    private studentMapper sm;
    private SqlSession sqlSession;
    public String getResource() {
        return resource;
    }
    public void setSm(studentMapper sm){
        this.sm = sm;
    }
    public static void main(String[] args) {
        System.out.println("���ݿ�t_record���ݱ�");
        int cur;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        testSystem ts = new testSystem();
        ts.init();
        while(flag){

            System.out.println("1.��ѯ����ѧ������");
            System.out.println("2.���ѧ������");
            System.out.println("3.����ѧ������");
            System.out.println("4.ɾ��ѧ������");
            cur = sc.nextInt();
            switch(cur){
                case 1:
                    ts.seachAll();break;
                case 2:
                    ts.add();break;
                case 3:
                    ts.updateById();break;
                case 4:
                    ts.delete();break;
                default:flag=false;break;
            }
        }
        ts.close();
    }
    public void init(){
        resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
         sm = sqlSession.getMapper(studentMapper.class);
    }
    public void close(){
        sqlSession.close();
    }
    public List<student> seachAll(){
        List<student> list = new ArrayList();
        list = sm.selectAll();
        for(student stu:list){
            System.out.println(stu);
        }
        System.out.println("��ѯ�ɹ�");
        return list;
    }

    public void add(){
        Scanner sc=new Scanner(System.in);
        student stu = new student();
        System.out.println("��д����");
        String name = sc.next();
        stu.setName(name);
        System.out.println("��д���ĳɼ�");
        int chinese = sc.nextInt();
        stu.setChinese(chinese);
        System.out.println("��д��ѧ�ɼ�");
        int math = sc.nextInt();
        stu.setMath(math);
        System.out.println("��дӢ��ɼ�");
        int eng = sc.nextInt();
        stu.setEng(eng);
        System.out.println("��д��¼ʱ�䣬�硱2022-11-17��");
        String data = sc.next();
        Date udata = null;
        try {
            udata = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = udata.getTime();
        java.sql.Date sDate = new java.sql.Date(time);
        stu.setData(sDate);
        sm.add(stu);
    }

    public void updateById(){
        System.out.println("��ǰѧ������");
        List<student> list= seachAll();
        Scanner sc = new Scanner(System.in);
        System.out.println("����Ҫ����ѧ�����ݵ�id");
        int id = sc.nextInt();
        student stu = null;
        for(student tem :list){
            if(id == tem.getId()){
                stu=tem;
                break;
            }
        }
        if(stu==null){
            System.out.println("δ�ҵ���ѧ��");
            return;
        }
        System.out.println("�Ƿ��������(Y/N)");
        String tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("��������");
            String name = sc.next();
            stu.setName(name);
        }
        System.out.println("�Ƿ�������ĳɼ�(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("�������ĳɼ�");
            int chinese = sc.nextInt();
            stu.setChinese(chinese);
        }
        System.out.println("�Ƿ������ѧ�ɼ�(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("������ѧ�ɼ�");
            int math = sc.nextInt();
            stu.setMath(math);
        }
        System.out.println("�Ƿ����Ӣ��ɼ�(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("����Ӣ��ɼ�");
            int eng = sc.nextInt();
            stu.setEng(eng);
        }
        System.out.println("�Ƿ��������(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("��������");
            String data = sc.next();
            Date udata = null;
            try {
                udata = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long time = udata.getTime();
            java.sql.Date sDate = new java.sql.Date(time);
            stu.setData(sDate);
        }
        sm.update(stu);
    }
    public void delete(){
        System.out.println("��ǰѧ������");
        List<student> list= seachAll();
        Scanner sc = new Scanner(System.in);
        System.out.println("����Ҫɾ��ѧ�����ݵ�id");
        int id = sc.nextInt();
        sm.deleteById(id);
    }
}
