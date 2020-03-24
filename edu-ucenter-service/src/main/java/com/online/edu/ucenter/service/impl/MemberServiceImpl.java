package com.online.edu.ucenter.service.impl;

import com.online.edu.ucenter.entity.Member;
import com.online.edu.ucenter.mapper.MemberMapper;
import com.online.edu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Override
    public Integer getMemberRegisterCount(String day) {
        return baseMapper.selectMemberRegisterCount(day);
    }
}
