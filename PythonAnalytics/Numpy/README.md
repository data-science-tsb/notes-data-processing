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

# randomly-generated vector
arr = np.random.rand(5) #5 elements (0 to 1)
arr = np.random.randn(5) #5 elements around 0, -1 to 1 normally distributed
```

### Generating Matrices
```python
arr_2d = np.zeros((2,10))
# array([
#         [0., 0., 0., 0., 0., 0., 0., 0., 0., 0.],
#         [0., 0., 0., 0., 0., 0., 0., 0., 0., 0.]
#     ])

arr_2d = np.linspace(40,99,100).reshape(10,10) #10x10 array

arr_eye = np.eye(3)
# identity matrix
# array([[1., 0., 0.],
#        [0., 1., 0.],
#        [0., 0., 1.]])

# randomly-generated matrices
arr_5x5 = np.random.rand(5,5)
arr_5x5 = np.random.randn(5,5) #Gaussian distribution
```
