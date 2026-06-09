public class DoublyLinkedList
{
    private Node head,tail;

    public Node getHead()
    {
        return head;
    }

    public void setHead(Node head)
    {
        this.head = head;
    }

    public Node getTail()
    {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
    public void insertAtEnd(Node str)
    {
        if (this.head == null)
        {
            head = tail = str;
            return;
        }
        tail.setNext(str);
        str.setPrevious(tail);
        tail = str;
    }
    public void displayDonations(){
        Node temp = this.head;
        while (temp!=null)
        {
            System.out.print(temp);
            temp = temp.getNext();
        }
    }
    public boolean containsID(String id){
        Node temp = head;
        while(temp!=null)
        {
            if(temp.getData().equals(id)){
                return true;
            }
            temp=temp.getNext();
        }
        return false;
    }
}
