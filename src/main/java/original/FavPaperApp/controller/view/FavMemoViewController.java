package original.FavPaperApp.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import original.FavPaperApp.mapper.data.FavMemoView;
import original.FavPaperApp.service.view.FavMemoViewService;
import original.FavPaperApp.service.UserService;

import java.util.List;

/*HTMLページを返すエンドポイント専用のコントローラーです。
Springの@Controllerを使用。*/

@Controller
public class FavMemoViewController {

    private final FavMemoViewService service;
    private final UserService userService; // UserServiceを注入

    public FavMemoViewController(FavMemoViewService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/fav_memo")
    public String favMemoList(Model model) {
        // ログインユーザーの情報を取得
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        String userName = userService.searchUser(email).getUserName();
        model.addAttribute("userName", userName);

        // ログインユーザーのファブメモの一覧をモデルに追加
        int userId = userService.searchUser(email).getUserId();
        List<FavMemoView> favMemoList = service.searchFavMemoByUser(userId);
        model.addAttribute("favMemos", favMemoList);

        return "favMemos";
    }


}
