package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.ConstGlobal;
import logic.LoginLogic;

@Controller
public class SystemController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrLogin);
		mav.addObject("msg", ConstGlobal.gstrLoginScreen); // 表示メッセージ
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("UserId") String id,@RequestParam("UserPass") String pass, ModelAndView mav) {
		ArrayList<String> list = new ArrayList<String>();
		
		// ログイン時の判定処理
		list = LoginLogic.DetermineLogin(id,pass);
		
		mav.setViewName(list.get(ConstGlobal.zero));
		mav.addObject("msg", list.get(ConstGlobal.one)); // 表示メッセージ
		
		mav.addObject("id", id); // 入力テキストに入力値を表示
		mav.addObject("pass", pass); // 入力テキストに入力値を表示
		return mav;
	}

	@RequestMapping(value = "/Serch", method = RequestMethod.POST)
	public ModelAndView serchControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrSerch);
		mav.addObject("msg", ConstGlobal.gstrSerchScreen); // 表示メッセージ
		return mav;
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public ModelAndView registerControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrRegister);
		mav.addObject("msg", ConstGlobal.gstrRegisterScreen); // 表示メッセージ
		return mav;
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public ModelAndView deleteControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrDelete);
		mav.addObject("msg", ConstGlobal.gstrDeleteScreen); // 表示メッセージ
		return mav;
	}
}
