package vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

import vendingmachine.utils.ExceptionMessages;

public class VendingMachine {

    private final int machineMoney;

    public VendingMachine(final int machineMoney) {
        validateMachineMoney(machineMoney);
        this.machineMoney = machineMoney;
    }

    public boolean isContinuePurchasing(final List<Product> products, final int cheapestProductPrice, final int purchasingCost) {
        boolean isContinueDeal = true;

        if (!isCostBiggerCheapestProductPrice(cheapestProductPrice, purchasingCost)) {
            isContinueDeal = false;
        }

        if (!isSoldOutAllProduct(products)) {
            isContinueDeal = false;
        }

        return isContinueDeal;
    }

    protected boolean isSoldOutAllProduct(final List<Product> products) {
        boolean isContinueDeal = false;

        for (Product product : products) {
            if (!(product.getProductCount() == 0)) {
                isContinueDeal = true;
            }
        }

        return isContinueDeal;
    }

    protected boolean isCostBiggerCheapestProductPrice(final int cheapestProductPrice, final int purchasingCost) {
        return purchasingCost >= cheapestProductPrice;
    }


    protected void validateMachineMoney(final int inputMachineMoney) {
        if (!isMultiplyTen(inputMachineMoney)) {
            throw new IllegalArgumentException(ExceptionMessages.ERROR_MESSAGE_INPUT_MACHINE_MONEY_CONDITION.getErrorMessage());
        }
    }

    protected boolean isMultiplyTen(final int inputMachineMoney) {
        return (inputMachineMoney % 10) == 0;
    }

    public List<Integer> calculateCoins(final int machineMoney, final List<Integer> coins) {
        List<Integer> machineCoins = new ArrayList<>();
        int machineMoneyToCountCoin = machineMoney;

        while (machineMoneyToCountCoin != 0) {
            machineCoins = new ArrayList<>();
            machineMoneyToCountCoin = machineMoney;

            machineMoneyToCountCoin = inputCoinRandomly(machineCoins, coins, machineMoneyToCountCoin);
        }

        return machineCoins;
    }


    protected int inputCoinRandomly(final List<Integer> machineCoins, final List<Integer> coins, int machineMoneyToCountCoin) {
        for (int coinUnit : coins) {
            int share = machineMoneyToCountCoin / coinUnit;

            final List<Integer> inputRandomCoinRange = createCoinRangeList(share);
            int coinCount = Coin.COIN_500.inputCoinCountRandomly(inputRandomCoinRange);

            machineMoneyToCountCoin = machineMoney - (coinCount * coinUnit);
            machineCoins.add(coinCount);
        }

        return machineMoneyToCountCoin;
    }


    protected List<Integer> createCoinRangeList(final int share) {
        final List<Integer> inputRandomCoinRange = new ArrayList<>();

        for (int j = 0; j <= share; j++) {
            inputRandomCoinRange.add(j);
        }

        return inputRandomCoinRange;
    }

    public List<Integer> createCoinUnitList() {
        final List<Integer> coinUnitList = new ArrayList<>();
        Coin[] coin = Coin.values();

        for (int i = 0; i < Coin.values().length; i++) {
            coinUnitList.add(coin[i].getAmount());
        }

        return coinUnitList;
    }

    public void validatePurchasingProductNameOnMachine(final List<Product> products, final String purchasingProductName) {
        boolean checkPurchasingProductOnProductList = false;

        for (Product product : products) {
            if (product.getName().compareProductName(purchasingProductName)) {
                checkPurchasingProductOnProductList = true;
            }
        }

        if (!checkPurchasingProductOnProductList) {
            throw new IllegalArgumentException(ExceptionMessages.ERROR_MESSAGE_PURCHASING_PRODUCT_NAME_CONDITION.getErrorMessage());
        }
    }

    public int sellProduct(final List<Product> products, final String choosePurchasingProductName, int purchasingCost) {
        for (Product product : products) {
            if (product.getName().compareProductName(choosePurchasingProductName)) {
                purchasingCost = product.getPrice().subtractPrice(purchasingCost);
                product.getCount().minusCount();
            }
        }

        return purchasingCost;
    }

    public void validatePurchasingProductSoldOut(final List<Product> products, final String purchasingProductName) {
        boolean checkPurchasingProductSoldOut = false;

        for (Product product : products) {
            if (product.getName().compareProductName(purchasingProductName)) {
                checkPurchasingProductSoldOut = product.getCount().isCountValidation();
            }
        }

        if (!checkPurchasingProductSoldOut) {
            throw new IllegalArgumentException(ExceptionMessages.ERROR_MESSAGE_PURCHASING_PRODUCT_SOLD_OUT.getErrorMessage());
        }
    }

}
