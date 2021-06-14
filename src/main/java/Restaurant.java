import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime current = this.getCurrentTime();
        return current.isAfter(openingTime) && current.isBefore(closingTime);
    }

    public LocalTime getCurrentTime(){ return LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    // get total price
    public int getTotalPrice() {
        List<Item> menus = getMenu();
        int totalPrice = 0;
        for (int i = 0; i < menus.size(); i++) {
            totalPrice += menus.get(i).getPrice();
        }
        return totalPrice;
    }

    public String

    public int getTotalCostOrder(List<String> itemNames) {

        int totalCost = 0;

        for (int i = 0; i < itemNames.size(); i++) {
            for (int j = 0; j < menu.size(); j++) {
                if (itemNames.get(i).compareTo(menu.get(j).getName()) == 0) {
                    totalCost += menu.get(j).getPrice();
                }
            }
        }

        return totalCost;

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());
    }

    public String getName() {
        return name;
    }

    public List<String> getMenuNameList() {

        List<String> menuList = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            menuList.add(menu.get(i).getName());
        }

        return menuList;
    }

}
