package original.FavPaperApp.controller.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import original.FavPaperApp.mapper.data.PaperView;
import original.FavPaperApp.mapper.data.FavMemo; // 追加
import original.FavPaperApp.mapper.data.Tag;
import original.FavPaperApp.service.TagService;
import original.FavPaperApp.service.view.PaperViewService;
import original.FavPaperApp.service.UserService;
import original.FavPaperApp.service.FavMemoService; // 追加
import java.util.List;

@Controller
public class PaperViewController {

    private final PaperViewService service;
    private final UserService userService;
    private final FavMemoService favMemoService; // 追加（お気に入りサービス）
    private final TagService tagService;

    public PaperViewController(PaperViewService service, UserService userService, FavMemoService favMemoService, TagService tagService) { // 修正
        this.service = service;
        this.userService = userService;
        this.favMemoService = favMemoService; // 追加
        this.tagService = tagService;
    }

    @GetMapping(value = "/paper/list")
    public String paperList(Model model) {
        model.addAttribute("userName", getLoggedInUserName());

        // ログインユーザーの userId を取得
        String email = getLoggedInUserEmail();
        int userId = userService.searchUser(email).getUserId();

        List<PaperView> papers = service.showPaperAll();

        // ユーザーのお気に入り登録済みの paperId リストを取得
        List<Integer> favoritePaperIds = favMemoService.getUserFavoritePaperIds(userId);

        // 各 Paper に isFavorite をセット
        for (PaperView paper : papers) {
            paper.setFavorite(favoritePaperIds.contains(paper.getPaperId()));
        }

        model.addAttribute("papers", papers);

        // 検索画面用のタグ一覧を取得
        List<Tag> allTags = tagService.showTag();
        model.addAttribute("allTags", allTags);

        return "papers";
    }

    @GetMapping(value = "/paper/search")
    public String searchPapers(
            @RequestParam(required = false) String paperName,
            @RequestParam(required = false) String typeName,
            @RequestParam(required = false) List<String> tagNames,
            Model model) {

        model.addAttribute("userName", getLoggedInUserName());

        List<PaperView> paperList = service.searchPapers(paperName, typeName, tagNames);

        if (paperList.isEmpty()) {
            model.addAttribute("message", "一致する結果がありません。");
        } else {
            model.addAttribute("message", paperList.size() + "件の検索結果があります。");
        }

        model.addAttribute("papers", paperList);   // 検索結果を渡す
        model.addAttribute("allTags", tagService.showTag()); // タグ一覧も再表示

        return "papers";
    }


    // お気に入りのトグル処理
    @PostMapping(value = "/paper/toggleFavorite")
    @ResponseBody
    public String toggleFavorite(@RequestParam int paperId) throws Exception {
        String email = getLoggedInUserEmail();
        int userId = userService.searchUser(email).getUserId();

        // 既に登録されているかチェック
        FavMemo existingFav = favMemoService.searchFavMemo(userId, paperId);
        if (existingFav != null) {
            favMemoService.deleteFavMemo(userId, paperId);  // 既存なら削除
            return "DELETED";
        } else {
            FavMemo favMemo = new FavMemo();
            favMemo.setUserId(userId);
            favMemo.setPaperId(paperId);
            favMemoService.registerFavMemo(favMemo);  // 新規登録
            return "ADDED";
        }
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
