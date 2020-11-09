package com.service;

import com.entity.Apply;
import com.entity.Binapply;
import com.entity.User;
import com.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplyService {

    public void insert(Apply apply) {
        String sqlTxt = "insert into apply ( Aname, Asno, Aclass, Atime, Areason, Acourse, Ateacher, Ainstructor) ";
        sqlTxt +=  "values( ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt, new String[] {apply.getName(), apply.getSno(), apply.getClassno(), apply.getDate(), apply.getReason(), apply.getCourse(), apply.getTeacher(), apply.getInstructor()});
    }

    public int getPages(String field,String condition){
        String sqlTxt = "select count(*) as pages from apply where 1 = 1 ";
        if(!field.equals("default")){
            sqlTxt += " and " + field + " = '" + condition + " ' ";
        }
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        int pages = Integer.parseInt(list.get(0).get("pages").toString());
        return (int)Math.ceil(pages/5.0);
    }

    public List<Apply> showApply(String field,String condition,String page_number,String page_size,String sno){
        List<Apply> applyList = new ArrayList<Apply>();
        String sqlTxt = "select * from apply where Asno = '"+sno+"'";
        if (!field.equals("default")){
            sqlTxt += " and " + field + " like '%" + condition + "%' ";
        }
        //分页的位置
        sqlTxt += " limit " + (Integer.parseInt(page_number) -1)*5 + ", "
                + page_size;

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
            apply.setRight(map.get("Aright").toString());
            applyList.add(apply);
        }
        return applyList;
    }


   /* public List<Apply> showApply(String sno){
        List<Apply> applyList = new ArrayList<Apply>();
        String sqlTxt = "select * from apply where Asno = '"+sno+"'";
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
            apply.setInstructor(map.get("Ainstructor").toString());
            apply.setNo(Integer.parseInt(map.get("Ano").toString()));
            applyList.add(apply);
        }
        return applyList;
    }*/

    public Apply getSingleApply(String no){
        Apply apply = new Apply();
        String sqlTxt = "select * from apply where Ano ='"+ no + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);

        apply.setSno(list.get(0).get("Asno").toString());
        apply.setName(list.get(0).get("Aname").toString());
        apply.setClassno(list.get(0).get("Aclass").toString());
        apply.setDate(list.get(0).get("Atime").toString());
        apply.setReason(list.get(0).get("Areason").toString());
        apply.setCourse(list.get(0).get("Acourse").toString());
        apply.setTeacher(list.get(0).get("Ateacher").toString());
        apply.setInstructor(list.get(0).get("Ainstructor").toString());
        apply.setNo(Integer.parseInt(no));
        return apply;
    }

    public int editApply(Apply apply, String no){
        int flag=0;
        String sqlTxt = "update apply " +
                " set " + "Asno = '" + apply.getSno() +"',"+ "Aname = '" + apply.getName() +"'," +"Aclass = '" + apply.getClassno() +"',"+
                "Atime = '" + apply.getDate() +"'," + "Areason = '" + apply.getReason()+"'," + "Acourse = '" + apply.getCourse() + "'," +
                "Ateacher = '" + apply.getTeacher() + "'," + "Ainstructor = '" + apply.getInstructor() + "' where Ano=" + Integer.parseInt(no);

        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        flag = jdbcTemplate.update(sqlTxt);
        System.out.println("flag service : " + flag);
        return flag;
    }

    public void insertBin(String no){
        String sqlTxt = "insert into bin_apply ( select * from apply where Ano ='" + no +"')";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);
    }

    public void deleteApply(String no){
        String sqlTxt = "delete from apply where Ano = '"+ no + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);

    }

    public List<Binapply> showBinapply(String page_number, String page_size, String sno){
        List<Binapply> binapplyList = new ArrayList<Binapply>();
        String sqlTxt = "select * from bin_apply where Bsno = '"+sno+"'";
        sqlTxt += " limit " + (Integer.parseInt(page_number) -1)*5 + ", "
                + page_size;

        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String,Object> map : list){
            Binapply binapply = new Binapply();
            binapply.setBsno(map.get("Bsno").toString());
            binapply.setBname(map.get("Bname").toString());
            binapply.setBclass(map.get("Bclass").toString());
            binapply.setBtime(map.get("Btime").toString());
            binapply.setBreason(map.get("Breason").toString());
            binapply.setBcourse(map.get("Bcourse").toString());
            binapply.setBteacher(map.get("Bteacher").toString());
            binapply.setBinstructor(map.get("Binstructor").toString());
            binapply.setBno(Integer.parseInt(map.get("Bno").toString()));
            binapply.setBright(map.get("Bright").toString());
            binapplyList.add(binapply);
        }
        return binapplyList;
    }

    public int getPage(String field,String condition){
        String sqlTxt = "select count(*) as pages from bin_apply where 1 = 1 ";
        if(!field.equals("default")){
            sqlTxt += " and " + field + " = '" + condition + " ' ";
        }
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        int pages = Integer.parseInt(list.get(0).get("pages").toString());
        return (int)Math.ceil(pages/5.0);
    }
    public void insertApply(String bno){
        String sqlTxt = "insert into apply ( select * from bin_apply where Bno ='" + bno +"')";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);
    }

    public void deleteBin(String bno){
        String sqlTxt = "delete from bin_apply where Bno = '"+ bno + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);

    }
    public void clearBin(String sno){
        String sqlTxt = "delete from bin_apply where Bsno = '"+ sno + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);
    }
}