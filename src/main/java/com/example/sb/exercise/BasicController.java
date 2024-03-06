package com.example.sb.exercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ex")
public class BasicController {
	//Get => GetMapping, Post => PostMapping 
	
	@GetMapping("/hello")							//localhost:8090/sb/ex/hello
	public String hello() {
		return "exercise/hello";				//hello.html
		//viewer 파일을 렌더링 하는 형태

	}
	
	@ResponseBody			//HTML파일을 찾지 말고, 데이터를 직접 전송
	@GetMapping("/noHtml")
	public String noHtml() {
		return "<h1>Hello Spring Boot!!!</h1>"; 		
		//데이터를 직접 보내는 경우
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/ex/hello";		//Redirection = hello 로 보냄
		//리드렉션을 하는 경우
	}	
	
	//파라메터 전달 방법
	@GetMapping("/params")
	public String params(Model model) {
		model.addAttribute("name", "제임스");
		//name 이라는 이름을 제임스로 주겠다.
		return "exercise/params";
	}
	
	
}
