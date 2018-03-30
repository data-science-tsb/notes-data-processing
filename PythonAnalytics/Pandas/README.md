# Pandas
Python Dataframes/Tables

### Creating a Series
```python
labels = ['a', 'b', 'c']
my_data = [10,20,30]
arr = np.array(my_data)
d = {'a':10,'b':20,'c':'30'}

pd.Series(data = my_data, index = labels)
pd.Series(my_data,labels)

pd.Series(d)
```
| Label | Data |
| ----- | ---- |
|a      | 10   |
|b      | 20   |
|c      | 30   |

### Creating a DataFrame
Multiple series sharing an index
```python
df = pd.DataFrame(randn(5,4), ['A', 'B', 'C', 'D', 'E'], ['W', 'X', 'Y', 'Z'])
```

|   | W	        |X	        |   Y	      |        Z|
|---|-----------|-----------|-----------|---------|
|A	|2.706850	  |0.628133	  |0.907969   |	0.503826|
|B	|0.651118	  |-0.319318	|-0.848077  |	0.605965|
|C	|-2.018168	|0.740122	  |0.528813   |-0.589001|
|D	|0.188695	  |-0.758872  |-0.933237  |0.955057 |
|E	|0.190794   |1.978757	  |2.605967	  |0.683509 |

### Operations
```python
df['sumXY'] = df['X'] + df['Y'] #creates a new column

df = df.drop('sumXY',axis=1) #removes the column (this does not mutate the original object)
df.drop('sumXY',axis=1,inplace=True) #inplace = mutate the original object

df.drop('E') #remove a row
```

### Selecting Subsets
```python
df.loc['A':'B']
df.iloc[0:2]
```
|	W	|X	|Y	|Z |
|---|---|---|--|
|A	|2.706850	|0.628133	|0.907969	|0.503826|
|B	|0.651118	|-0.319318	|-0.848077	|0.605965|

```python
df.loc[['A','B'], ['X', 'Y']]
```
|X	|Y|
|---|-|
|A	|0.628133	|0.907969|
|B	|-0.319318	|-0.848077|

### Conditional Selection
```python
df[df['W'] > 0] #rows where W is positive

# replace 'and' and 'or' with & and | respectively
df[(df['W'] > 0) & (df['Y'] > 1)] #rows where W is positive and Y is greater than 1
```

### Reset Index
```python
reset_df = df.reset_index()
```

|index	|W	|X	|Y	|Z	|sumXY|
|	--|--	|--	|--	|--	|--|
|0	|A	|2.706850	|0.628133	|0.907969	|0.503826	|1.536102|
|1	|B	|0.651118	|-0.319318	|-0.848077	|0.605965	|-1.167395|
|2	|C	|-2.018168	|0.740122	|0.528813	|-0.589001	|1.268936|
|3	|D	|0.188695	|-0.758872	|-0.933237	|0.955057	|-1.692109|
|4	|E	|0.190794	|1.978757	|2.605967	|0.683509	|4.584725|
