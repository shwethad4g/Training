package WeekendTaskJuly20;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    private String symbol;
    private String name;
    private double price;

}