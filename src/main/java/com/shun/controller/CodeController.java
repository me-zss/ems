package com.shun.controller;

import com.shun.util.SecurityCode;
import com.shun.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author shun
 */
@Controller
@RequestMapping("code")
public class CodeController {
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = SecurityCode.getSecurityCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
    }
}
