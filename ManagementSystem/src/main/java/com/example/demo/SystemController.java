package com.example.demo;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import common.ConstGlobal;
import form.LoginForm;
import form.RegisterForm;
import logic.LoginLogic;
import logic.RegisterLogic;

/**
 * 画面遷移時のトリガーとなるクラス
 */
@Controller
public class SystemController {
	/**
	 * 画面遷移メソッド
	 * @param mav　遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(LoginForm form,ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrLogin);
		mav.addObject("msg", ConstGlobal.gstrLoginScreen); // 表示メッセージ
		mav.addObject("form", new LoginForm());
		
		return mav;
	}

	/**
	 * 画面遷移メソッド
	 * @param id　入力値ユーザーID
	 * @param pass　入力値ユーザーPass
	 * @param mav 画面遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/Menu", method = RequestMethod.POST)
	public ModelAndView send(@ModelAttribute("form") @Valid LoginForm form,BindingResult bindingResult, ModelAndView mav) {
		ArrayList<String> list = new ArrayList<String>();
		
	    for (ObjectError error : bindingResult.getAllErrors()) {
	        System.out.println(error.getDefaultMessage());
			mav.setViewName(ConstGlobal.gstrLogin);
			mav.addObject("msg", ConstGlobal.gstrLoginScreen); // 表示メッセージ
			mav.addObject("id", form.getId()); // 入力テキストに入力値を表示
			mav.addObject("pass", form.getPass()); // 入力テキストに入力値を表示
			return mav;
	    }
		// ログイン時の判定処理
		list = LoginLogic.DetermineLogin(form.getId(),form.getPass());
		if("true".equals(list.get(ConstGlobal.two))) {
			mav.addObject("flag", true); // 入力テキストに入力値を表示
			form.setflag(true); // 入力テキストに入力値を表示
		} else {
			mav.addObject("flag", false); // 入力テキストに入力値を表示
			form.setflag(false); // 入力テキストに入力値を表示
		}

		
		mav.setViewName(list.get(ConstGlobal.zero));
		mav.addObject("msg", list.get(ConstGlobal.one)); // 表示メッセージ
		
		return mav;
	}

	/**
	 * 検索画面遷移メソッド
	 * @param mav　遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/Serch", method = RequestMethod.POST)
	public ModelAndView serchControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrSerch);
		mav.addObject("msg", ConstGlobal.gstrSerchScreen); // 表示メッセージ
		return mav;
	}
	
	/**
	 * 登録画面遷移メソッド
	 * @param mav　遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public ModelAndView registerControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrRegister);
		mav.addObject("msg", ConstGlobal.gstrRegisterScreen); // 表示メッセージ
		mav.addObject("registerForm", new RegisterForm());
		return mav;
	}
	
	/**
	 * 削除画面遷移メソッド
	 * @param mav　遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public ModelAndView deleteControl(ModelAndView mav) {
		mav.setViewName(ConstGlobal.gstrDelete);
		mav.addObject("msg", ConstGlobal.gstrDeleteScreen); // 表示メッセージ
		return mav;
	}
	
	/**
	 * 登録完了画面遷移メソッド
	 * @param mav　遷移情報
	 * @return ModelAndView 遷移情報
	 */
	@RequestMapping(value = "/Completion", method = RequestMethod.POST)
	public ModelAndView registerCompletControl(@ModelAttribute("form") @Valid RegisterForm form,BindingResult bindingResult, ModelAndView mav) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add(form.getCustomerName());
		list.add(form.getCustomerNameKana());
		list.add(form.getCustomerNo());
		list.add(form.getPostalCode());
		list.add(form.getStreetAddress());
		list.add(form.getStreetAddressKana());
		
		// 登録処理開始
		RegisterLogic.DetermineRegister(list);
		
		mav.setViewName(ConstGlobal.gstrRegisterComplet);
		mav.addObject("msg", ConstGlobal.gstrRegisterCompletScreen); // 表示メッセージ
		return mav;
	}
	
}
