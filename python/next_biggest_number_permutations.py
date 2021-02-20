#!/usr/bin/python3
import sys
from itertools import permutations  

def main():
    next_biggest_number(sys.argv[1])

# of a given number, get the next highest 
# permuation (order matters)
# wanted to give this a try just for fun
def next_biggest_number(num):
    unique_permutations = get_unique_perms(num)
    next_highest = get_next_highest(unique_permutations, num)
    return next_highest

# convert iterable object to single int
def obj_to_int(_obj):
    return int(''.join(_obj))

# get all the unique permutations of number
def get_unique_perms(num):
    # convert to list of strings 
    num_str_list = list(str(num))
    
    # get permutations
    perm = permutations(list(num_str_list))  

    # convert results to int
    perms_as_int = [obj_to_int(i) for i in perm]

    # strip duplicates to get unique nums
    unique_num = []
    [unique_num.append(j) for j in perms_as_int if j not in unique_num]

    # sort lowest to largest
    unique_num.sort()
    return unique_num

# get all numbers greater than min and return -1
# if that list is only 1 (the current number)
# and return the next largest in list otherwise
def get_next_highest(num_list, min_num):
    max_v = [k for k in num_list if k >= int(min_num)]
    return max_v[1] if len(max_v) > 1 else -1

if __name__ == "__main__":
    main()



