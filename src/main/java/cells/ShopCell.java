package cells;

import lombok.Getter;
import lombok.Setter;
import main.Main;
import players.Bot;
import players.Human;
import players.Player;
import shops.BotShop;
import shops.HumanShop;
import java.util.Random;

@Setter
@Getter

public class ShopCell extends Cell {
    private int N;
    private int K;
    private double compensationCoefficient;
    private double improvementCoefficient;
    private int shopUpgradeValue;

    public ShopCell(int n, int k, double compensationCoefficient, double improvementCoefficient) {
        N = n;
        K = k;
        this.compensationCoefficient = compensationCoefficient;
        this.improvementCoefficient = improvementCoefficient;
    }
    /**
     * Метод возвращает иконку клетки магазин.
     * @return Иконку "🏬".
     */
    @Override
    public String getIcon() {
        return "[S]";
    }

    /**
     * Метод срабатывающий при попадании на клетку.
     * @param player Активный игрок.
     * @param player2 Противник.
     */
    @Override
    public void actAfterCellHitting(Player player, Player player2) {
        printInfo();
        Random random = new Random();
        if (player instanceof Human && player.getMoney() >= getN()) {
            System.out.println("Would you like to buy it?\nPress '1' if you agree or '2' otherwise.");
            int userNum = Main.getHumanNumber(1,2);
            if (userNum == 1) {
                player.setMoney(player.getMoney() - getN());
                Main.getCells()[player.getCoordinateX()][player.getCoordinateY()]
                        = new HumanShop(N, K, compensationCoefficient, improvementCoefficient, shopUpgradeValue);
                System.out.println("Human - player spent: " + getN() + "$ on the purchase of the store.");
            } else {
                System.out.println("Human - player passed by.");
            }
        } else {
            if (player instanceof Bot) {
                int randNum = random.nextInt(2);
                if (randNum == 0 && player.getMoney() >= getN()) {
                    player.setMoney(player.getMoney() - getN());
                    Main.getCells()[player.getCoordinateX()][player.getCoordinateY()]
                            = new BotShop(N, K, compensationCoefficient, improvementCoefficient, shopUpgradeValue);
                    System.out.println("Bot - player spent: " + getN() + "$ on the purchase of the store.");
                } else {
                    System.out.println("Bot - player passed by.");
                }
            }
        }
    }

    /**
     * Выводит информацию о нейтральном магазине.
     */
    public void printInfo() {
        System.out.println("This is a neutral shop cell.\nShop value is: " + getN() + ".");
    }
}