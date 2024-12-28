package original.FavPaperApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import original.FavPaperApp.mapper.data.FavMemo;

import java.util.List;

@RestController
public class LoginController {

    //ファブメモの一覧表示
    @GetMapping(value = "/")
    @ResponseBody
    public String index() {
        return "Welcome to Fav Paper App";
    }

}
