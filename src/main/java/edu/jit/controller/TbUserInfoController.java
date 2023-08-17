package edu.jit.controller;

import edu.jit.model.TbUserInfo;
import edu.jit.service.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Api(value = "user_login" , tags = "用户信息管理：包含登录、注册、登出等")
@RequestMapping(value = "/login" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class TbUserInfoController {
    Logger log= LoggerFactory.getLogger(TbUserInfoController.class);
    @Resource
    private ITbUserInfoService tbUserInfoService;
    @Autowired
    private IDeskStateService deskStateService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login/login";
    }
    @RequestMapping("/login")
    public  String login(String userName, String passwd, HttpServletRequest request, Model model){
        //登录请求,用户名和密码
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passwd)){
            log.error("用户名密码不能为空！");
            request.getSession().setAttribute("LOGIN_KEY",null);
            return"redirect:/login/toLogin";
        }
        TbUserInfo user = tbUserInfoService.login(userName,passwd);
        request.getSession().setAttribute("LOGIN_KEY",user);

        if(null!=user){//登录如果成功，则需要把对应的LOGIN_KEY赋值
            log.info("用户登录成功："+user.getUsername());
            deskStateService.initData(model);
            return "index";
        }else{
            log.error("用户不存在，无法登录");
            return "redirect:/login/toLogin";
        }
    }

    //退出到登录界面
    @RequestMapping("loginOut")
    public String logout(){
        return "redirect:/login/toLogin";
    }

    @RequestMapping("toModify")
    public String toModify(HttpServletRequest req,Model model){
        Object user = req.getSession().getAttribute("LOGIN_KEY");
        model.addAttribute("user",user);
        return "login/modify";
    }

    @RequestMapping("saveModify")
    public String saveModify(String username,String pwd,String confirmPwd){
        if(org.apache.commons.lang3.StringUtils.isEmpty(username) || org.apache.commons.lang3.StringUtils.isEmpty(pwd) || org.apache.commons.lang3.StringUtils.isEmpty(confirmPwd)){
            System.out.println("用户名、密码、确认密码不能为空！！");
            return "redirect:/login/toModify";
        }
        if(!pwd.equals(confirmPwd)){
            System.out.println("密码、确认密码不一致！！");
            return "redirect:/login/toModify";
        }
        tbUserInfoService.updateUserPwd(username,pwd);
        System.out.println("密码修改完成，请重新登录！");
        return "redirect:/login/toLogin";
    }

    @RequestMapping("toRegex")
    public String toRegex(){
        return "login/regex";
    }

    @RequestMapping("regex")
    public String regex(String username,String password,String cfmPwd){
        if(org.apache.commons.lang3.StringUtils.isEmpty(username) || org.apache.commons.lang3.StringUtils.isEmpty(password) || org.apache.commons.lang3.StringUtils.isEmpty(cfmPwd)){
            return "redirect:/login/toRegex";
        }
        if(!password.equals(cfmPwd)){
            return "redirect:login/toRegex";
        }
        tbUserInfoService.regex(username,password);
        return "redirect:/login/toLogin";
    }
}
