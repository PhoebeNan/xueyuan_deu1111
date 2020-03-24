package com.online.edu.statistics.controller;


import com.online.edu.common.R;
import com.online.edu.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @GetMapping("memberRegisterCount/{day}")
    public R getMemberRegisterCountStatisticController(@PathVariable("day") String day){

        Integer memberRegisterCount = dailyService.getMemberRegisterCountStatisticService(day);

        return R.ok().data("memberRegisterCount",memberRegisterCount);
    }

    //{"code":20000,"data":{"token":"admin"}}
    //模拟登陆
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}

