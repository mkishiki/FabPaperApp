package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Paper {
    private int paperId;
    private String paperName;
    private int typeId;
    private String description;

    //ログ出力用
    @Override
    public String toString() {
        return "Paper{" +
                "paperId=" + paperId +
                ", paperName='" + paperName + '\'' +
                ", typeId=" + typeId +
                ", description='" + description + '\'' +
                '}';
    }
}
