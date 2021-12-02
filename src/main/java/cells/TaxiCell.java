package cells;

import main.Main;
import players.Player;

public class TaxiCell extends Cell {
    /**
     * Метод возвращает иконку клетки такси.
     * @return Иконку "🚖".
     */
    @Override
    public String getIcon() {
        return "[T]";
    }

    /**
     * Метод срабатывающий при попадании на клетку.
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        int min = 3;
        int max = 5;
        int taxiDistance = min + (int)(Math.random() * ((max - min) + 1));
        System.out.println("This is a taxi cell. Move on: " + taxiDistance + " cells ahead.");
        player.movePlayer(taxiDistance);
        Main.getCells()[player.getCoordinateX()][player.getCoordinateY()].actAfterCellHitting(player, player2);
    }
}