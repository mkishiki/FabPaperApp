package original.FavPaperApp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import original.FavPaperApp.mapper.data.Paper;
import original.FavPaperApp.mapper.data.User;
import original.FavPaperApp.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    //ユーザーの一覧表示
    @GetMapping(value = "/user/list")
    public List<User> userList() {
        return service.showUser();
    }

    //e-mailで検索して表示
    @GetMapping(value = "/user")
    public User user(@RequestParam String email) {
        return service.searchUser(email);
    }

    //ユーザーの登録
    @PostMapping(value = "/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception{
        User registerUser = service.registerUser(user);
        return new ResponseEntity<>(registerUser, HttpStatus.OK);
    }

    //ユーザーの削除
    @DeleteMapping(value = "/user/edit")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) throws Exception {
        service.deleteUser(userId); // 削除処理を実行
        return new ResponseEntity<>("削除しました", HttpStatus.OK); // 成功した場合のみ "OK" を返す
    }

}
