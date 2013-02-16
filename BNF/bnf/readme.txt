1. The file sampleBNF is the source file of BNF to be tokenized.
2. I treat '/n' as terminal in my implementation. So '\n' is also a legal part of any terminal. If you print out the tokens, maybe some tokens will appear as blank because they are simply new line or tab.
3. When some illegal combinations of letters happen, the program will throw out a runtime exception. (such as <bnf<> or <bnf\n>).
