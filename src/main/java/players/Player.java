package players;

import lombok.Getter;
import lombok.Setter;
import main.Main;

@Getter
@Setter

public abstract class Player {
    private int coordinateX;
    private int coordinateY;
    private int money;

    public Player(int coordinateX, int coordinateY, int money) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.money = money;
    }
    /**
     * Метод перемещает игрока на сумму выпавших кубиков.
     * @param cubesSum Возвращает сумму кубиков.
     */
    public void movePlayer(int cubesSum) {
        while (cubesSum > 0) {
            if (coordinateX < Main.getWidth() - 1 && coordinateY == 0) {
                coordinateX++;
            } else if (coordinateX == Main.getWidth() - 1 && coordinateY < Main.getHeight() - 1) {
                coordinateY++;
            } else if (coordinateX != 0 && coordinateY == Main.getHeight() - 1) {
                coordinateX--;
            } else {
                coordinateY--;
            }
            cubesSum--;
        }
    }

    public abstract String getType();

    public abstract String toString();
}