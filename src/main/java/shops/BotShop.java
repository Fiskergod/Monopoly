package shops;

import cells.ShopCell;
import players.Bot;
import players.Human;
import players.Player;
import java.util.Random;

public class BotShop extends ShopCell {
    private int shopUpgradeValue;
    public BotShop(int n, int k, double compensationCoefficient, double improvementCoefficient, int shopUpgradeValue) {
        super(n, k, compensationCoefficient, improvementCoefficient);
        this.shopUpgradeValue = shopUpgradeValue;
    }
    /**
     * Метод возвращает знак клетки магазина бота.
     * @return Иконку "O".
     */
    @Override
    public String getIcon() {
        return "[O]";
    }

    /**
     * Метод срабатывающий при попадании на клетку и взаимодействующий с человеком - игроком.
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        Random random = new Random();
        printInfo();
        shopUpgradeValue = (int)Math.round(getN() + getImprovementCoefficient() * getN());
        if (player instanceof Bot) {
            int botNum = random.nextInt(2);
            if (botNum == 0 && player.getMoney() > shopUpgradeValue) {
                setN(shopUpgradeValue);
                setK((int)Math.round(getK() + getCompensationCoefficient() * getK()));
                player.setMoney(player.getMoney() - getN());
                System.out.println("Bot - player spent: " + getN() + "$ on the purchase of the store.");
            } else {
                System.out.println("Bot - player passed by.");
            }
        } else {
            if (player instanceof Human) {
                player.setMoney(player.getMoney() - getK());
                player2.setMoney(player.getMoney() + getK());
            }
        }
    }

    /**
     * Выводит информацию о магазине игрока - бота.
     */
    @Override
    public void printInfo() {
        System.out.println("This is a bot's shop cell. Compensation is: " + getK() + "$.");
    }
}