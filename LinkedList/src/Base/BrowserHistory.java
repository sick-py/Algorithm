package Base;

public class BrowserHistory {
    //1472
    class Node{
        String url;
        Node next, prev;
        Node(String s) {
            url = s;
        }
    }
    Node dummy, cur;


    public BrowserHistory(String homepage) {
        dummy = new Node("dummy");
        Node added = new Node(homepage);
        dummy.next = added;
        added.prev = dummy;
        cur = added;
    }

    public void visit(String url) {
        Node added = new Node(url);
        cur.next = added;
        added.prev = cur;
        cur = cur.next;
    }

    public String back(int steps) {
        while (cur != null && steps != 0) {
            cur = cur.prev;
            steps--;
        }

        if (cur == null || cur == dummy) {
            cur = dummy.next;
        }
        return cur.url;
    }

    public String forward(int steps) {
        while (cur.next != null && steps != 0) {
            cur = cur.next;
            steps--;
        }
        return cur.url;
    }
}
