package players;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Human extends Player {
    public Human(int coordinateX, int coordinateY, int money) {
        super(coordinateX, coordinateY, money);
    }
    /**
     * Метод возвращает тип игрока.
     * @return тип - человек.
     */
    @Override
    public String getType() {
        return "human 'ℙ'";
    }

    /**
     * Метод возвращает информацию о игроке.
     * @return Возвращает информацию о местоположении и балансе игрока.
     */
    @Override
    public String toString() {
        return "\nYou are here at the moment: (" + getCoordinateY() + "):(" + getCoordinateX() + ")." +
                "\nYour current balance is: " + getMoney() + "$.";
    }
}