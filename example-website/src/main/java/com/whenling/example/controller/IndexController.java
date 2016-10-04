package com.whenling.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whenling.example.api.UserService;

@Controller
public class IndexController {

	@Reference
	private UserService userService;

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("admin", userService.findByUsername("admin"));
		return "/index";
	}


}
