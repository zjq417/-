package com.controller;

import com.entity.Apply;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService service;

    @RequestMapping("show_tapply")
    public String showTapply(HttpServletRequest request , Model model){
        String tName = (String) request.getSession().getAttribute("Global_tName");
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Apply> applyList =service.showTapply(field,condition,page_number,page_size,tName);
        model.addAttribute("apply_list",applyList);
        int pages = 0;
        pages = service.getPages(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);

        return "show_tapply";
    }

    @RequestMapping("manage_tapply")
    public String manageApply(HttpServletRequest request){
        String no = request.getParameter("no");
        String right = request.getParameter("status");
        service.manageTapply(right,no);
        return "Tmain";
    }

    @RequestMapping("untreated_tapply")
    public String untreatedApply(HttpServletRequest request , Model model){
        String field = "Aright";
        String condition = "教务已通过";
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String tName = (String) request.getSession().getAttribute("Global_tName");
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Apply> applyList =service.showTapply(field,condition,page_number,page_size,tName);
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
