package original.FavPaperApp.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/logout-success")
    public String logoutSuccessPage() {
        return "logout-success"; // templates/logout-success.html を返す
    }

}
