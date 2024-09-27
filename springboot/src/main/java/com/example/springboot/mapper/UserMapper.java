package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //直接利用sql语句插入数据库
    @Insert("insert into public.\"user\" (account,passwords,telephone) values (#{account},#{passwords},#{telephone})")
    void insert(User user);
    @Update("update public.\"user\" set passwords = #{passwords}, telephone = #{telephone} where account = #{account}")
    void updateUser(User user);
    @Delete("delete  from public.\"user\" where account = #{account}")
    void deleteUser(String account);
    @Select("select * from public.\"user\" where account = #{account} and passwords = #{passwords}")
    User selectUser(@Param("account") String account,@Param("passwords") String passwords);
    @Select("select name from public.\"rubbish\"")
    List<String> rubbish();
    @Select("select name from public.\"rubbish\" where index=0")
    List<String> recycle();
    @Select("select name from public.\"rubbish\" where index=1")
    List<String> harm();
    @Select("select name from public.\"rubbish\" where index=2")
    List<String> kitchen();
    @Select("select name from public.\"rubbish\" where index=3")
    List<String> other();
}
