package com.example.demo.mapper;

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Usermapper {

  @Insert("insert into usertable(usercharacter,username,pwd,college,grade,cname) values(#{usercharacter},#{username},#{pwd},#{college},#{grade},#{cname})")
  int insertUser(User user);

  @Select("select * from usertable")
  List<User> findAll();

  @Insert("insert into student(addtime,cname,start,end,username,college,grade) values(#{addtime},#{cname},#{start},#{end},#{username},#{college},#{grade})")
  int insertStudent(Student student);

  @Select("select * from student order by addtime desc")
  List<Student> findAllStudent();

  @Select("SELECT college,addtime FROM student GROUP BY addtime,college ORDER BY addtime DESC")
  List<Studentaddnum> findALLaddstudent();
  @Select("select count(*) FROM student GROUP BY addtime,college ORDER BY addtime DESC")
  List<Integer> countstudent();
@Select("SELECT COUNT(*) AS collegesum FROM usertable WHERE usercharacter='student' GROUP BY college")
  public List<Integer> countcollege();
@Select("SELECT college FROM usertable WHERE usercharacter='student' GROUP BY college")
  public List<String> returncollege();
  @Select("SELECT college FROM student  GROUP BY college")
  public List<String> returncollegefromstudent();
  @Select("select * from student where cname=#{cname}")
  public List<Student> returnteacherneedstudentsbystudent(String cname);
  @Select("select * from student where college=#{college}")
  public List<Student> returnsportteacher(String college);
}

