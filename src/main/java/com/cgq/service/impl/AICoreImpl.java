package com.cgq.service.impl;

import com.cgq.service.AICore;
import org.springframework.stereotype.Service;

/**
 *价值上亿AI核心代码
 */
@Service
public class AICoreImpl implements AICore {
    @Override
    public String chart(String str) {
        str = str.replace("我帅吗","比吴亦凡还帅");
        str = str.replace("我美吗","美若天仙");
        str = str.replace("吗","");
        str = str.replace("你是谁","我是你的小可爱");
        str = str.replace("你","我");
        str = str.replace("？","！");
        str = str.replace("?","!");
        return str;
    }
}
