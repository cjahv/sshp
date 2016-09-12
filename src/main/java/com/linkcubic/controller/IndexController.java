package com.linkcubic.controller;

import com.linkcubic.model.entity.user.User;
import com.support.mcv.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController<User> {

    @RequestMapping("index.html")
    public String main() {
        return "index";
    }
}
