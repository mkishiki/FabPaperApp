package original.FavPaperApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.PaperWithTagDTO;
import original.FavPaperApp.service.DuplicateException;
import original.FavPaperApp.service.NotFoundException;
import original.FavPaperApp.service.PaperService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class PaperController {

    private final PaperService service;

    public PaperController(PaperService service) {
        this.service = service;
    }

    //紙の一覧表示
    @GetMapping(value = "/paper/list")
    public List<Paper> paperList() {
        return service.showPaper();
    }

    //紙名で検索して表示
    @GetMapping(value = "/paper")
    public Paper paper(@RequestParam String paperName) {
        return service.searchPaperName(paperName);
    }

//
//    //タグで検索して表示
//    @GetMapping("/paper/by_tag")
//    public ResponseEntity<List<Paper>> getPapersByTagId(@RequestParam int tagId) {
//        List<PaperWithTagDTO> papers = service.getPapersByTagId(tagId);
//        if (papers.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(papers, HttpStatus.OK);
//    }


    //紙の登録
    @PostMapping(value = "/paper")
    public ResponseEntity<Paper> registerPaper(@RequestBody Paper paper) throws Exception{
        Paper registerPaper = service.registerPaper(paper);
        return new ResponseEntity<>(registerPaper, HttpStatus.OK);
    }

    //紙の上書き
    @PostMapping(value = "/paper/edit")
    public ResponseEntity<Paper> updatePaper(@RequestBody Paper paper) {
        Paper updatedPaper = service.updatePaper(paper);
        return new ResponseEntity<>(updatedPaper, HttpStatus.OK);
    }

    //紙の削除
    @DeleteMapping(value = "/paper/edit")
    public ResponseEntity<String> deletePaper(@RequestParam int paperId) throws Exception {
        service.deletePaper(paperId); // 削除処理を実行
        return new ResponseEntity<>("paperId：" + paperId + "を削除しました", HttpStatus.OK); // 成功した場合のみ "OK" を返す
    }



    //例外処理
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Map<String, String>> handleDuplicatePaper(
            DuplicateException e, HttpServletRequest request) { //例外の情報e（今回はエラー文言）と、呼び出しもとからのリクエストを引数
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
