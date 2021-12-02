package cells;

import players.Player;
import utils.Utils;

public class PenaltyCell extends Cell {
    /**
     * Метод возвращает иконку штрафной клетки.
     *
     * @return Иконку "%".
     */
    @Override
    public String getIcon() {
        return "[%]";
    }

    /**
     * Метод срабатывающий при попадании на клетку.
     *
     * @param player  Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        int penaltyValue = (int) (Utils.getPenaltyCoeff() * player.getMoney());
        System.out.println("This is a penalty cell, you have to pay some money.");
        player.setMoney(player.getMoney() - penaltyValue);
        System.out.printf("The %s lost: " + penaltyValue + "$.", player.getType());
        if (player.getMoney() < penaltyValue) System.out.printf("%s has no money to pay the penalty value.", player.getType());
    }
}