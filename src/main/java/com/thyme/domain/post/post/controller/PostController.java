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
			<form method="post">
				<input type="text" name="title" placeholder="제목" />
				<textarea name="content"></textarea>
				<input type="submit" value="등록" />
			</form>
			""";
		// form: 사용자가 입력한 데이터를 서버로 보내준다
		// form action: 응답을 어디로 보낼지 지정할 수 있다 '/' 하나로 앞주소가 대체된다
		// form method="post"로 데이터 제출의 HTTP 메서드를 POST로 설정할 수 있다

		// action에 따로 값을 정하지 않으면 입력 데이터를 GET 파라미터로 만들어 요청을 보낸다
		// 글쓰기 과정: 글쓰기 작성 Form -> 입력 완료 -> 처리 : Form을 보여주는 것과 데이터를 처리하는 부분은 서로 필요하므로 같은 엔드포인트가 이상적이다
		// GET /posts/write: 폼 보여주기, GET /posts/write: 처리 이렇게는 구분되지 않아 사용할 수 없다
		// GET /posts/write: 폼 보여주기, POST /posts/write: 동일한 주소라도 HTTP 메서드가 다르면 가능하다
	}

	// 서버로 데이터를 제출하기 위한 메서드로 POST를 사용한다
	@PostMapping("/write")
	@ResponseBody
	public String doWrite(String title, String content) { // 파라미터의 이름이 같으면 그대로 사용할 수 있다
		if (title.isBlank() || title == null) {
			return "제목을 입력해주세요";
		}

		if (content.isBlank() || title == null) {
			return "내용을 입력해주세요";
		}

		return """
			<h1>게시물 조회</h1>
			<div>%s</div>
			<div>%s</div>
			""".formatted(title, content);
	}
}
