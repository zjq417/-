package com.service;

import com.entity.Apply;
import com.entity.Instructor;
import com.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InstructorService {

    public int getPages(String field,String condition){
        String sqlTxt = "select count(*) as pages from apply where 1=1";
        if(!field.equals("default")){
            sqlTxt += " and " + field + " = '" + condition + "'";
        }
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        int pages = Integer.parseInt(list.get(0).get("pages").toString());
        return (int)Math.ceil(pages/5.0);
    }

    public List<Apply> showIapply(String field, String condition, String page_number, String page_size, String iname ){
        List<Apply> applyList = new ArrayList<Apply>();

        String sqlTxt = "select * from apply where 1=1 and Ainstructor = '" + iname + "'";
        if (!field.equals("default")){
            sqlTxt += " and " + field + " like '%" + condition + "%' ";
        }
        //分页的位置
        sqlTxt += " limit " + (Integer.parseInt(page_number) -1)*5 + ", " + page_size;

        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String,Object> map : list){
            Apply apply = new Apply();
            apply.setSno(map.get("Asno").toString());
            apply.setName(map.get("Aname").toString());
            apply.setClassno(map.get("Aclass").toString());
            apply.setDate(map.get("Atime").toString());
            apply.setReason(map.get("Areason").toString());
            apply.setCourse(map.get("Acourse").toString());
            apply.setTeacher(map.get("Ateacher").toString());
            apply.setInstructor(map.get("Ainstructor").toString());
            apply.setNo(Integer.parseInt(map.get("Ano").toString()));
            if(map.get("Aright").toString().equals("审核中")){
                apply.setRight("未处理");
            }
            else{
                if(map.get("Aright").toString().equals("辅导员已通过")){
                    apply.setRight("已同意");
                }
                else{
                    apply.setRight("已拒绝");
                }
            }
            applyList.add(apply);
        }
        return applyList;
    }

    public void manageApply(String status, String no){
        String info = "";
        if(status.equals("1")){
            info = "辅导员已通过";
        } else {
            info = "辅导员已拒绝";
        }
        String sqlTxt = "update apply ";
        sqlTxt += " set Aright = '" + info + "' " ;
        sqlTxt += "where Ano = '" + no + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);
    }
}
