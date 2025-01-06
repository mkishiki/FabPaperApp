package original.FavPaperApp.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import original.FavPaperApp.mapper.data.PaperViewDTO;
import original.FavPaperApp.service.PaperViewDTOService;

import java.util.List;

/*HTMLページを返すエンドポイント専用のコントローラーです。
Springの@Controllerを使用。*/

@Controller
public class PaperViewController {

    private final PaperViewDTOService service;

    public PaperViewController(PaperViewDTOService service) {
        this.service = service;
    }

    // 紙の一覧表示 (HTMLを返す)
    @GetMapping(value = "/paper/list")
    public String paperList(Model model) {
        List<PaperViewDTO> paperList = service.getPaper();
        model.addAttribute("papers", paperList);
        return "papers"; // papers.htmlを返す
    }
}
