package com.zsebank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsebank.entity.AppAccount;
import com.zsebank.service.AppAccountService;
import com.zsebank.mapper.AppAccountMapper;
import org.springframework.stereotype.Service;

/**
 * 应用账户表-ServiceImpl
 * **/
@Service
public class AppAccountServiceImpl extends ServiceImpl<AppAccountMapper, AppAccount> implements AppAccountService {

}
