package cn.lihua.week09;

/**
 * 面试题52. 两个链表的第一个公共节点
 */
public class Interview52GetIntersectionNode {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 时间复杂度：O(M+N)O(M+N)。
     * 空间复杂度：O(1)O(1)。
     * 精选答案:双指针法
     * node1和node2,都分别走了2条链路,M+N;
     * 逆向思维: 两指针走完2条链路会有M+N步,但最后有一段是公共的X步,
     * 同时减去这段公共的X步,还是相同的步数M+N-X,而且也是第一次相交的地方.
     * 如果没有公共部分，X=0， 那么走过M+N步都指向Null，返回Null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1 != null ? node1.next : headB;
            node2 = node2 != null ? node2.next : headA;
        }
        return node1;
    }
}
