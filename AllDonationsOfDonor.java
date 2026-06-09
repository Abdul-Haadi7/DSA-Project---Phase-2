//Store all donations of every donor in doubly linked list.

public class AllDonationsOfDonor
{
    private Donation head,tail;
    public Donation getHead()
    {
        return head;
    }

    public void setHead(Donation head)
    {
        this.head = head;
    }

    public Donation getTail()
    {
        return tail;
    }

    public void setTail(Donation tail) {
        this.tail = tail;
    }
    public void insertAtEnd(Donation donation)
    {
        if (this.head == null)
        {
            head = tail = donation;
            return;
        }
        tail.setNext(donation);
        donation.setPrevious(tail);
        tail = donation;
    }
    public void displayDonations(){
        Donation temp = head;
        while (temp!=null)
        {
            System.out.print(temp);
            temp = temp.getNext();
        }
    }
}
