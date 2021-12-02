package cells;

import players.Player;

public class EmptyCell extends  Cell {

    /**
     * Метод возвращает иконку нейтральной клетки.
     * @return Иконку "E".
     */
    @Override
    public String getIcon() {
        return "[E]";
    }

    /**
     * Метод срабатывающий при попадании на клетку.
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        System.out.println("Just relax for a while.");
    }

}