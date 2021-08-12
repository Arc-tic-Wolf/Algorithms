import cmath

def fft(cs, sign):
  n = len(cs)
  if n == 1: return [cs[0]]
  cs1, cs2 = [], []
  
  for i, c in enumerate(cs):
    if i & 1: cs2.append(c)
    else: cs1.append(c)

  es = fft(cs1, sign)
  ds = fft(cs2, sign)
  y = [ None ] * n
  ww = cmath.exp(complex(0, sign*2*cmath.pi/n))
  w = 1
  for k in range(n//2):
    y[k] = es[k] + w * ds[k]
    y[k+n//2] = es[k] - w * ds[k]
    w *= ww
  return y

def mult(a, b):
  n = 1
  max_len = max(len(a), len(b))
  while n < max_len: n *= 2
  for i in range(2*n-len(a)): a.append(0)
  for i in range(2*n-len(b)): b.append(0)
  a_fft = fft(a, 1)
  b_fft = fft(b, 1)
  c_fft = []
  for i in range(2*n):
    c_fft.append(a_fft[i] * b_fft[i])
  c = fft(c_fft, -1)
  c = map(lambda x: int(0.5 + x.real/(2*n)), c)
  res, carry = [], 0
  for x in c:
    tmp = x + carry
    res.append(tmp % 10)
    carry = tmp / 10
  if carry: res.append(carry)
  return res

#Application of fft algo for fast number multtiplication
if __name__ == "__main__":
  xx=int(input("first: "))
  yy=int(input("second: "))

  xs = map(lambda x: int(x), reversed(list(str(xx))))
  ys = map(lambda x: int(x), reversed(list(str(yy))))
  res = mult(list(xs), list(ys))
  sum=(res[len(res)-1])

  while int(sum)!=sum:
    sum*=10
  res1=int(sum)
  res2 = xx * yy
  print(res1,res2)
