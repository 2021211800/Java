package week03;

public class exer1 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int i=0, j=0;
        if(tickets.length==1)
            return tickets[0];
        while (tickets[k]!=0) {
            if(tickets[i]>0) {
                tickets[i]--;
                j++;
            }
            i++;
            if(i==tickets.length)
                i=0;
        }return j;
    }

}
