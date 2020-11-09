package com.controller;

import com.entity.Apply;
import com.entity.Instructor;
import com.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.List;

@Controller
public class InstructorController {
    @Autowired
    private InstructorService service;

    @RequestMapping("show_iapply")
    public String showIapply(HttpServletRequest request, Model model){
        String field = request.getParameter("field");
//        System.out.println("打印的是这个"+field);
        String condition = request.getParameter("condition");
        if(condition.equals("已处理")){
            condition = "辅导员";
        }
        else if(condition.equals("未处理")){
            condition = "审核中";
        }
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String iname = (String) request.getSession().getAttribute("Global_iName");
//        System.out.println("iName : " + iname);
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
/*        System.out.println("field:"+field);
        System.out.println("condition:"+condition);
        System.out.println("page_number:"+page_number);
        System.out.println("page_size:"+page_size);*/
        List<Apply> applyList =service.showIapply(field,condition,page_number,page_size,iname);
        model.addAttribute("apply_list",applyList);
        int pages = 0;
        pages = service.getPages(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);

        return "show_iapply";
    }

    @RequestMapping("manage_apply")
    public String manageApply(HttpServletRequest request){
        String no = request.getParameter("no");
//        System.out.println("no : " + no);
        String right = request.getParameter("status");
//        System.out.println("status : " + right);
        service.manageApply(right,no);
        return "Imain";
    }

    @RequestMapping("untreated_apply")
    public String untreatedApply(HttpServletRequest request , Model model){
        String field = "Aright";
        String condition = "审核中";
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String iname = (String) request.getSession().getAttribute("Global_iName");
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Apply> applyList =service.showIapply(field,condition,page_number,page_size,iname);
        model.addAttribute("apply_list",applyList);
        int pages = 0;
        pages = service.getPages(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);
        return "untreated_apply";
    }
}
