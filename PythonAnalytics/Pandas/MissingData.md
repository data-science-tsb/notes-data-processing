# Pandas: Working with Missing Data
When some cells are missing, either drop the row or fill it up with another value
```python
df = pd.DataFrame({'A':[1,2,np.nan],
                  'B':[5,np.nan,np.nan],
                  'C':[1,2,3]})
```

### Drop Rows
```python
df.dropna() #drop all rows with missing values
df.dropna(axis=1) #drop columns with missing values
df.dropna(thresh=2) #drop rows with missing columns > 2
```

### Fill Missing Data
```python
df.fillna(value='FILL VALUE') #replace NaN with a string
df['A'].fillna(value=df['A'].mean()) #replace NaN with the mean of existing values
```
