package com.mapper;

import com.entiy.student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface studentMapper {
    /**
     * 查询所有数据
     * @return student对象的集合
     */
    List<student> selectAll();

    /**
     * 通过id查询
     * 当有多个参数的如何传入
     *  方法1. 每个进行注解，注解中的字段要和查询字段一致@Param(sql占位符字段)
     *  方法2. 将多个参数进行封装成对象，再将对象当参数传入，对象中的字段要和查询字段一致
     * @return student对象
     */
    student selectById(@Param("id") int id);

    student selectByName(student temp);

    void add(student temp);

    /**
     * 修改全部或部分更改
     */
    int update(student temp);

    /**
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int [] ids);
}
