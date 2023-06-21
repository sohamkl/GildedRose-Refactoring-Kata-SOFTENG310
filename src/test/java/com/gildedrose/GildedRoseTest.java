//Tests from: 
//https://github.com/sparsick/coding-katas/blob/master/GildedRose-Refactoring-Kata-Java/com/gildedrose/GildedRoseTest.java
//NOTE: tests are only for the purpose of validating you do not break the code when adding new features / refactoring
//      Proper test construction will be covered later in the course

package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    //All items have a name
    @Test
    public void checkItemName() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals("foo", app.items[0].name);
    }
    
    //All items have a SellIn value which denotes the number of days we have to sell the item
    //All items have a Quality value which denotes how valuable the item is
    //At the end of each day our system lowers both values for every item
    @Test
    public void decreaseSellDate() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    public void decreaseQuality() {
        Item[] items = new Item[] { new Item("foo", 0, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    //Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void decreaseQualityTwice() {
        Item[] items = new Item[] { new Item("foo", -1, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }
    
    @Test
    public void decreaseQualityTwiceWithNoNegativeValue() {
        Item[] items = new Item[] { new Item("foo", -1, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    //The Quality of an item is never negative
    @Test
    public void qualityCannotBeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    //"Aged Brie" actually increases in Quality the older it gets
    @Test
    public void increaseQualityForAgedBrieItem() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(2, app.items[0].quality);
    }
    
    @Test
    public void increaseQualityTwicedForAgedBrieItemBecauseSellInIsPassed() {
        Item[] items = new Item[] { new Item("Aged Brie", -2, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void increaseQualityTwicedForAgedBrieItemBecauseSellInIs0() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
    }

    //The Quality of an item is never more than 50
    @Test
    public void qualityCannotBeGreaterThan50() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void qualityIncreasedTwiceCannotBeGreaterThan50() {
        Item[] items = new Item[] { new Item("Aged Brie", -2, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    //Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
    //legendary item and as such its Quality is 80 and it never alters.
    @Test
    public void sulfurasItemNeverBeSold() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 2, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    public void sulfurasItemNeverDecreaseQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 2, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(80, app.items[0].quality);
    }

    //"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    //Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
    //Quality drops to 0 after the concert
    @Test
    public void checkQualityForBackstagePasses11Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void checkQualityForBackstagePasses10Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void checkQualityCannotBeGreaterThan50ForBackstagePasses10Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void checkQualityForBackstagePasses5Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(13, app.items[0].quality);
    }

    @Test
    public void checkQualityCannotBeGreaterThan50ForBackstagePasses5Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void checkQualityForBackstagePasses0Daysleft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void checkQualityForBackstagePassesAfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void checkSellInForConjuredItems(){
        Item[] items = new Item[] { new Item("Conjured", 2, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
    }

}
