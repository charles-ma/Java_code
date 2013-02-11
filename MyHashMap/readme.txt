The time of the put method of ListHashMap repeated 2000000 times is 115748240 nano secs
The time of the get method of ListHashMap repeated 2000000 times is 159863345 nano secs
The time of the put method of ListHashMap repeated 3000000 times is 170560727 nano secs
The time of the get method of ListHashMap repeated 3000000 times is 205799560 nano secs
The time of the put method of ListHashMap repeated 4000000 times is 224041691 nano secs
The time of the get method of ListHashMap repeated 4000000 times is 262790469 nano secs
The time of the put method of ListHashMap repeated 5000000 times is 284738587 nano secs
The time of the get method of ListHashMap repeated 5000000 times is 334716655 nano secs

The time of the put method of TreeHashMap repeated 2000000 times is 130379783 nano secs
The time of the get method of TreeHashMap repeated 2000000 times is 300482901 nano secs
The time of the put method of TreeHashMap repeated 3000000 times is 287112768 nano secs
The time of the get method of TreeHashMap repeated 3000000 times is 562051362 nano secs
The time of the put method of TreeHashMap repeated 4000000 times is 463482471 nano secs
The time of the get method of TreeHashMap repeated 4000000 times is 918674504 nano secs
The time of the put method of TreeHashMap repeated 5000000 times is 334988902 nano secs
The time of the get method of TreeHashMap repeated 5000000 times is 1206268896 nano secs

The time of the put method of Java HashMap repeated 2000000 times is 124662847 nano secs
The time of the get method of Java HashMap repeated 2000000 times is 24649013 nano secs
The time of the put method of Java HashMap repeated 3000000 times is 84607689 nano secs
The time of the get method of Java HashMap repeated 3000000 times is 35057250 nano secs
The time of the put method of Java HashMap repeated 4000000 times is 99218972 nano secs
The time of the get method of Java HashMap repeated 4000000 times is 49327083 nano secs
The time of the put method of Java HashMap repeated 5000000 times is 129686394 nano secs
The time of the get method of Java HashMap repeated 5000000 times is 58129392 nano secs


Above is one result from my experiment.
Due to some system factor, there maybe some dirty datas in the result.

In theory, the put and get method of hashmap should be O(1)
So if you repeat it for N, 2N, 3N, 4N times, the time will increase linearly

In my implementation, the put method of list hash will be of O(1), the get method of list hash will be of O(n) (the bucket number is certain, so the length of the linkedlists will be growing linearly), so the experiment time of put method is actually growing linearly while the time of the get method will be O(cn^2), and due to the value of c, it will appear to be growing a little more than linearly.

The put and get method of tree hash will be of O(lgn) and O(n) (I didn't use binary search in the get method). The experiment time are all a little more than linear.

The put and get method of Java hash is O(1), so the experiment time of this hash appears to be linear.

