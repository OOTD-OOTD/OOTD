package com.ootd.ootdApp.myPage.senondHand.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ootd.ootdApp.member.model.vo.Member;
import com.ootd.ootdApp.myPage.senondHand.model.service.SecondHandService;

@SessionAttributes({"member"})
@Controller
public class SecondHandConrtoller {
	
	//Order_Detail 모달 (주문번호클릭시)
	//Info 회원정보
	//Product 상품목록 (중고상품 올린 페이지)
	//Purchased 주문상품(구매완료)
	//Sale 판매한 상품
	//Review Sale페이지에서 구매한 상품 리뷰 작성하기
	
	@Autowired
	SecondHandService secondHandService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@RequestMapping("/myPage/myPage_Info.mp")
	public String myPage_Info() {
		return "myPage/myPage_Info";
	}
	
	@RequestMapping("/myPage/myPage_Purchased.mp")
	public String myPagePurchased() {
		return "myPage/myPage_Purchased";
	}
	
	@RequestMapping("/myPage/myPage_Product.mp")
	public String myPageProduct() {
		return "myPage/myPage_Product";
	}
	
	@RequestMapping("/myPage/myPage_Sale.mp")
	public String myPageSale() {
		return "myPage/myPage_Sale";
	}
	
	@RequestMapping("/myPage/testMapping")
	public String test(HttpSession session) {
		
		Member member = new Member();
		member.setMember_id("user01");
		member.setMember_pw(bcryptPasswordEncoder.encode("pwd01"));
		member.setEmail("user01@naver.com");
		member.setPhone("010-1111-1111");
		
		session.setAttribute("member", member);
		
		return "redirect:/";
	}
	
	@RequestMapping("/myPage/update_Password.do")
	public String update_Password(@RequestParam(value="password") String password,
							@RequestParam(value="new_password") String new_password,
							@RequestParam(value="confirm_password") String confirm_password,
							Member member,
							Model model) {
		
		System.out.println("로그인 된 회원 / 비밀번호 : " + member.getMember_id() + " / " + member.getMember_pw());
		
		if (!new_password.equals(confirm_password))	{
			System.out.println("new password not equals");
			return "redirect:myPage_Info.mp";
		}
		if (!bcryptPasswordEncoder.matches(password, member.getMember_pw())) {
			System.out.println("not equals current password");
			return "redirect:myPage_Info.mp";
		}
		
		member.setMember_pw(bcryptPasswordEncoder.encode(new_password));
		
		int result = secondHandService.updatePassword(member);
		
		if (result > 0) {
			model.addAttribute("member", member);
			System.out.println("비밀번호 변경 완료 -> " + member.getMember_pw());
		}
		
		return "redirect:myPage_Info.mp";
	}
	
	@RequestMapping("/myPage/update_Email.do")
	public String update_Email(@RequestParam(value="update_email") String update_email, Member member, Model model) {
		
		System.out.println("current email -> " + member.getEmail());

		if (update_email == null) {
			System.out.println("Email Null");
			return "redirect:myPage_Info.mp";
		}
		
		if (member.getEmail().equals(update_email)) {
			System.out.println("same email");
			return "redirect:myPage_Info.mp";
		}
		
		member.setEmail(update_email);
		
		int result = secondHandService.updateEamil(member);
		
		if (result > 0) {
			model.addAttribute("member", member);
			System.out.println("change email -> " + member.getEmail());
		}
		
		return "redirect:myPage_Info.mp";
	}
	
	@RequestMapping("/myPage/update_Phone.do")
	public String update_Phone(@RequestParam(value="update_phone") String update_phone, Member member, Model model) {
		
		System.out.println("변경할 핸드폰 번호 :: " + member.getPhone());

		if (update_phone == null) { 
			System.out.println("Phone number Null");
			return "redirect:myPage_Info.mp";
		}
		
		if (member.getPhone().equals(update_phone)) {
			System.out.println("same Phone number");
			return "redirect:myPage_Info.mp";
		}
		
		
		member.setPhone(update_phone);
		
		int result = secondHandService.updatePhone(member);
		
		if (result > 0) {
			model.addAttribute(member);
			System.out.println("change Phone number -> " + member.getPhone());
		}
		
		return "redirect:myPage_Info.mp";
	}
	
	@RequestMapping("/myPage/updateAddress.do")
	public String updateAddress(@RequestParam(value="update_address") String address, @RequestParam(value="update_detail_address") String detailAddress) {
		
		System.out.println(address + " / " + detailAddress);
		
		return "redirect:myPage_Info.mp";
	}
	
	@RequestMapping("/myPage/updateBank.do")
	public String updateBank() {
		
		
		
		return "redirect:myPage_Info.mp";
	}
}