package main;

import cells.*;
import players.Bot;
import players.Human;
import players.Player;
import utils.Utils;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int TAXI_CELL_NUMBER = 2;
    public static final int PENALTY_CELL_NUMBER = 1;
    public static final int MIN_MONEY = 500;
    public static final int MAX_MONEY = 15000;
    public static final int MIN_SIZE_IN = 6;
    public static final int MAX_SIZE_IN = 30;
    static Scanner scanner = new Scanner(System.in);
    private static Cell[][] cells;
    private static int height;
    private static int width;

    public static void main(String[] args) {
        System.out.println("\nWelcome to the MONOPOLY game!\n" +
                "\nFirst of all enter the size of the playing field.");
        System.out.println("Height (within 6 - 30): ");
        height = getHumanNumber(MIN_SIZE_IN, MAX_SIZE_IN);
        System.out.println("Width (within 6 - 30): ");
        width = getHumanNumber(MIN_SIZE_IN, MAX_SIZE_IN);
        System.out.println("And finally amount of money (within 500 - 15000): ");
        int money = getHumanNumber(MIN_MONEY, MAX_MONEY);
        System.out.println();
        cells = new Cell[width][height];
        fillNeutralCells(cells);
        fillEmptyCells(cells);
        fillGameTable(width, 1, cells, 0);
        fillGameTable(width, height, cells, 0);
        fillGameTable(height, 1, cells, 1);
        fillGameTable(height, width, cells, 1);

        Human human = new Human(0, 0, money);
        Bot bot = new Bot(0, 0, money);

        printGameTable(cells, human, bot);
        System.out.println("Amount of money is: " + money + "$;");
        System.out.println("Compensation coefficient is: " + Utils.getRoundCoeff(Utils.getCompensationCoeff()) + ";");
        System.out.println("Improvement coefficient is: " + Utils.getRoundCoeff(Utils.getImproveCoeff()) + ";");
        System.out.println("Penalty coefficient is: " + Utils.getRoundCoeff(Utils.getPenaltyCoeff()) + ";");

        Random random = new Random();
        int turn = random.nextInt(2);
        if (turn == 0) {
            System.out.println("\nHuman turn!");
            while (human.getMoney() >= 0 && bot.getMoney() >= 0) {
                gameOn(human, bot);
                if (human.getMoney() < 0) {
                    System.out.println("\nPLAYER - BOT WON!!!");
                    break;
                }
                gameOn(bot, human);
            }
        } else {
            System.out.println("\nBot turn!");
            while (bot.getMoney() >= 0 && human.getMoney() >= 0) {
                gameOn(bot, human);
                if (bot.getMoney() < 0) {
                    System.out.println("\nPLAYER - HUMAN WON!!!");
                    break;
                }
                gameOn(human, bot);
            }
        }
    }

    /**
     * Метод воспроизводит действия игры. Подбрасывает кубики, перемещает игроков, вызывает действие определенной клетки.
     *
     * @param player  Активный игрок.
     * @param player2 Противник.
     */
    public static void gameOn(Player player, Player player2) {
        int cubesSum = Utils.getCubesSum();
        player.movePlayer(cubesSum);
        System.out.println("\nPlayer - " + player.getType() + " threw: " + cubesSum);
        if (player instanceof Human) {
            printGameTable(cells, (Human) player, (Bot) player2);
        } else {
            printGameTable(cells, (Human) player2, (Bot) player);
        }
        cells[player.getCoordinateX()][player.getCoordinateY()].actAfterCellHitting(player, player2);
        System.out.println(player);
    }

    /**
     * Метод заполняет игровое поле определенным количеством различных клеток.
     *
     * @param sideSize Стороны массива.
     * @param twoSize  Определяется 2 числами.
     * @param cells    Массив.
     * @param flag     Флаг.
     */
    public static void fillGameTable(int sideSize, int twoSize, Cell[][] cells, int flag) {
        Random random = new Random();
        ArrayList<Integer> freeCellsRow = new ArrayList<>();
        for (int i = 1; i < sideSize - 1; i++) {
            freeCellsRow.add(i);
        }
        for (int i = 0; i < TAXI_CELL_NUMBER; i++) {
            int randomTaxi = random.nextInt(freeCellsRow.size());
            int value = freeCellsRow.get(randomTaxi);
            if (flag == 0) {
                cells[value][twoSize - 1] = new TaxiCell();
            } else {
                cells[twoSize - 1][value] = new TaxiCell();
            }
            freeCellsRow.remove(randomTaxi);
        }
        for (int i = 0; i < PENALTY_CELL_NUMBER; i++) {
            int randomPenalty = random.nextInt(freeCellsRow.size());
            int value = freeCellsRow.get(randomPenalty);
            if (flag == 0) {
                cells[value][twoSize - 1] = new PenaltyCell();
            } else {
                cells[twoSize - 1][value] = new PenaltyCell();
            }
            freeCellsRow.remove(randomPenalty);
        }
        for (int i = 0; i < freeCellsRow.size(); i++) {
            int N = Utils.getShopValue();
            int K = (int) (Utils.getInitialCompValue() + Utils.getCompensationCoeff() * Utils.getInitialCompValue());
            double compCoeff = Utils.getCompensationCoeff();
            double improveCoeff = Utils.getImproveCoeff();
            if (flag == 0) {
                cells[freeCellsRow.get(i)][twoSize - 1] = new ShopCell(N, K, compCoeff, improveCoeff);
            } else {
                cells[twoSize - 1][freeCellsRow.get(i)] = new ShopCell(N, K, compCoeff, improveCoeff);
            }
            freeCellsRow.remove(i);
            i--;
        }
    }

    /**
     * Метод печатает игровое поле.
     *
     * @param cells Двухмерный массив.
     * @param human Человек.
     * @param bot   Бот.
     */
    public static void printGameTable(Cell[][] cells, Human human, Bot bot) {
        System.out.print("  ");
        for (int i = 0; i < cells.length; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < width; j++) {
                if (i == human.getCoordinateY() && j == human.getCoordinateX() &&
                        i == bot.getCoordinateY() && j == bot.getCoordinateX()) {
                    System.out.print("[⇉]");
                } else if (i == human.getCoordinateY() && j == human.getCoordinateX()) {
                    System.out.print("[ℙ]");
                } else if (i == bot.getCoordinateY() && j == bot.getCoordinateX()) {
                    System.out.print("|\uD83E\uDD16|");
                } else {
                    System.out.print(cells[j][i].getIcon());
                }
            }
            System.out.println();
        }
    }

    /**
     * Метод заполняет поле нейтральными клетками.
     *
     * @param cells Двухмерный массив.
     */
    public static void fillNeutralCells(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * Метод заполняет поле пустыми клетками.
     *
     * @param cells Двухмерный массив.
     */
    public static void fillEmptyCells(Cell[][] cells) {
        cells[0][0] = new EmptyCell();
        cells[0][height - 1] = new EmptyCell();
        cells[width - 1][0] = new EmptyCell();
        cells[width - 1][height - 1] = new EmptyCell();
    }

    /**
     * Метод проверяет введенное число на правильность ввода.
     *
     * @param leftBorder  Левая граница числа.
     * @param rightBorder Правая граница числа.
     * @return Возвращает введенное число.
     */
    public static int getHumanNumber(int leftBorder, int rightBorder) {
        String playerStr = scanner.next();
        while (!checkHumanStr(playerStr, leftBorder, rightBorder)) {
            System.out.println("Incorrect input value, try again!");
            playerStr = scanner.next();
        }
        return Integer.parseInt(playerStr);
    }

    /**
     * Метод проверяет введенное число на парс и границы ввода.
     *
     * @param humanStr Введенное пользователем число.
     * @return Возвращает число, если True, иначе возвращает False.
     */
    public static boolean checkHumanStr(String humanStr, int leftBorder, int rightBorder) {
        try {
            int num = Integer.parseInt(humanStr);
            if (num >= leftBorder && num <= rightBorder) {
                return true;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Parse error occurred");
        }
        return false;
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
}