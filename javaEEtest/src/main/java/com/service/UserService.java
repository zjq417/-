package com.service;

import com.entity.Instructor;
import com.entity.Partment;
import com.entity.Teacher;
import com.entity.User;
import com.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public User checkUser(String username, String password) {
        User user =null;
        String sqlTxt = " select * from stu_info where Sno = '"+username+"'and password = '"+password+"'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        System.out.println(list.size());
        if (list.size() > 0) {
            user = new User();
            user.setSno(list.get(0).get("Sno").toString());
            user.setPassword(list.get(0).get("password").toString());
        }
        return user;
    }
    public Teacher checkTeacher(String username, String password){
        Teacher teacher = null;
        String sqlTxt = "select * from teacher where Tno = '"+username+"'and Tpwd = '"+password+"'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        System.out.println(list.size());
        if(list.size() > 0){
            teacher = new Teacher();
            teacher.setTno(list.get(0).get("Tno").toString());
            teacher.setTpwd(list.get(0).get("Tpwd").toString());
            teacher.setTname(list.get(0).get("Tname").toString());
        }
        return teacher;
    }
    public Instructor CheckInstructor(String username,String password){
        Instructor instructor = null;
        String sqlTxt = "select * from instructor where Ino = '"+username+"'and Ipwd ='"+password+"'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        System.out.println(list.size());
        if(list.size()>0){
            instructor = new Instructor();
            instructor.setIno(list.get(0).get("Ino").toString());
            instructor.setIpwd(list.get(0).get("Ipwd").toString());
            instructor.setIname(list.get(0).get("Iname").toString());
        }
        return instructor;
    }
    public Partment CheckPartment(String username,String password){
        Partment partment = null;
        String sqlTxt = "select * from partment where Pno = '"+username+"'and Pwd = '"+password+"'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        System.out.println(list.size());
        if(list.size()>0){
            partment = new Partment();
            partment.setPno(list.get(0).get("Pno").toString());
            partment.setPwd(list.get(0).get("Pwd").toString());
        }
        return partment;
    }

}
