package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            // Update quality based on item type
            updateItemQuality(item);

            // Update sell-in date (except for Sulfuras)
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            // Handle expired items
            if (item.sellIn < 0) {
                handleExpiredItem(item);
            }
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item)) {
            increaseQualityIfPossible(item);
        } else if (isBackstagePasses(item)) {
            updateBackstagePassesQuality(item);
        } else if (!isSulfuras(item)) {
            decreaseQualityIfPossible(item);
        }
    }

    private void updateBackstagePassesQuality(Item item) {
        increaseQualityIfPossible(item);

        if (item.sellIn <= 10) {
            increaseQualityIfPossible(item);
        }

        if (item.sellIn <= 5) {
            increaseQualityIfPossible(item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (isAgedBrie(item)) {
            increaseQualityIfPossible(item);
        } else if (isBackstagePasses(item)) {
            item.quality = 0; // Backstage passes become worthless after concert
        } else if (!isSulfuras(item)) {
            decreaseQualityIfPossible(item);
        }
    }

    private void increaseQualityIfPossible(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQualityIfPossible(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}