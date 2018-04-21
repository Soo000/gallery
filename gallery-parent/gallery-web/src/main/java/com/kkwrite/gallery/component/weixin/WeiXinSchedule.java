package com.kkwrite.gallery.component.weixin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkwrite.gallery.component.MyConfig;
import com.kkwrite.gallery.service.param.ParamService;

@Component
public class WeiXinSchedule {
	
	//@Autowired
	private MyConfig myConfig;
	@Autowired
	private ParamService paramService;
	
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
	
	@PostConstruct
	public void init() {
		int initialDelay = 5; // 5 秒以后开始执行 
		int period = 7100; // 7100s 轮询一次
		WeiXinTokenUtil weiXinTokenUtil = new WeiXinTokenUtil(myConfig, paramService);
		scheduledExecutorService.scheduleAtFixedRate(weiXinTokenUtil, initialDelay, period, TimeUnit.SECONDS);
	}
	
}
