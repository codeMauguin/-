package com.white.ErrorMannage;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈浩
 * @creed: Talk is cheap,show me the code
 * @Date: 2020/11/26 星期四 13:49
 */
@RestController
@SuppressWarnings("all")
@ConfigurationProperties(prefix = "login")
public class ErrorPage extends BasicErrorController {
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private  String redirectUrl;

    public ErrorPage(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
        }
        return null;
    }

}
