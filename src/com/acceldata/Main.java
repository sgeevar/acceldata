package com.acceldata;

import java.util.ArrayList;
import java.util.Random;

class Node {
    String data;
    Node next;
    Node random;

    public Node(String data) {
        this.data = data;
        this.next = this.random = null;
    }
}

class LinkedList {
    Node root;

    public void insert(Node n) {
        n.next = root;
        root = n;
    }

    public LinkedList(Node root) {
        this.root = root;
    }

    public void print() {
        Node n = root;
        while(n != null) {
            System.out.println(n.data + "->" + n.random.data);
            n = n.next;
        }
    }

    public LinkedList clone() {
        Node source = root;
        while (source != null) {
            Node n = new Node(source.data + "1");
            n.next = source.next;
            source.next = n;
            source = source.next.next;
        }

        //Now copy the random pointers
        source = root;
        while (source != null) {
            source.next.random = source.random.next;
            source = source.next.next;
        }

        //Separate A and B list
        source = root;
        Node target = source.next;
        LinkedList copy = new LinkedList(target);

        while(source != null) {
            source.next = source.next.next;
            target.next = target.next == null? null: target.next.next;

            source = source.next;
            target = target.next;
        }

        return copy;
    }

}

public class Main {
    /* Solution:
    Create b nodes and place in the middle of a list
    Copy random link using source->next->random = source->random->next;
    Then:
        source->next = source->next->next;
        target->next = target->next->next;
     */

    public static void main(String[] args) {
	    LinkedList source = new LinkedList(null);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A"));
        nodes.add(new Node("B"));
        nodes.add(new Node("C"));
        nodes.add(new Node("D"));
        nodes.add(new Node("E"));

        for(Node n: nodes) {
            Random r = new Random();
            n.random = nodes.get(r.nextInt(nodes.size()));
            source.insert(n);
        }

        System.out.println("Source list");
        source.print();

        LinkedList target = source.clone();

        System.out.println("Target list");
        target.print();

        System.out.println("Source list after clone");
        source.print();
    }
}
