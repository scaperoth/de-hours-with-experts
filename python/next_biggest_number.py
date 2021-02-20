#!/usr/bin/python3
import sys
from itertools import permutations  

def main():
    next_biggest_number(sys.argv[1])

# finds next biggest number based on O(n)
# traversal algorithm
def next_biggest_number(num):
    num_s = str(num)
    num_l = [ int(i) for i in list(num_s)]
    left, right = ([], [])

    # (1) split left and right at left number being 
    # smaller than right number
    for idx, n in reversed(list(enumerate(num_l))):
        left = num_l[:idx]
        right = num_l[idx:]
        if len(left) == 0 or left[-1] < right[0]:
            break
            
    # (2) if we traversed the whole number, we're donezo
    if len(left) == 0:
        return -1
    
    swap_idx = 0 # number on right to swap 

    # (3) find new number on right less than the splitting number
    for idx, right_num in enumerate(right):
        if right_num > left[-1] and right_num < right[swap_idx]:
            swap_idx = idx

    # (4) swap lowest higher number on right with final left number 
    # which is the splitting number
    right[swap_idx], left[-1] = left[-1], right[swap_idx]

    # (5) sort the right side and BAM! we're done
    right.sort()

    # convert int list to string and back into single integer value
    str_list =  [str(i) for i in (left + right)]
    return int(''.join(str_list))

if __name__ == "__main__":
    main()



