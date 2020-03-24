package com.online.edu.ucenter.mapper;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
public interface MemberMapper extends BaseMapper<Member> {

    //统计某一天的注册人数
    Integer selectMemberRegisterCount(String day);
}
