import re
n=int(input())
pat=[]
for i in range(n):
    s=input()
    sum=re.compile(s)
    pat.append(sum)

n=int(input())
for i in range(n):
    s=input()
    flag=True
    j=1
    for k in pat:
        if k.fullmatch(s) != None:
            print("YES,",j)
            flag=False
        j+=1
    if flag:
        print("NO, 0")
