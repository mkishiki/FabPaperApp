package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaperViewDTO {
    private int paperId;
    private String paperName;
    private String description;
    private String typeName;
    private String tagNames;  // GROUP_CONCATで複数のタグをカンマ区切りでまとめたもの
    private Integer userFav;  // お気に入り登録状態（favの値）

}
