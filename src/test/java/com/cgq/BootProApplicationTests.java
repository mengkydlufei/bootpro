package com.cgq;

import com.cgq.pojo.SysUser;
import com.cgq.service.SysUserService;
import com.cgq.utils.ParamDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootProApplicationTests {
	@Autowired
	private SysUserService sysUserService;

	@Test
	public void contextLoads() {
		PageHelper.startPage(1,1);
		List<SysUser> list = sysUserService.queryData(new ParamDto());
		PageInfo pageInfo = new PageInfo(list);
		System.out.println(pageInfo);
	}

}
