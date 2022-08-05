package hello.selfmadeboard.controller.form;

import lombok.*;

@Getter
@Builder
public class BoardSearchForm {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public long getOffset(){
        return (long) (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }

//    @Builder
//    public BoardSearchForm(Integer page, Integer size) {
//        this.page = page == null ? 1 : page ;
//        this.size = size == null ? 20 : size;
//    }
}
