package r.real.common.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import r.real.users.TokenUtil;

import java.util.stream.Collectors;

@Controller
public class HomeController {

    @ModelAttribute("roles")
    public String roles(Authentication auth) {
        if (auth == null) return "";
        return auth.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/")
    public String home() { return "home"; }

    @GetMapping("/home")
    public String base(Model model, Authentication auth) {
        if (auth instanceof OAuth2AuthenticationToken) {
            return "redirect:/home-openid";
        }
        model.addAttribute("name", auth.getName());
        return "home";
    }

    @GetMapping("/home-openid")
    public String homeOpenId(Model model,
                             OAuth2AuthenticationToken token,
                             @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        OidcUser principal = (OidcUser) token.getPrincipal();
        model.addAttribute("name", principal.getFullName());
        model.addAttribute("email", principal.getEmail());
        model.addAttribute("accessToken", TokenUtil.extractToken(client.getAccessToken().getTokenValue()));
        model.addAttribute("refreshToken", TokenUtil.extractToken(client.getRefreshToken().getTokenValue()));
        model.addAttribute("idToken", TokenUtil.extractToken(principal.getIdToken().getTokenValue()));
        return "home";
    }
}