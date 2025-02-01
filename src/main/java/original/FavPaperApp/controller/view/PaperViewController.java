package original.FavPaperApp.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import original.FavPaperApp.mapper.data.PaperView;
import original.FavPaperApp.mapper.data.FavMemo; // 追加
import original.FavPaperApp.service.view.PaperViewService;
import original.FavPaperApp.service.UserService;
import original.FavPaperApp.service.FavMemoService; // 追加
import java.util.List;

@Controller
public class PaperViewController {

    private final PaperViewService service;
    private final UserService userService;
    private final FavMemoService favMemoService; // 追加（お気に入りサービス）

    public PaperViewController(PaperViewService service, UserService userService, FavMemoService favMemoService) { // 修正
        this.service = service;
        this.userService = userService;
        this.favMemoService = favMemoService; // 追加
    }

    @GetMapping(value = "/paper/list")
    public String paperList(Model model) {
        model.addAttribute("userName", getLoggedInUserName());

        // 追加: ログインユーザーの userId を取得
        String email = getLoggedInUserEmail(); // 追加
        int userId = userService.searchUser(email).getUserId(); // 追加

        List<PaperView> papers = service.showPaperAll();

        // 追加: ユーザーのお気に入り登録済みの paperId リストを取得
        List<Integer> favoritePaperIds = favMemoService.getUserFavoritePaperIds(userId);

        // 追加: 各 Paper に isFavorite をセット
        for (PaperView paper : papers) {
            paper.setFavorite(favoritePaperIds.contains(paper.getPaperId()));
        }

        model.addAttribute("papers", papers);
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

        if (paperList.isEmpty()) {
            model.addAttribute("message", "一致する結果がありません。");
        } else {
            model.addAttribute("message", paperList.size() + "件の検索結果があります。");
        }

        // 追加: 検索結果にもお気に入り情報を付与
        String email = getLoggedInUserEmail();
        int userId = userService.searchUser(email).getUserId();
        List<Integer> favoritePaperIds = favMemoService.getUserFavoritePaperIds(userId);
        for (PaperView paper : paperList) {
            paper.setFavorite(favoritePaperIds.contains(paper.getPaperId()));
        }

        model.addAttribute("papers", paperList);
        return "papers";
    }

    // 追加: お気に入りのトグル処理
    @PostMapping(value = "/paper/toggleFavorite")
    public String toggleFavorite(@RequestParam int paperId) throws Exception {
        String email = getLoggedInUserEmail();
        int userId = userService.searchUser(email).getUserId();

        FavMemo existingFav = favMemoService.searchFavMemo(userId, paperId);
        if (existingFav != null) {
            favMemoService.deleteFavMemo(userId, paperId);
        } else {
            FavMemo favMemo = new FavMemo();
            favMemo.setUserId(userId);
            favMemo.setPaperId(paperId);
            favMemoService.registerFavMemo(favMemo);
        }

        return "redirect:/paper/list";
    }

    /*
    ログインしているユーザー名を表示します。
    */
    private String getLoggedInUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userService.searchUser(email).getUserName();
    }

    // 追加: ログイン中のユーザーの email を取得するメソッド
    private String getLoggedInUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
