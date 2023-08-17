package edu.jit.service.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import edu.jit.model.MailSet;
import edu.jit.mapper.MailSetMapper;
import edu.jit.service.IMailSetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class MailSetService extends ServiceImpl<MailSetMapper , MailSet> implements IMailSetService {
    Logger logger=LoggerFactory.getLogger(MailSetService.class);
    @Resource
    private MailSetMapper mailSetMapper;

    @Override
    public boolean testSent(MailSet mailSet) {
        MailAccount account=new MailAccount();
        account.setHost(mailSet.getSmtpHost());
        account.setPass(mailSet.getFromPwd());
        account.setPort(Integer.valueOf(mailSet.getSmtpPort()));
        logger.info(mailSet.getSmtpSSL());
        account.setUser(mailSet.getFromMailName().split("@")[0]);
        //account.setSslEnable(mailSet.getSmtpSSl());
        String title="这是一个标题";
        String content="邮件正文在这里";
        File f=new File("wgcloud.2023-07-18.log");
        account.setFrom(mailSet.getFromMailName());
        account.setSslEnable(false);
        account.setAuth(false);
        account.setDebug(true);
        MailUtil.send(account,mailSet.getToMail(),title,content,false,f);
        return true;
    }
}
