def init_lps(w):
    m = len(w);i = 0;j = 1
    lps=[None]*m
    lps[0] = 0
    while j < m:
        if w[i] == w[j]:
            i += 1
            lps[j] = i
            j += 1
        elif i == 0:
            lps[j] = 0
            j += 1
        else:
            i = lps[i - 1]
    return lps

def kmp_search(w, s):
    lps=init_lps(w)
    i=0;j=0
    m = len(w);n = len(s)
    while i < m and j < n:
        if w[i] == s[j] and i == m - 1:
            return True
        elif w[i] == s[j]:
            i += 1
            j += 1
        else:
            if i != 0:
                i = lps[i-1] 
            else:
                j += 1
    return False

#Implementation of KMP algorithm for string pattern searching
if __name__ == "__main__":
    txt = input("Text: ")
    pat = input("Pattern: ")
    if kmp_search(pat, txt):
        print("Match!")
    else:
        print("No substring found.")