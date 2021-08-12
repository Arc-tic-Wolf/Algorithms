import random

def exp(a,n,mod):
    if n==0:
        return 1
    r=exp(a,int(n/2),mod)
    r=(r*r)%mod
    if n%2 == 1:
        r= (r*a)%mod
    return r

def witness (a,n):
    t=0
    u=n-1
    while((u&1)==0):
       u = u >> 1 
       t+=1
    x=[None]*n
    x[0]=exp(a,u,n)
    for i in range(1,t+1):
        x[i]= (x[i-1]*x[i-1]) % n
        if x[i] == 1 and x[i-1] != 1 and x[i-1] != (n-1):
            return True
    if x[t] !=1:
        return True
    return False 

ans=0
n=57
for i in range(1,n):
    r=witness(i,n)
    if r == True :
        ans+=1
print(ans)
