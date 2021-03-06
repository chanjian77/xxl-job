package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.constant.Constants.JobGroupEnum;
import com.xxl.job.admin.core.model.ReturnT;
import com.xxl.job.admin.service.IXxlJobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {
	
	@Resource
	private IXxlJobService xxlJobService;
	
	@RequestMapping
	public String index(Model model) {
		model.addAttribute("JobGroupList", JobGroupEnum.values());			// 任务组列表
		return "jobinfo/jobinfo.index";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,  
			@RequestParam(required = false, defaultValue = "10") int length,
			String jobGroup, String jobDesc, String filterTime) {
		
		return xxlJobService.pageList(start, length, jobGroup, jobDesc, filterTime);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(String jobGroup, String jobCron, String jobDesc,
			String executorAddress, String executorParam, String author, String alarmEmail,
			int glueSwitch, String glueSource, String glueRemark) {
		
		return xxlJobService.add(jobGroup, jobCron, jobDesc, executorAddress, executorParam,
				author, alarmEmail, glueSwitch, glueSource, glueRemark);
	}
	
	@RequestMapping("/reschedule")
	@ResponseBody
	public ReturnT<String> reschedule(String jobGroup, String jobName, String jobCron, String jobDesc,
			String executorAddress, String executorParam, String author, String alarmEmail) {

		return xxlJobService.reschedule(jobGroup, jobName, jobCron, jobDesc, executorAddress, executorParam, author, alarmEmail);
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public ReturnT<String> remove(String jobGroup, String jobName) {
		return xxlJobService.remove(jobGroup, jobName);
	}
	
	@RequestMapping("/pause")
	@ResponseBody
	public ReturnT<String> pause(String jobGroup, String jobName) {
		return xxlJobService.pause(jobGroup, jobName);
	}
	
	@RequestMapping("/resume")
	@ResponseBody
	public ReturnT<String> resume(String jobGroup, String jobName) {
		return xxlJobService.resume(jobGroup, jobName);
	}
	
	@RequestMapping("/trigger")
	@ResponseBody
	public ReturnT<String> triggerJob(String jobGroup, String jobName) {
		return xxlJobService.triggerJob(jobGroup, jobName);
	}
	
}
