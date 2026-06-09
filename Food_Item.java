import java.time.LocalDate;

public class Food_Item
{
    private String itemID,itemName,location,category;
    private double weightInKg;
    private int quantity;
    private LocalDate expiryDate;
    private Food_Item next,previous;

    public Food_Item(String itemID, String itemName, String location, String category,
                     double weightInKg, int quantity, LocalDate expiryDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.location = location;
        this.category = category;
        this.weightInKg = weightInKg;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Food_Item getNext() {
        return next;
    }

    public void setNext(Food_Item next) {
        this.next = next;
    }

    public Food_Item getPrevious() {
        return previous;
    }

    public void setPrevious(Food_Item previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return itemID +","+ itemName +","+ location +","+ category +","+ weightInKg +
                "," + quantity+"," + expiryDate +"\n";
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public String  displayItemDetails(){
        return "ID: "+this.getItemID()+", Name: "+this.getItemName()
                +", Category: "+this.getCategory()+", Weight(kg): "+this.getWeightInKg()
                +", Quantity: "+this.getQuantity()+", Location: "+this.getLocation()+
                ", Expiry Date: "+this.getExpiryDate()+"\n";
    }
}