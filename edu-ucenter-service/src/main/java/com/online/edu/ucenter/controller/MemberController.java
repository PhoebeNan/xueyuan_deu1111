package com.online.edu.ucenter.controller;


import com.online.edu.common.R;
import com.online.edu.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("memberRegisterCount/{day}")
    public R getMemberRegisterCount(@PathVariable("day") String day){

        Integer resultCount = memberService.getMemberRegisterCount(day);

        return R.ok().data("memberRegisterCount",resultCount);
    }

}

