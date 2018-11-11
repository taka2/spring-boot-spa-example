package springbootspaexample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 許可ロールの設定
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // SpringSecurity認証 - ユーザID/パスワード認証を設定する - パスワードはpassword固定
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        username, password, authorities);

        // External Authentication
        if(username != null && password != null && username.equals(password)) {
        	// OK
        } else {
        	return "redirect:/";
        }

        // SpringSecurityコンテキストを生成し、SpringSecurityの認証情報を格納する
        SecurityContextImpl context = new SecurityContextImpl();
        context.setAuthentication(authentication);

        // SpringSecurityコンテキストをコンテキストホルダーへ格納し、認証連携を完了する。
        // この処理にて、SpringSecurityの設定(=HTTP用認証)を完了する。
        SecurityContextHolder.setContext(context);

		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}
}
