package edu.jit.controller;

import edu.jit.model.MailSet;
import edu.jit.service.IMailSetService;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//邮件告警-设置
@Api(value = "null" , tags = "null")
@RequestMapping(value = "mailset" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class MailSetController {

    @Resource
    private IMailSetService mailSetService;

    @RequestMapping("list")
    public String mailList(Model model){
        MailSet mailSet=new MailSet();
        mailSet.setId("");
        model.addAttribute("mailSet",mailSet);
        return "mail/view";
    }

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String testSentMail(MailSet mailSet,Model model){
        model.addAttribute("mailSet",mailSet);
        boolean result=mailSetService.testSent(mailSet);
        String msg=result?"测试发送成功":"测试发送失败";
        model.addAttribute("msg",msg);
        return "redirect:/mailset/list";
    }

}
