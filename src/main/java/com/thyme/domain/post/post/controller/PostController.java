package com.thyme.domain.post.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/posts")
@Controller
public class PostController {

	// 서버로부터 정보를 요청하기 위한 메서드로 GET을 사용한다
	@GetMapping("/write")
	@ResponseBody
	public String showWrite() {
		return """
			<form action="/posts/doWrite" method="post">
				<input type="text" name="title" placeholder="제목" />
				<textarea name="content"></textarea>
				<input type="submit" value="등록" />
			</form>
			""";
		// form: 사용자가 입력한 데이터를 서버로 보내준다
		// form action: 응답을 어디로 보낼지 지정할 수 있다 '/' 하나로 앞주소가 대체된다
		// method="post"로 데이터 제출의 HTTP 메서드를 POST로 설정할 수 있다
	}

	// 서버로 데이터를 제출하기 위한 메서드로 POST를 사용한다
	@PostMapping("/doWrite")
	@ResponseBody
	public String doWrite(String title, String content) { // 파라미터의 이름이 같으면 그대로 사용할 수 있다
		return """
			<h1>게시물 조회</h1>
			<div>%s</div>
			<div>%s</div>
			""".formatted(title, content);
	}
}
