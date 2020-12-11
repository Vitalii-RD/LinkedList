package com.dudnyk;

import java.util.function.Consumer;

public class LinkedList {
  private ListNode head;
  private ListNode tail;
  private int size;

  public LinkedList() {
    size = 0;
  }

  public void append(String value) {
      ListNode newNode = new ListNode(value);
      if (head == null) {
        head = newNode;
      } else {
        tail.next = newNode;
        newNode.prev = tail;
      }

    tail = newNode;
    size++;
  }

  public void insert(int index, String value) throws IndexOutOfBoundsException {
    if (!isInRange(index)) throw new IndexOutOfBoundsException("Wrong index");

    ListNode newNode = new ListNode(value);

    if (index == size) {
      this.append(value);
      return;
    } else if (index == 0) {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    } else {
      ListNode node = getNode(index);

      node.prev.next = newNode;
      node.prev = newNode;
      newNode.prev = node.prev;
      newNode.next = node;
    }
    size++;
  }

  public void replace(int index, String value) throws IndexOutOfBoundsException {
    if (!isInRange(index)) throw new IndexOutOfBoundsException("Wrong index");

    ListNode newNode = new ListNode(value);

    if (index == 0) {
      if (head.next != null) head.next.prev = newNode;
      newNode.next = head.next;
      head = newNode;
    } else if (index == size-1) {
      tail.prev.next = newNode;
      newNode.prev = tail.prev;
      tail = newNode;
    } else {
      ListNode node = getNode(index);

      newNode.prev = node.prev;
      newNode.next = node.next;
      node.prev.next = newNode;
      node.next.prev = newNode;
    }
  }

  public void remove(int index) throws IndexOutOfBoundsException {
    if (!isInRange(index)) throw new IndexOutOfBoundsException("Wrong index");

    if (index == 0) {
      head = head.next;
    } else if (index == size-1) {
      tail = tail.prev;
    } else {
      ListNode node = getNode(index);
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    size--;
  }

  private boolean isInRange(int index) {
    return index <= size - 1 && index >= 0 && size != 0;
  }


  public void forEach(Consumer<String> consumer) {
    for (ListNode node = head; node != null; node = node.next) {
      consumer.accept(node.value);
    }
  }

  public String get(int index) throws IndexOutOfBoundsException {
    if(!isInRange(index)) throw new IndexOutOfBoundsException("Wrong index");
    return getNode(index).value;
  }

  private ListNode getNode(int index){
    ListNode node = head;
    for(int i = 0; i < index; i++) node = node.next;

    return node;
  }

  public int getSize() {
    return size;
  }

  public void printList() {
    for (ListNode node = head; node != null; node = node.next) {
      System.out.println(node.value);
    }
  }
}
