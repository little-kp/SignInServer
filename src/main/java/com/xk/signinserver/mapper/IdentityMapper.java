package com.xk.signinserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xk.signinserver.entity.Identity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdentityMapper extends BaseMapper<Identity> {
}
