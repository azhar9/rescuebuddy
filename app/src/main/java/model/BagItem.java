package model;

public class BagItem {

    private String imageUrl;
    private String title;
    private String info;
    private int minCount;
    private int maxCount;
    private int currentCount;
    //private String itemId;
    private int itemWeight;

    public BagItem(){}
    public BagItem(String title, String info, int minCount, int maxCount, int currentCount,int itemWeight) {
        this.title = title;
        this.info = info;
        //this.imageUrl = imageUrl;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.currentCount = currentCount;
       // this.itemId = itemId;
        this.itemWeight = itemWeight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemTitle() {
        return title;
    }

    public void setItemTitle(String title) {
        this.title = title;
    }

    public String getItemInfo() {
        return info;
    }

    public void setItemInfo(String info) {
        this.info = info;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }

    public int getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }
}
