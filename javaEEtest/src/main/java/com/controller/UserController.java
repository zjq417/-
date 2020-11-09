package com.controller;

import com.entity.Instructor;
import com.entity.Partment;
import com.entity.Teacher;
import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("show_login")
    public String showLogin(){return "login";}

    @RequestMapping("show_main")
    public String showMain(){return "main";}

    @RequestMapping("show_tmain")
    public String showTmain(){
        return "Tmain";
    }

    @RequestMapping("show_imain")
    public String showImain(){
        return "Imain";
    }

    @RequestMapping("show_pmain")
    public String showPmain(){
        return "Pmain";
    }

    @RequestMapping("CheckLogin")
    public String checkLogin(HttpServletRequest request) {
        String type = request.getParameter("type");
        if (type.equals("student")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            request.getSession().setAttribute("GlobalSno", username);
            User user =this.service.checkUser(username,password);
            if(user==null) {
                System.out.println(username);
                System.out.println(password);
                System.out.println("1");
                return "redirect:show_login";
            }
            else{
//                System.out.println(username);
//                System.out.println(password);
//                System.out.println("0");
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                return "redirect:show_main";
            }
        }
        else if(type.equals("teacher")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Teacher teacher = this.service.checkTeacher(username,password);
            String tName = teacher.getTname();
            request.getSession().setAttribute("Global_tName",tName);
            if (teacher==null){
                System.out.println(username);
                System.out.println(password);
                System.out.println("1");
                return "redirect:show_login";
            }
            else{
                HttpSession session = request.getSession();
                session.setAttribute("teacher",teacher);
                return "redirect:show_tmain";
            }
        }
        else if(type.equals("instructor")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//            request.getSession().setAttribute("GlobalSno", );
            Instructor instructor = this.service.CheckInstructor(username,password);
            String iName = instructor.getIname();
            request.getSession().setAttribute("Global_iName", iName);
            if (instructor==null){
                System.out.println(username);
                System.out.println(password);
                System.out.println("1");
                return "redirect:show_login";
            }
            else{
                HttpSession session = request.getSession();
                session.setAttribute("instructor",instructor);
                return "redirect:show_imain";
            }
        }
        else{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Partment partment = this.service.CheckPartment(username,password);
            if(partment==null){
                return "redirect:show_login";
            }
            else{
                HttpSession session = request.getSession();
                session.setAttribute("partment",partment);
                return "redirect:show_pmain";
            }
        }
    }
    @RequestMapping("insert_apply")
    public String insertApply(){
        return "apply";
    }
}
