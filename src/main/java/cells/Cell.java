package cells;

import players.Player;

public class Cell {
    /**
     * Метод возвращает иконку клетки пустого поля.
     *
     * @return Иконку ":|:".
     */
    public String getIcon() {
        return ":|:";
    }

    /**
     * Метод срабатывающий при попадании на клетку.
     *
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    public void actAfterCellHitting(Player player, Player player2) {
    }
}