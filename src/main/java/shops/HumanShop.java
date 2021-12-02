package shops;

import cells.ShopCell;
import players.Bot;
import players.Human;
import players.Player;
import main.Main;

public class HumanShop extends ShopCell {
    private int shopUpgradeValue;
    public HumanShop(int n, int k, double compensationCoefficient, double improvementCoefficient, int shopUpgradeValue) {
        super(n, k, compensationCoefficient, improvementCoefficient);
        this.shopUpgradeValue = shopUpgradeValue;
    }
    /**
     * Метод возвращает знак клетки магазина человека.
     * @return Иконку "M".
     */
    @Override
    public String getIcon() {
        return "[M]";
    }

    /**
     * Метод срабатывающий при попадании на клетку и взаимодействующий с ботом - игроком.
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        shopUpgradeValue = (int)Math.round(getN() + getImprovementCoefficient() * getN());
        printInfo();
        if (player instanceof Human && player.getMoney() > shopUpgradeValue) {
            System.out.println("Would you like to upgrade it?\nPress '1' if you agree or '2' otherwise.");
            int userNum = Main.getHumanNumber(1,2);
            if (userNum == 1) {
                setN(shopUpgradeValue);
                setK((int)Math.round(getK() + getCompensationCoefficient() * getK()));
                player.setMoney(player.getMoney() - getN());
                System.out.println("Human - player spent: " + getN() + "$ on the purchase of the store.");
            } else {
                System.out.println("Human - player passed by.");
            }
        } else {
            if (player instanceof Bot) {
                player.setMoney(player.getMoney() - getK());
                System.out.println("Bot - player spent: " + getK() + "$ compensation.");
                player2.setMoney(player.getMoney() + getK());
            } else {
                System.out.println("Bot - player has no money to pay the compensation.");
            }
        }
    }

    /**
     * Выводит информацию о магазине игрока - человека.
     */
    @Override
    public void printInfo() {
        System.out.println("This is a human's shop cell." + "\nStore upgrade value is: " +
                shopUpgradeValue + "$; Compensation is: " + getK() + "$.");
    }
}