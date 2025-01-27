package original.FavPaperApp.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import original.FavPaperApp.mapper.data.PaperView;
import original.FavPaperApp.service.PaperViewService;
import original.FavPaperApp.service.UserService;
import java.util.List;

@Controller
public class PaperViewController {

    private final PaperViewService service;
    private final UserService userService; // UserServiceを注入

    public PaperViewController(PaperViewService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/paper/list")
    public String paperList(Model model) {
        model.addAttribute("userName", getLoggedInUserName());
        model.addAttribute("papers", service.showPaperAll());
        return "papers";
    }

    @GetMapping(value = "/paper/search")
    public String searchPapers(
            @RequestParam(required = false) String paperName,
            @RequestParam(required = false) String typeName,
            @RequestParam(required = false) String tagName,
            Model model) {

        model.addAttribute("userName", getLoggedInUserName());
        List<PaperView> paperList = service.searchPapers(paperName, typeName, tagName);

        // 検索結果に応じたメッセージを設定
        if (paperList.isEmpty()) {
            model.addAttribute("message", "一致する結果がありません。");
        } else {
            model.addAttribute("message", paperList.size() + "件の検索結果があります。");
        }

        model.addAttribute("papers", paperList);

        return "papers";
    }

    /*
    ログインしているユーザー名を表示します。
    */
    private String getLoggedInUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userService.searchUser(email).getUserName();
    }

}
