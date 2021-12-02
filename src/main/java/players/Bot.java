package players;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Bot extends Player {
    public Bot(int coordinateX, int coordinateY, int money) {
        super(coordinateX, coordinateY, money);
    }
    /**
     * Метод возвращает тип игрока.
     * @return тип - бот.
     */
    @Override
    public String getType() {
        return "bot '\uD83E\uDD16'";
    }

    /**
     * Метод возвращает информацию о игроке.
     * @return Возвращает информацию о местоположении и балансе игрока.
     */
    @Override
    public String toString() {
        return "\nBot right here at the moment: (" + getCoordinateY() + "):(" + getCoordinateX() + ")." +
                "\nBot's current balance is: " + getMoney() + "$.";
    }
}