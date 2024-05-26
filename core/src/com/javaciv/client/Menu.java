/**
 * @file Menu.java
 * @brief This file contains the Menu class.
 * @date 13/05/2024
 */

package com.javaciv.client;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * This class represents a menu.
 * A menu is a table with a background and a list of items.
 * The menu can be horizontal or vertical.
 *
 * The menu items are actors that can be added to the menu.
 * The menu actions are click listeners that can be added to the menu items.
 * The menu items are clickable and can be used to trigger actions.
 *
 * Useful methods :
 * - getMenuItems() : returns the list of items in the menu
 * - setPosition(float x, float y) : sets the position of the top left corner of the menu
 * - setBackgroundColor(Color backgroundColor) : sets the background color of the menu
 * - setVisible(boolean visible) : sets the visibility of the menu  (true by default)
 */
public class Menu extends Table {
    // Define the padding for the menu
    private static final int PADDING = 10;

    private boolean row;

    // Define the menu items
    public Actor[] menuItems;

    /**
     * This is the constructor of the Menu class.
     * @param menuItems the menu items
     * @param menuActions the menu actions
     * @param backgroundColor the background color
     * @param row the orientation of the menu
     */
    public Menu(Actor[] menuItems, ClickListener[] menuActions, Color backgroundColor, boolean row) {
        // Create a background object for the menu
        Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        background.setColor(backgroundColor);
        background.fill();
        TextureRegionDrawable backgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
        this.setBackground(backgroundTexture);
        this.defaults().pad(PADDING);

        this.menuItems = menuItems;
        this.row = row;

        if(row) {
            this.setHeight((getMaxItemHeight() + PADDING * 2) * menuItems.length);
            this.setWidth(getMaxItemWidth() + PADDING * 2);
            this.row();
        } else {
            this.setHeight(getMaxItemHeight() + PADDING * 2);
            this.setWidth((getMaxItemWidth() + PADDING * 2) * menuItems.length);
        }

        for (int i = 0; i < menuItems.length; i++) {
            this.menuItems[i].addListener(menuActions[i]);
        }

        for(Actor item : menuItems) {
            if(row) { row(); }
            this.add(item)
                .pad(PADDING)
                .expand()
                .fill();
        }

        //this.setDebug(true);
    }

    /**
     * This is the constructor of the Menu class.
     * @param menuItems the menu items
     * @param menuActions the menu actions
     * @param backgroundColor the background color
     */
    public Menu(Actor[] menuItems, ClickListener[] menuActions, Color backgroundColor) {
        this(menuItems, menuActions, backgroundColor, false);
    }

    /**
     * This is the constructor of the Menu class.
     * @param menuItems the menu items
     * @param menuActions the menu actions
     * @param row the orientation of the menu
     */
    public Menu(Actor[] menuItems, ClickListener[] menuActions, boolean row) {
        this(menuItems, menuActions, new Color(1, 1, 1, 0.8f), row);
    }

    /**
     * This is the constructor of the Menu class.
     * @param menuItems the menu items
     * @param menuActions the menu actions
     */
    public Menu(Actor[] menuItems, ClickListener[] menuActions) {
        this(menuItems, menuActions, new Color(1, 1, 1, 0.8f), false);
    }

    /**
     * This function returns the list of items in the menu.
     * @return the list of items in the menu
     */ 
    public Actor[] getMenuItems() {
        return menuItems;
    }

    /**
     * This function sets the position of the top left corner of the menu.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    @Override
    public void setPosition(float x, float y) {
        // Set the position of the top left corner of the menu
        super.setPosition(x, y - getHeight());
    }

    /**
     * This function sets the background color of the menu.
     * @param backgroundColor the background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        background.setColor(backgroundColor);
        background.fill();
        TextureRegionDrawable backgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
        this.setBackground(backgroundTexture);
    }

    /**
     * This function resizes the menu.
     * The menu is resized to fit the items.
     */
    public void resizeMenu() {
        if(row) {
            this.setHeight((getMaxItemHeight() + PADDING * 2) * menuItems.length);
            this.setWidth(getMaxItemWidth() + PADDING * 2);
        } else {
            this.setHeight(getMaxItemHeight() + PADDING * 2);
            this.setWidth((getMaxItemWidth() + PADDING * 2) * menuItems.length);
        }
    }

    /**
     * This function returns the maximum width of the items in the menu.
     * @return the maximum width of the items in the menu
     */
    private float getMaxItemWidth() {
        float maxItemWidth = 0;
        for(Actor item : menuItems) {
            if(item.getWidth() > maxItemWidth) {
                maxItemWidth = item.getWidth();
            }
        }
        return maxItemWidth;
    }

    /**
     * This function returns the maximum height of the items in the menu.
     * @return the maximum height of the items in the menu
     */
    private float getMaxItemHeight() {
        float maxItemHeight = 0;
        for(Actor item : menuItems) {
            if(item.getHeight() > maxItemHeight) {
                maxItemHeight = item.getHeight();
            }
        }
        return maxItemHeight;
    }
}
