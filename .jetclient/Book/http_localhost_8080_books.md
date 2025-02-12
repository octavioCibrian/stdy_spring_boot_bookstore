```toml
name = 'http://localhost:8080/books'
method = 'POST'
url = 'http://localhost:8080/books'
sortWeight = 1000000
id = 'df5330fa-9456-4e49-96be-2bd824afc641'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '{"title": "Book title", "isbn": "12345", "authors": { "id": 1}}'
```
