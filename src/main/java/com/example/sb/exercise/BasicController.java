package com.example.sb.exercise;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ex")
public class BasicController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	// Get => GetMapping, Post => PostMapping

	@GetMapping("/hello") // localhost:8090/sb/ex/hello
	public String hello() {
		return "exercise/hello"; // hello.html
		// viewer 파일을 렌더링 하는 형태

	}

	@ResponseBody // HTML파일을 찾지 말고, 데이터를 직접 전송
	@GetMapping("/noHtml")
	public String noHtml() {
		return "<h1>Hello Spring Boot!!!</h1>";
		// 데이터를 직접 보내는 경우
	}

	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/ex/hello"; // Redirection = hello 로 보냄
		// 리드렉션을 하는 경우
	}

	// 파라메터 전달 방법
	@GetMapping("/params")
	public String params(Model model) {
		model.addAttribute("name", "제임스");
		// name 이라는 이름을 제임스로 주겠다.
		return "exercise/params";
	}

	// 파라메터 받는 방법1
	@GetMapping("/params2")
	public String params2(Model model, HttpServletRequest req) {
		String name = req.getParameter("name");
		model.addAttribute("name", name);
		return "exercise/params";
		// 파일을 업로드할 경우 이 코드를 지향
	}

	// 파라메터 받는 방법2
	@GetMapping("/params3")
	public String params3(Model model, String name, int count) {
		// 1. 메서드에서 파라메터를 설정하면 스프링이 자동으로 설정해 줌
		model.addAttribute("name", name + count);
		// 2. int 또한 자동으로 형성
		return "exercise/params";
	}

	@GetMapping("/memberForm")
	public String memberForm() {
		return "exercise/memberForm";
	}

	@PostMapping("/memberProc")
	// 객체 또한 자동으로 형성
	// 1. 필드명은 같아야 한다
	public String memberProc(Member member, Model model) {
		log.info(member.toString());
		model.addAttribute("name", member.getName());
		return "exercise/params";

	}

	@GetMapping("/login")
	public String login() {
		return "exercise/login";
	}

	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model) {
		// bbs user 3.제임스
		String hashedPwd = "$2a$10$uVHxpYdw4juNNa6Kem6NtOUmEMAPfWCpSNF4dPARSm.gx8l.4WkUy";
		if (uid.equals("james") && BCrypt.checkpw(pwd, hashedPwd)) {
			model.addAttribute("msg", uid + "님이 로그인했습니다.");
			session.setAttribute("sessUid", uid);
			session.setAttribute("sessUname", "제임스");
			return "exercise/loginResult";
		} else {
			model.addAttribute("msg", "uid, 비밀번호를 입력하세요.");
			return "exercise/loginResult";
		}
	}

	@GetMapping(value={"/path/{uid}/{bid}", "/path/{uid}"})
	// 1. 웹 주소에서 입력값을 받은 걸 그대로 보여주고 싶을 때
	// 2. value 추가 = 앞에 경우와 뒤에 경우도 받겠다.
	@ResponseBody
	public String path(@PathVariable String uid, @PathVariable (required = false) Integer bid) {
		//2. required 추가
		bid = (bid == null) ? 0 : bid;
		return "<h1>uid=" + uid + ", bid=" + bid + "</h1>";

	}

}
