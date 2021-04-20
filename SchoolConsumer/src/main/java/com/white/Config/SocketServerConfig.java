package com.white.Config;

import com.whit.Socket.SocketBuildConfig.SocketBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/29 22:47
 */
@Configuration
public class SocketServerConfig extends SocketBuilder {
    /**
     * 随机提供服务器
     * @return 在可用服务器内随机选择提供服务
     */
    @Override
    public int getCount() {
        final int size = nodes.size();
        if (size==0){
            throw new RuntimeException("没有服务器提供服务");
        }
        return (int) (Math.random() * size);
    }

}
