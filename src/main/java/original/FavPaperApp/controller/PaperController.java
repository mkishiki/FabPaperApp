package original.FavPaperApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.service.PaperService;

import java.util.List;

@RestController
public class PaperController {

    private final PaperService service;

    public PaperController(PaperService service) {
        this.service = service;
    }

    // 紙名で検索して表示
    @GetMapping(value = "/paper")
    public Paper paper(@RequestParam String paperName) {
        return service.searchPaperName(paperName);
    }

    // 紙の登録
    @PostMapping(value = "/paper")
    public ResponseEntity<Paper> registerPaper(@RequestBody Paper paper) throws Exception {
        Paper registerPaper = service.registerPaper(paper);
        return new ResponseEntity<>(registerPaper, HttpStatus.OK);
    }

    // 紙の上書き
    @PostMapping(value = "/paper/edit")
    public ResponseEntity<Paper> updatePaper(@RequestBody Paper paper) {
        Paper updatedPaper = service.updatePaper(paper);
        return new ResponseEntity<>(updatedPaper, HttpStatus.OK);
    }

    // 紙の削除
    @DeleteMapping(value = "/paper/edit")
    public ResponseEntity<String> deletePaper(@RequestParam int paperId) throws Exception {
        service.deletePaper(paperId);
        return new ResponseEntity<>("paperId：" + paperId + "を削除しました", HttpStatus.OK);
    }
}
