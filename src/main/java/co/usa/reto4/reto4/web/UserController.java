package co.usa.reto4.reto4.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal){
        return Collections.singletonMap("name", principal.getAttribute("name"));

    }
    @GetMapping("/avatar_url")
    public Map<String, Object> avatar(@AuthenticationPrincipal OAuth2User principal){
        return Collections.singletonMap("avatar_url", principal.getAttribute("avatar_url"));
    }

}
