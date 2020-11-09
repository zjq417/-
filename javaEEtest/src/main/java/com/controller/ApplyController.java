package com.controller;

import com.entity.Apply;
import com.entity.Binapply;
import com.entity.User;
import com.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService service;

    @RequestMapping("InsertApply")
    public String insertApply(Apply apply) {
//        System.out.println(apply);
        service.insert(apply);
        return "main";
    }

    @RequestMapping("show_apply_edit")
    public String showApplyEdit(HttpServletRequest request,Model model){
        String no = request.getParameter("no");
//        System.out.println("sno : " + sno) ;
        Apply apply = service.getSingleApply(no);
        model.addAttribute("apply",apply);
        request.getSession().setAttribute("no", no);
        return "edit_apply";
    }

    @RequestMapping("edit_apply")
    public String editApply(HttpServletRequest request){
        Apply apply = new Apply();
        apply.setSno(request.getParameter("sno"));
        apply.setName(request.getParameter("name"));
        apply.setClassno(request.getParameter("classno"));
        apply.setDate(request.getParameter("date"));
        apply.setReason(request.getParameter("reason"));
        apply.setCourse(request.getParameter("course"));
        apply.setTeacher(request.getParameter("teacher"));
        apply.setInstructor(request.getParameter("instructor"));

        int flag = service.editApply(apply, (String) request.getSession().getAttribute("no"));
        request.getSession().removeAttribute("no");
        if (flag <= 0)
            return "redirect:InsertApply";
        else
            return "redirect:shows_n";
    }


    @RequestMapping("shows_n")
    public String showsn(){
        return "main";
    }

    @RequestMapping("show_apply")
    public String showApply(HttpServletRequest request, Model model){
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String sno = (String) request.getSession().getAttribute("GlobalSno");
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
/*        System.out.println("field:"+field);
        System.out.println("condition:"+condition);
        System.out.println("page_number:"+page_number);
        System.out.println("page_size:"+page_size);*/
        List<Apply> applyList =service.showApply(field,condition,page_number,page_size,sno);
        model.addAttribute("apply_list",applyList);
        int pages = 0;
        pages = service.getPages(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);

        return "show_apply";
    }
    @RequestMapping("show_apply_delete")
    public String showApplyDelete(HttpServletRequest request){
        String no = request.getParameter("no");
        service.insertBin(no);
        service.deleteApply(no);
        return "redirect:shows_n";
    }
/*    @RequestMapping("show_apply")
    public String showApply(HttpServletRequest request, Model model){
        String sno = (String) request.getSession().getAttribute("GlobalSno");
        List<Apply> applyList = service.showApply(sno);
        model.addAttribute("apply_list",applyList);
        return "show_apply";
    }*/
    @RequestMapping("bin_apply")
    public String binApply(HttpServletRequest request , Model model){
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        String sno = (String) request.getSession().getAttribute("GlobalSno");
        System.out.println("sno3333 : " +sno);
        if(page_number==null){
            page_number="1";
            page_size="5";
        }
        List<Binapply> binapplyList =service.showBinapply(page_number,page_size,sno);
        model.addAttribute("binapply_list",binapplyList);
        int pages = 0;
        pages = service.getPage(field,condition);
        model.addAttribute("field",field);
        model.addAttribute("condition",condition);
        model.addAttribute("page_number",page_number);
        model.addAttribute("page_size",page_size);
        model.addAttribute("pages",pages);
        return "Bin_apply";
    }

    @RequestMapping("return_apply")
    public String returnApply(HttpServletRequest request ){
        String bno = request.getParameter("bno");
        service.insertApply(bno);
        service.deleteBin(bno);
        return "redirect:shows_n";
    }

    @RequestMapping("clear_bin")
    public String clearBin(HttpServletRequest request){
        String sno = (String) request.getSession().getAttribute("GlobalSno");
        System.out.println("sno : " +sno);
        service.clearBin(sno);
        return "redirect:shows_n";
    }
}
