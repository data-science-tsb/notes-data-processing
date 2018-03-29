# NumPy
Array(Vector and Matrix) processing

[Docs](https://docs.scipy.org/doc/numpy-1.13.0/reference/)

### Start Using NumPy
```python
my_list = [1,2,3]

import numpy as np
arr = np.array(my_list)
```

### Generating Vectors
```python
arr = np.arange(0,10) #0,1 ... 9
arr = np.arange(0,10,0.5) #0,0.5 ... 9.6
arr = np.zeros(10) #ten zeroes
arr = np.ones(10) #ten ones
arr = np.linspace(0,10,100) #100 evenly-spaced points from 0 to 10
```

### Generating Matrices
```python
arr2d = np.zeros((2,10))
# array([
#         [0., 0., 0., 0., 0., 0., 0., 0., 0., 0.],
#         [0., 0., 0., 0., 0., 0., 0., 0., 0., 0.]
#     ])

arr2d = np.linspace(40,99,100).reshape(10,10) #10x10 array
```
