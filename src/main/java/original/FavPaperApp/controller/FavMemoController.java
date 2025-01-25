package original.FavPaperApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.FavMemo;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.service.DuplicateException;
import original.FavPaperApp.service.FavMemoService;
import original.FavPaperApp.service.PaperService;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class FavMemoController {

    private final FavMemoService service;

    public FavMemoController(FavMemoService service) {
        this.service = service;
    }


    //ファブメモの一覧表示
    @GetMapping(value = "/fav_memo/list")
    public List<FavMemo> favMemoList() {
        return service.showFavMemo();
    }
//
//    //user_id,paper_idで検索して表示
//    @GetMapping(value = "/fav_memo")
//    public FavMemo favMemo(@RequestParam int userId, int paperId) {
//        return service.searchFavMemo(userId, paperId);
//    }
//
//    //ファブメモの登録
//    @PostMapping(value = "/fav_memo")
//    public ResponseEntity<FavMemo> registerFavMemo(@RequestBody FavMemo favMemo) throws Exception{
//        favMemo.setRegisteredAt(LocalDateTime.now());
//        FavMemo registerFavMemo = service.registerFavMemo(favMemo);
//        return new ResponseEntity<>(registerFavMemo, HttpStatus.OK);
//    }

    //ファブメモの上書き
    @PostMapping(value = "/fav_memo/edit")
    public ResponseEntity<FavMemo> updateFavMemo(@RequestBody FavMemo favMemo) {
        FavMemo updatedFavMemo = service.updateFavMemo(favMemo);
        return new ResponseEntity<>(updatedFavMemo, HttpStatus.OK);
    }

    //ファブメモの削除
    @DeleteMapping(value = "/fav_memo/edit")
    public ResponseEntity<String> deleteFavMemo(@RequestParam int userId, int paperId) throws Exception {
        service.deleteFavMemo(userId, paperId); // 削除処理を実行
        return new ResponseEntity<>("userId:" + userId + ", paperId:" + paperId + "のファブメモを削除しました", HttpStatus.OK); // 成功した場合のみ "OK" を返す
    }


    //例外処理
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateFavMemo(
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
