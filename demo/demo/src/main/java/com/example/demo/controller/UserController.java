package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private Usermapper usermapper;

    @RequestMapping("/login")
    public boolean login(@RequestBody Userlogin userlogin) {

        boolean iffind = false;
        for (int i = 0; i < usermapper.findAll().size(); i++) {

            if (usermapper.findAll().get(i).getUsername().compareTo(userlogin.getUsername()) == 0
                    && usermapper.findAll().get(i).getPwd().compareTo(userlogin.getPwd()) == 0 &&
                    usermapper.findAll().get(i).getUsercharacter().compareTo(userlogin.getUsercharacter()) == 0) {
                iffind = true;
            }
        }

        return iffind;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {

        int x = usermapper.insertUser(user);
        if (x > 0) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/addstudent")
    public boolean addstudent(@RequestBody Student student) {

        boolean ifhaveadded = false;
        for (int i = 0; i < usermapper.findAllStudent().size(); i++) {

            if (usermapper.findAllStudent().get(i).getUsername().compareTo(student.getUsername()) == 0
                    && usermapper.findAllStudent().get(i).getAddtime().compareTo(student.getAddtime()) == 0) {
                ifhaveadded = true;
            }
        }
        if (ifhaveadded||student.getEnd().compareTo("")==0||student.getStart().compareTo("")==0)
            return false;
        else {
            boolean end = false;
            for (int i = 0; i < usermapper.findAll().size(); i++) {
                if (usermapper.findAll().get(i).getUsername().compareTo(student.getUsername()) == 0) {
                    student.setCname(usermapper.findAll().get(i).getCname());
                    student.setCollege(usermapper.findAll().get(i).getCollege());
                    student.setGrade(usermapper.findAll().get(i).getGrade());
                    int x = usermapper.insertStudent(student);
                    if (x > 0) {
                        end = true;
                    } else {
                        end = false;
                    }
                }
            }
            return end;
        }
    }

    @RequestMapping("/returnstudents")
    public List<Student> returnstudents(@RequestBody String username) {
        System.out.println(username);
        List<Student> end=new ArrayList<>();
        ;
        for (int i = 0; i < usermapper.findAllStudent().size(); i++) {
            if (usermapper.findAllStudent().get(i).getUsername().compareTo(username) == 0)
            {

                end.add(usermapper.findAllStudent().get(i));}
        }
        return end;
    }

    @RequestMapping("/showmap")
    public List<String> showmap(@RequestBody String username) {
        List<String> end=new ArrayList<>();
        ;
        for (int i = usermapper.findAllStudent().size()-1; i >=0; i--) {
            if (usermapper.findAllStudent().get(i).getUsername().compareTo(username) == 0)
            {
                end.add(usermapper.findAllStudent().get(i).getStart());
                end.add(usermapper.findAllStudent().get(i).getEnd());
                break;
            }
        }
        return end;
    }
    @RequestMapping("/returnteacher")
    public List<Teacherneedstudent> returnteacherneedstudents(@RequestBody String username) {
        List<Teacherneedstudent> end = new ArrayList<>();
        String college = "";
        String grade = "";
        for (int i = 0; i < usermapper.findAll().size(); i++) {
            if (usermapper.findAll().get(i).getUsercharacter().compareTo("headteacher") == 0
                    && usermapper.findAll().get(i).getUsername().compareTo(username) == 0) {
                college = usermapper.findAll().get(i).getCollege();
                grade = usermapper.findAll().get(i).getGrade();
                break;
            }
        }
        for (int i = 0; i < usermapper.findAllStudent().size(); i++) {
            if (usermapper.findAllStudent().get(i).getCollege().compareTo(college) == 0
                    && usermapper.findAllStudent().get(i).getGrade().compareTo(grade) == 0) {
                Teacherneedstudent temp = new Teacherneedstudent();
                temp.setAddtime(usermapper.findAllStudent().get(i).getAddtime());
                temp.setCname(usermapper.findAllStudent().get(i).getCname());
                temp.setCollege(usermapper.findAllStudent().get(i).getCollege());
                temp.setGrade(usermapper.findAllStudent().get(i).getGrade());
                end.add(temp);
            }
        }
        return end;
    }
    @RequestMapping("/returnsportteacher")
 public   List<Studentaddnum> findALLaddstudent(){
        List<Studentaddnum> end=new ArrayList<>();
        for(int i=0;i<usermapper.findALLaddstudent().size();i++){
            Studentaddnum temp=new Studentaddnum();
      temp.setAddtime(usermapper.findALLaddstudent().get(i).getAddtime());
      temp.setCollege(usermapper.findALLaddstudent().get(i).getCollege());
temp.setCollegeaddsum(usermapper.countstudent().get(i));

            for(int j=0;j<usermapper.countcollege().size();j++){
                if(usermapper.findALLaddstudent().get(i).getCollege().compareTo(usermapper.returncollege().get(j))==0)
                {temp.setCollegesum(usermapper.countcollege().get(j));}
            }
      end.add(temp);
        }
return end;
    }

    @RequestMapping("/returnteacherbystudent")
    public List<Teacherneedstudent> returnteacherneedstudentsbystudent(@RequestBody String studentname) {
        List<Teacherneedstudent> end = new ArrayList<>();
        List<Student> temp = usermapper.returnteacherneedstudentsbystudent(studentname);
        for(int i=0;i< temp.size();i++){
            Teacherneedstudent tempstudent = new Teacherneedstudent();
            tempstudent.setAddtime(temp.get(i).getAddtime());
            tempstudent.setCname(temp.get(i).getCname());
            tempstudent.setGrade(temp.get(i).getGrade());
            tempstudent.setCollege(temp.get(i).getCollege());
            end.add(tempstudent);
        }

        return end;
    }

    @RequestMapping("/returnsportteacherbycollege")
    public List<Studentaddnum> returnsportteacher(@RequestBody String college) {
        List<Studentaddnum> end = new ArrayList<>();
        List<Student> temp = usermapper.returnsportteacher(college);
        for(int i=0;i< temp.size();i++){
            Studentaddnum tempstudent = new Studentaddnum();
            tempstudent.setAddtime(temp.get(i).getAddtime());
            tempstudent.setCollege(temp.get(i).getCollege());
            tempstudent.setCollegeaddsum(usermapper.countstudent().get(i));
            for(int j=0;j<usermapper.countcollege().size();j++){
                if(usermapper.findALLaddstudent().get(i).getCollege().compareTo(usermapper.returncollege().get(j))==0)
                {tempstudent.setCollegesum(usermapper.countcollege().get(j));}
            }
            end.add(tempstudent);
        }

        return end;
    }

}
