# Pandas: Group By
Aggregating dataframes

```python
df = pd.DataFrame({
        'Company':['GOOG','GOOG','MSFT','MSFT','FB','FB'],
        'Person':['Sam','Charlie','Amy','Vanessa','Carl','Sarah'],
        'Sales':[200,120,340,124,243,350]
        })
```
|Company	|Person	|Sales|
|---------|-------|-----|
|0	|GOOG	|Sam	|200|
|1	|GOOG	|Charlie	|120|
|2	|MSFT	|Amy	|340|
|3	|MSFT	|Vanessa	|124|
|4	|FB	|Carl	|243|
|5	|FB	|Sarah	|350|

```python
df.groupby("Company").mean()
```
| | Sales|
|-|------|
|Company||	
|FB	|296.5|
|GOOG	|160.0|
|MSFT	|232.0|
