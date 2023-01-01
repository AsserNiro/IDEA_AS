package com.mapper;

import com.entiy.student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface studentMapper {
    /**
     * ��ѯ��������
     * @return student����ļ���
     */
    List<student> selectAll();

    /**
     * ͨ��id��ѯ
     * ���ж����������δ���
     *  ����1. ÿ������ע�⣬ע���е��ֶ�Ҫ�Ͳ�ѯ�ֶ�һ��@Param(sqlռλ���ֶ�)
     *  ����2. ������������з�װ�ɶ����ٽ����󵱲������룬�����е��ֶ�Ҫ�Ͳ�ѯ�ֶ�һ��
     * @return student����
     */
    student selectById(@Param("id") int id);

    student selectByName(student temp);

    void add(student temp);

    /**
     * �޸�ȫ���򲿷ָ���
     */
    int update(student temp);

    /**
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * ����ɾ��
     * @param ids
     */
    void deleteByIds(@Param("ids") int [] ids);
}
