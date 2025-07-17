package Day9Task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String name;
    private Integer age;
    private Details details;

    @Getter
    @Setter
    public static class Details {
        private String detail1;
        private Integer id;
    }
}
