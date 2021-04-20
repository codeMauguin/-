package com.white;

import com.whit.password.IPassWordUtil;
import com.white.pojo.Count;
import com.white.service.AddAdminCount;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈浩
 * @version 2021/2/6 20:45
 * @cread Talk is cheap. Show me the code
 * @apiNote id->管理员账户
 *          name->管理员名称
 *          pwd管理员账户密码
 * @apiNote 如果需要使用将类上添加注解 {@link Configuration}
 * @apiNote
 */
public class Api implements ApplicationContextAware {
    private ApplicationContext context;
    private String pwd;
    private long id;
    private String name;
    private final IPassWordUtil passwordUtil = IPassWordUtil.getInstance();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        AddAdminCount adminCount = this::AddAdminCount;
        adminCount.AddAdminCount(id, name);
    }

    private void AddAdminCount(long id, String name) {
        final Count count = new Count();
        count.setCountId(id);
        count.setCountSalt(passwordUtil.getSalt());
        // TODO: 2021/2/6 将密码改为自定义的pwd 这里使用Id
        count.setCountPwd(passwordUtil.getPwd(String.valueOf(id), count.getCountSalt()));
        count.setCountInfo(3);
    }
}
