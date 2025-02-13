```toml
name = 'Save Review'
method = 'POST'
url = 'http://localhost:8080/reviews'
sortWeight = 1000000
id = '8fa70779-f78d-44ec-b3b6-b830723c734c'

[body]
type = 'JSON'
raw = '''
{
   "bookId" :  1,
  "reviewerName": "OCtavio",
  "comment": "Good book",
  "rating": 5
}'''
```
