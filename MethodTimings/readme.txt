_________________
This is the test result for subset1

Result Group 1(Array size = 25,25)
function subset1 took 443004 nanoseconds to do this.
the hash version took 11663 nanoseconds to do this.

function subset2 took 11943 nanoseconds to do this.
the hash version took 7892 nanoseconds to do this.

function subset3 took 21650 nanoseconds to do this.
the hash version took 13270 nanoseconds to do this.

function subset4 took 18159 nanoseconds to do this.
the hash version took 7892 nanoseconds to do this.
_________________
This is the test result for subset2

Result Group 2(Array size = 50,50)
function subset1 took 46095 nanoseconds to do this.
the hash version took 16273 nanoseconds to do this.

function subset2 took 23397 nanoseconds to do this.
the hash version took 12921 nanoseconds to do this.

function subset3 took 41695 nanoseconds to do this.
the hash version took 12222 nanoseconds to do this.

function subset4 took 23607 nanoseconds to do this.
the hash version took 12153 nanoseconds to do this.
_________________
This is the test result for subset3

Result Group 3(Array size = 75,75)
function subset1 took 65511 nanoseconds to do this.
the hash version took 16622 nanoseconds to do this.

function subset2 took 35899 nanoseconds to do this.
the hash version took 16762 nanoseconds to do this.

function subset3 took 66559 nanoseconds to do this.
the hash version took 16692 nanoseconds to do this.

function subset4 took 35898 nanoseconds to do this.
the hash version took 16692 nanoseconds to do this.
_________________
This is the test result for subset4

Result Group 4(Array size = 100,100)
function subset1 took 122571 nanoseconds to do this.
the hash version took 21581 nanoseconds to do this.

function subset2 took 57758 nanoseconds to do this.
the hash version took 21092 nanoseconds to do this.

function subset3 took 114959 nanoseconds to do this.
the hash version took 21092 nanoseconds to do this.

function subset4 took 58038 nanoseconds to do this.
the hash version took 21093 nanoseconds to do this.
_________________
From the above results, we can infer that
1.If use normal method to sort the target array and then do binary search, the time consuming will be of O(nlgn)
2.But if use the hash method, the time consuming will be of O(n), and we can apply this hash method inside any of the 4 subset methods
3.So the best solution is to use the hash method but this method may take up extra space in memory while executed


The problem size is 10
The problem is
[436, 244, 49, 93, 41, 59, 89, 409, 119, 461]
and the sub set found is
[436, 244, 49, 93, 59, 119]
The time spent is 19555 nano seconds
_________________

The problem size is 20
The problem is
[226, 107, 7, 18, 98, 279, 36, 217, 88, 122, 34, 13, 135, 97, 147, 138, 34, 121, 75, 8]
and the sub set found is
[226, 107, 7, 18, 98, 279, 88, 122, 34, 13, 8]
The time spent is 14454143 nano seconds
_________________

The problem size is 30
The problem is
[19, 29, 5, 14, 19, 102, 5, 119, 70, 44, 29, 74, 16, 8, 192, 58, 10, 10, 1, 216, 214, 31, 81, 46, 27, 161, 107, 62, 179, 52]
and the sub set found is
[19, 29, 5, 14, 19, 102, 5, 119, 70, 44, 29, 74, 16, 8, 192, 58, 10, 1, 27, 107, 52]
The time spent is 18328864 nano seconds
_________________
From the above result we can infer that this method may take O(2^n) time, because it forms a binary tree to find the result with recursice method
