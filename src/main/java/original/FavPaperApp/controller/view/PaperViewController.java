package original.FavPaperApp.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import original.FavPaperApp.mapper.data.PaperViewDTO;
import original.FavPaperApp.service.PaperViewDTOService;
import original.FavPaperApp.service.UserService;

import java.util.List;

/*HTMLページを返すエンドポイント専用のコントローラーです。
Springの@Controllerを使用。*/

@Controller
public class PaperViewController {

    private final PaperViewDTOService service;
    private final UserService userService; // UserServiceを注入

    public PaperViewController(PaperViewDTOService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/paper/list")
    public String paperList(Model model) {

        // ログインユーザーの情報を取得
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // ログインしている場合、ユーザー名をモデルに追加
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String email = userDetails.getUsername();

            String userName = userService.searchUser(email).getUserName();

            model.addAttribute("userName", userName);
        }

        // 紙の一覧をモデルに追加
        List<PaperViewDTO> paperList = service.showPaperAll();
        model.addAttribute("papers", paperList);

        return "papers";
    }


}
