package com.online.edu.ucenter.service;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
public interface MemberService extends IService<Member> {

    /**
     * 统计某一天的注册人数
     * @param day
     * @return
     */
    Integer getMemberRegisterCount(String day);
}
