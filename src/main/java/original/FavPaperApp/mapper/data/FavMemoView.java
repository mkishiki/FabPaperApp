package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class FavMemoView {
    private int userId;
    private int paperId;
    private String paperName;
    private String description;
    private int typeId;
    private String typeName;
    private int fav;
    private String memo;
    private LocalDateTime registeredAt;

}
