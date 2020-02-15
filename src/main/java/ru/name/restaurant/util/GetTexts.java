package ru.name.restaurant.util;

import javafx.scene.text.Text;

public class GetTexts {
    public Text discountText;
    public Text resultText;
    public Text forPaymentText;

    public GetTexts(Text discountText, Text resultText, Text forPaymentText) {
        this.discountText = discountText;
        this.resultText = resultText;
        this.forPaymentText = forPaymentText;
    }

    public Text getDiscountText() {
        return discountText;
    }

    public void setDiscountText(Text discountText) {
        this.discountText = discountText;
    }

    public Text getResultText() {
        return resultText;
    }

    public void setResultText(Text resultText) {
        this.resultText = resultText;
    }

    public Text getForPaymentText() {
        return forPaymentText;
    }

    public void setForPaymentText(Text forPaymentText) {
        this.forPaymentText = forPaymentText;
    }
}