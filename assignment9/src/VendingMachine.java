import java.lang.IndexOutOfBoundsException;

public class VendingMachine {
    private Item[] items;  // store items in a min-heap
    private int itemCount; // number of items in this heap

    private static String FULL_CAPACITY = "WARNING: full capacity ";
    private static String EMPTY_CAPACITY = "WARNING: empty";
    private static String INALID_INDEX = "WARNING: Invalid";

    // Note use of min-heap here, to prioritize the smallest (soonest) expiration day.
    // You may decide to use either 0 or 1 as the top-index in this items array.

    public VendingMachine(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        items = new Item[capacity + 1];
        itemCount = 0;
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
        items[0] = new Item(Integer.MIN_VALUE, "Dummy");
    }

    private int getParent(int childIndex) {
        // return the parent index of the given child index
        return childIndex / 2;
    }

    private int getLeftChild(int parentIndex) {
        return 2 * parentIndex;
    }

    private int getRightChild(int parentIndex) {
        // return the right child index of the given parent index
        return (2 * parentIndex) + 1;
    }

    private void swap(int itemOneIndex, int itemTwoIndex) {
        Item tmp;
        tmp = items[itemOneIndex];
        items[itemOneIndex] = items[itemTwoIndex];
        items[itemTwoIndex] = tmp;
    }

    private void addHelper(int index) {
        // Propagates the min-heap order property from the node at position index,
        // up through it's ancestor nodes. Assumes that only the node at position
        // index may be in violation of this property. This is useful when adding
        // a new item to the bottom of the heap.
        while (index > 1) {
            int parentIndex = getParent(index);
            Item item = items[parentIndex];
            Item currItem = items[index];
            if (currItem.getExpirationDay() < item.getExpirationDay()) {
                swap(index, parentIndex);
            } else {
                return;
            }
            index = parentIndex;
        }
    }

    private void removeHelper(int index) {
        // Propagates the min-heap order property from the node at position index,
        // down through it's highest priority descendant nodes. Assumes that the
        // children of the node at position index conform to this heap property.
        // This is useful when removing an item from the top of the heap.
        Item currItem = items[index];
        int leftChildIndex, rightChildIndex;
        while (index <= itemCount) {
            rightChildIndex = getRightChild(index);
            leftChildIndex = getLeftChild(index);
            if (rightChildIndex <= itemCount) {
                Item rightItem = items[rightChildIndex];
                Item leftItem = items[leftChildIndex];
                if (leftItem.getExpirationDay() < currItem.getExpirationDay()) {
                    swap(index, leftChildIndex);
                    index = leftChildIndex;
                } else if (rightItem.getExpirationDay() < currItem.getExpirationDay()) {
                    swap(index, rightChildIndex);
                    index = rightChildIndex;
                }
            } else if (leftChildIndex <= itemCount) {
                Item leftItem = items[leftChildIndex];
                if (leftItem.getExpirationDay() < currItem.getExpirationDay()) {
                    swap(index, leftChildIndex);
                    index = leftChildIndex;
                    return;
                }
            }
        }
    }

    public void addItem(Item item) throws IllegalArgumentException {
        if (itemCount > items.length) {
            throw new IllegalArgumentException(FULL_CAPACITY);
        }
        items[++itemCount] = item;
        addHelper(itemCount);
    }

    public Item dispenseNextItem() {
        // Dispense the item with the smallest expiration date from this
        // vending machine, while maintaining the min-heap properties.
        // This method removes the item returned from the heap.
        if (itemCount == 0) {
            throw new IllegalStateException(EMPTY_CAPACITY);
        }
        Item item = items[1];
        swap(1, itemCount);
        items[--itemCount] = null;
        removeHelper(1);
        return item;
    }

    public Item getNextItem() {
        // This method returns a reference to the next item that will be dispensed.
        // This method does NOT change the state of the Vending Machine or its heap.
        if (itemCount == 0) {
            throw new IllegalStateException(EMPTY_CAPACITY);
        }
        return items[1];
    }

    public Item dispenseItemAtIndex(int index) {
        // Dispense the item from a particular array index, while maintaining
        // the min-heap properties.  This method removes that item from the heap.
        // This index parameter assumes the top-index is zero.  So you'll need to
        // add one to this index, if you are using the top-index = 1 convention.
        Item item = getItemAtIndex (index);
        swap(index + 1, itemCount);
        items[itemCount--] = null;
        removeHelper(index + 1);
        return item;
    }

    public Item getItemAtIndex(int index) {
        // This method returns a reference to the item at position index.
        // This method does not change the state of the vending machine.
        // This index parameter assumes the top-index is zero. So you'll need to
        // add one to this index, if you are using the top-index = 1 convention.
        if (itemCount == 0) {
            throw new IllegalStateException(EMPTY_CAPACITY);
        }
        if (index + 1 > itemCount) {
            throw new IndexOutOfBoundsException(INALID_INDEX);
        }
        return items[index + 1];
    }

    public void printAllItems() {
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(items[i].getDescription());
        }
    }
}
