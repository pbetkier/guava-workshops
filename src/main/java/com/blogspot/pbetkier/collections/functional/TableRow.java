package com.blogspot.pbetkier.collections.functional;

import com.google.common.base.Objects;

import java.awt.*;

public class TableRow {

    private Color background = Color.WHITE;

    private String text = "";

    public TableRow(String text) {
        this.text = text;
    }

    public TableRow(String text, Color background) {
        this.background = background;
        this.text = text;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
