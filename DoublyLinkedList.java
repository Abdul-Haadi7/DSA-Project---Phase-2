public class DoublyLinkedList <Datatype>
{
    private Node<Datatype> head,tail;

    public Node getHead()
    {
        return head;
    }

    public void setHead(Node<Datatype> head)
    {
        this.head = head;
    }

    public Node<Datatype> getTail()
    {
        return tail;
    }

    public void setTail(Node<Datatype> tail) {
        this.tail = tail;
    }
    public void insertAtEnd(Node<Datatype> node)
    {
        if (this.head == null)
        {
            head = tail = node;
            return;
        }
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
    }
    public void displayList()
    {
        Node<Datatype> temp = this.head;
        while (temp!=null)
        {
            System.out.print(temp.getData());
            temp = temp.getNext();
        }
    }
    public boolean containsID(String id){
        Node<Datatype> temp = head;
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
