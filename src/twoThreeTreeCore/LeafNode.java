package twoThreeTreeCore;
class LeafNode extends Node {
    int key;    
    LeafNode(int key) {
        this.key = key;
    }
    void print() {
        System.out.print(key + " ");
    } 
} 
