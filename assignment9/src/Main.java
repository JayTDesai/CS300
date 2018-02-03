public class Main {

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine(20);
        try {
            vm.dispenseNextItem();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            vm.dispenseItemAtIndex(2);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            vm.getNextItem();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            vm.getItemAtIndex(2);
        } catch (Exception e) {
            System.out.println();
        }

        Item item = new Item(1, "Expire1");
        vm.addItem(item);
        try {
            item = vm.dispenseNextItem();
            System.out.println(item.getDescription());
        } catch (Exception e) {
            System.out.println(e);
        }

        item = new Item(1, "Expire1");
        vm.addItem(item);
        try {
            vm.dispenseItemAtIndex(2);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            item = vm.getNextItem();
            System.out.println(item.getDescription());
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            item = vm.getItemAtIndex(2);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            item = vm.getItemAtIndex(0);
            System.out.println(item.getDescription());
        } catch (Exception e) {
            System.out.println(e);
        }
        item = new Item(5, "Expire5");
        vm.addItem(item);
        item = new Item(6, "Expire6");
        vm.addItem(item);
        item = new Item(9, "Expire9");
        vm.addItem(item);
        item = new Item(11, "Expire11");
        vm.addItem(item);
        item = new Item(8, "Expire8");
        vm.addItem(item);
        item = new Item(15, "Expire15");
        vm.addItem(item);
        item = new Item(17, "Expire17");
        vm.addItem(item);
        item = new Item(21, "Expire21");
        vm.addItem(item);
        vm.dispenseItemAtIndex(1);
        vm.printAllItems();
    }
}
