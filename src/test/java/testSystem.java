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
        System.out.println("数据库t_record数据表");
        int cur;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        testSystem ts = new testSystem();
        ts.init();
        while(flag){

            System.out.println("1.查询所有学生数据");
            System.out.println("2.添加学生数据");
            System.out.println("3.更新学生数据");
            System.out.println("4.删除学生数据");
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
        System.out.println("查询成功");
        return list;
    }

    public void add(){
        Scanner sc=new Scanner(System.in);
        student stu = new student();
        System.out.println("填写姓名");
        String name = sc.next();
        stu.setName(name);
        System.out.println("填写语文成绩");
        int chinese = sc.nextInt();
        stu.setChinese(chinese);
        System.out.println("填写数学成绩");
        int math = sc.nextInt();
        stu.setMath(math);
        System.out.println("填写英语成绩");
        int eng = sc.nextInt();
        stu.setEng(eng);
        System.out.println("填写记录时间，如”2022-11-17“");
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
        System.out.println("当前学生数据");
        List<student> list= seachAll();
        Scanner sc = new Scanner(System.in);
        System.out.println("输入要更新学生数据的id");
        int id = sc.nextInt();
        student stu = null;
        for(student tem :list){
            if(id == tem.getId()){
                stu=tem;
                break;
            }
        }
        if(stu==null){
            System.out.println("未找到该学生");
            return;
        }
        System.out.println("是否更改姓名(Y/N)");
        String tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("更改姓名");
            String name = sc.next();
            stu.setName(name);
        }
        System.out.println("是否更改语文成绩(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("更改语文成绩");
            int chinese = sc.nextInt();
            stu.setChinese(chinese);
        }
        System.out.println("是否更改数学成绩(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("更改数学成绩");
            int math = sc.nextInt();
            stu.setMath(math);
        }
        System.out.println("是否更改英语成绩(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("更改英语成绩");
            int eng = sc.nextInt();
            stu.setEng(eng);
        }
        System.out.println("是否更改日期(Y/N)");
        tag = sc.next();
        if(tag.equals("Y")){
            System.out.println("更改日期");
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
        System.out.println("当前学生数据");
        List<student> list= seachAll();
        Scanner sc = new Scanner(System.in);
        System.out.println("输入要删除学生数据的id");
        int id = sc.nextInt();
        sm.deleteById(id);
    }
}
