package com.controller;

import com.entity.Apply;
import com.service.PartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PartmentController {
    @Autowired
    private PartmentService service;

    @RequestMapping("show_papply")
    public String showPapply(HttpServletRequest request, Model model){
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String info = "辅导员已通过";
//        String iname = (String) request.getSession().getAttribute("Global_iName");
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Apply> applyList =service.showPapply(field,condition,page_number,page_size,info);
        model.addAttribute("apply_list",applyList);
        int pages = 0;
        pages = service.getPages(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);

        return "show_papply";
    }

    @RequestMapping("manage_papply")
    public String manageApply(HttpServletRequest request){
        String no = request.getParameter("no");
        String right = request.getParameter("status");
        service.managePapply(right,no);
        return "Pmain";
    }

    @RequestMapping("untreated_papply")
    public String untreatedApply(HttpServletRequest request , Model model){
        String field = "Aright";
        String condition = "辅导员";
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String info = "辅导员已通过";
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Apply> applyList =service.showPapply(field,condition,page_number,page_size,info);
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
