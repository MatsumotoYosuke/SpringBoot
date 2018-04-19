package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("Login");
		mav.addObject("msg", "Login画面"); // 表示メッセージ
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("UserId") String id,@RequestParam("UserPass") String pass, ModelAndView mav) {
		mav.setViewName("Main");
		mav.addObject("msg", "メニュー画面"); // 表示メッセージ
		mav.addObject("id", id); // 入力テキストに入力値を表示
		mav.addObject("pass", pass); // 入力テキストに入力値を表示
		return mav;
	}
	
	@RequestMapping(value = "/Serch", method = RequestMethod.POST)
	public ModelAndView serchControl(ModelAndView mav) {
		mav.setViewName("Serch");
		return mav;
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public ModelAndView registerControl(ModelAndView mav) {
		mav.setViewName("Register");
		return mav;
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public ModelAndView deleteControl(ModelAndView mav) {
		mav.setViewName("Delete");
		return mav;
	}
}
