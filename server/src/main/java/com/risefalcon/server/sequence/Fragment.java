package com.risefalcon.server.sequence;


import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fragment {
    private AtomTask head;
    private AtomTask tail;
    private boolean lock;
    private List<Fragment> todo;

    public int getLength() {
        List<Integer> headIndex = head.getIndex();
        List<Integer> tailIndex = tail.getIndex();
        int len = 0;
        for (int i = 0; i < headIndex.size(); i++) {
            len += (tailIndex.get(i) - headIndex.get(i)) * (head.getRadix() ^ i);
        }
        if (headIndex.size() < tailIndex.size()) {
            for (int i = headIndex.size(); i < tailIndex.size(); i++) {
                len += tailIndex.get(i) ^ i;
            }
        }
        return len;
    }

    public boolean equals(@NotNull Fragment fragment) {
        return fragment.head.compareTo(head) == 0
                && fragment.tail.compareTo(tail) == 0;
    }
}
