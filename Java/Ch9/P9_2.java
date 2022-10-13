package Ch9;

import static java.lang.System.out;

public class P9_2 {
    public static void main(String[] args) {
        Stock s = new Stock("ORCL", "Oracle Corporation");
        s.previousClosingPrice = 34.5;
        s.currentPrice = 34.35;
        out.print("The percentage changed is " + s.getChangePercent()+"%");
    }
}

class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    double getChangePercent() {
        return (currentPrice - previousClosingPrice) * 100.0 / previousClosingPrice;
    }
}