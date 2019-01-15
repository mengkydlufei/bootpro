package com.cgq.controller;

import com.cgq.service.AICore;
import com.cgq.vo.ChartVo;
import com.cgq.vo.ResultTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AIController {
    @Autowired
    private AICore aiCore;
    @PostMapping("aiChart")
    public ResultTemp chart(@RequestBody ChartVo chartVo){
        ResultTemp resultTemp = new ResultTemp();
        resultTemp.setData(aiCore.chart(chartVo.getInfo()));
        resultTemp.setCode(200);
        resultTemp.setMsg("ok");
        return resultTemp;
    }
}
