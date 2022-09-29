import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    private double result;

    public Calculator() {
    }

    class Node {
        String data;
        Node next, prev;

        Node (String data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    class DoublyLinkedList {
        Node head, tail;

        DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        boolean isEmpty() {
            return head == null;
        }

        void add(String data) {
            Node node = new Node(data);
            if (isEmpty()) {
                head = node;
            } else {
                node.prev = tail;
                tail.next = node;
            }
            tail = node;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
        }

        void printList() {
            Node current = head;
            while (current.next != null) {
                System.out.printf(current.data + " ");
                current = current.next;
            }
            System.out.printf(tail.data);
        }
    }

    private DoublyLinkedList queryUser() {
        DoublyLinkedList dll = new DoublyLinkedList();
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the Calculator.\nInput your desired calculation, alternating between numbers and operators until you are done.\nPlease start with a number, end with a number, and finish with an equals sign (=).");
        System.out.println("| number | + | - | * | / |");
        System.out.println("Enter number");
        String input = s.nextLine();
        boolean numberRequired = true;
        String helpForUser = "operator [+-*/] or equals sign [=]";
        while (!Objects.equals(input, "=")) {
            // checks to see if input is valid
            try {
                double num = Double.parseDouble(input);
                // it's a number, so add node for this input

                if (numberRequired) {
                    dll.add(input);
                    helpForUser = "operator [+-*/] or equals sign [=]";
                    numberRequired = false;
                } else {
                    System.out.println("Oops! Number is required");
                }
            } catch (Exception e) {
                if (Objects.equals(input, "+") || Objects.equals(input, "-") || Objects.equals(input, "*") || Objects.equals(input, "/")) {
                    // it's an operator, so add node for this input
                    if (!numberRequired) {
                        dll.add(input);
                        helpForUser = "number";
                        numberRequired = true;
                    } else {
                        System.out.println("Oops! Operator is required");
                    }
                } else {
                    // the input is invalid
                    System.out.println("Incorrect input -> " + input);
                }
            }
            dll.printList();
            System.out.printf("\nEnter %s\n", helpForUser);
            input = s.nextLine();
        }
        return dll;
    }

   public void performOperation() {
        DoublyLinkedList query = queryUser();
        while (query.isEmpty()) {
            System.out.println("goodness");
            query = queryUser();
        }
        // check if there are multiple nodes
        while (query.head != query.tail) {
            Node runner = query.head;
            Node operator = null;
            // check if there are "*" or "/" operators
            while (runner.next != null && operator == null) {
                if (Objects.equals(runner.data, "*") || Objects.equals(runner.data, "/")) {
                    operator = runner;
                }
                runner = runner.next;
            }
            if (operator != null) {
                switch (operator.data) {
                    case "*":
                        operator.data = String.valueOf(Double.parseDouble(operator.prev.data) * Double.parseDouble(operator.next.data));
//                        System.out.println("after multiplication: " + operator.data);
                        break;
                    case "/":
                        operator.data = String.valueOf(Double.parseDouble(operator.prev.data) / Double.parseDouble(operator.next.data));
//                        System.out.println("after division: " + operator.data);
                        break;
                }
                collapse(query, operator);
                continue;
            }
            runner = query.head;
            // check if there are "+" or "-" operators
            while (runner.next != null && operator == null) {
                if (Objects.equals(runner.data, "+") || Objects.equals(runner.data, "-")) {
                    operator = runner;
                }
                runner = runner.next;
            }
            if (operator != null) {
                switch (operator.data) {
                    case "+":
                        operator.data = String.valueOf(Double.parseDouble(operator.prev.data) + Double.parseDouble(operator.next.data));
//                        System.out.println("after addition: " + operator.data);
                        break;
                    case "-":
                        operator.data = String.valueOf(Double.parseDouble(operator.prev.data) - Double.parseDouble(operator.next.data));
//                        System.out.println("after subtraction: " + operator.data);
                        break;
                }
                collapse(query, operator);
            }
        }
        // the head is the final result
        result = Double.parseDouble(query.head.data);
   }

    private void collapse(DoublyLinkedList query, Node operator) {
        if (operator.next == query.tail) {
            query.tail = operator;
        } else {
            operator.next = operator.next.next;
            operator.next.prev = operator;
        }
        if (operator.prev == query.head) {
            query.head = operator;
        } else {
            operator.prev.prev.next = operator;

        }
    }

    public double getResult() {
        return result;
    }
}
